package org.dblab.auction_backend.config;

import java.sql.Date;
import java.util.List;

import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.service.AuctionService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BidDeadlineTimer extends Thread {

    private static List<BidClosingDTO> bidClosingDTOs = null;
    private static long DEADLINE_STANDARD_TIME = 10800000;             // 현재시간으로부터 마감경매를 찾는 기준 시간, 3시간(=10800000ms, 1000 * 60 * 60 * 3)
    private static long sleepTime = DEADLINE_STANDARD_TIME;
    private static Date latestTime = null;
    private final AuctionService auctionService;

    @Override
    public void run() {
        System.out.println("BidDeadlineTimerThread 실행");     
        
        while(true){
            try {
                if (bidClosingDTOs == null || bidClosingDTOs.size() == 0) {
                    bidClosingDTOs = auctionService.getRecentlyBid(); 

                    // 3시간 이내 마감될 경매가 없는 경우, 3시간 sleep 
                    if (bidClosingDTOs.size() == 0){
                        sleepTime = DEADLINE_STANDARD_TIME;
                        latestTime = null;
                    } else {
                        latestTime = bidClosingDTOs.get(bidClosingDTOs.size()-1).getDeadline_date();
                        sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                    }

                    // 테스트 코드 수정하기!!!!
                    if (sleepTime < 0) sleepTime = 12000;

                    for(int i = 0; i < bidClosingDTOs.size(); i++) 
                        System.out.println(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
                    System.out.println("-----------------------------------------" + System.currentTimeMillis() + "  sleepTime : "  + sleepTime);
                } else {
                    // 현재 시간에서 가까운 값 빼기(초 단위)
                    sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                    System.out.println("bidClosingDTOs.get(0).getDeadline_date().getTime(): " + bidClosingDTOs.get(0).getDeadline_date().getTime());
                    System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
                    System.out.println("sleepTime : " + sleepTime);
                    
                    System.out.println("마감경매 검사");
                    System.out.println("sleepTime : " + sleepTime);
                    System.out.println("bidClosingDTOs.auction_Id()" + bidClosingDTOs.get(0).getAuction_Id());

                    // 0.01 초 미만 시
                    if (sleepTime < 10) {
                        System.out.println("마감경매 추가!!");
                        auctionService.closeBidding(bidClosingDTOs.get(0).getAuction_Id());
                        bidClosingDTOs.remove(0);
                        if(bidClosingDTOs.size() > 0){
                            sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                        }
                    }
                }
                if(sleepTime < 0) sleepTime = 60000;
                System.out.println("sleep time : " + sleepTime);
                Thread.sleep(sleepTime);    // 헤당 시간 만큼 sleep
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("마감경매 인터럽트!!");
            } 
        }
    }

    // 마감 시간 확인 후 삽입 메소드
    public static int checkBidding(int auction_Id, Date deadline_date){
        System.out.println("auction_Id: " + auction_Id);
        System.out.println("deadline_date: " + deadline_date.getTime());
        System.out.println("latestTime: " + latestTime);
        if (latestTime == null ) {
            long tmpSleepTime = deadline_date.getTime() - System.currentTimeMillis();
            System.out.println("latestTime == null");
            System.out.println("tmpSleepTime: " + tmpSleepTime + " DEADLINE_STANDARD_TIME: " + DEADLINE_STANDARD_TIME);
            if ((tmpSleepTime < DEADLINE_STANDARD_TIME)){
                bidClosingDTOs.add(new BidClosingDTO(auction_Id, deadline_date));
                latestTime = deadline_date;
                sleepTime = tmpSleepTime;
                for(int i = 0; i < bidClosingDTOs.size(); i++) 
                    System.out.println(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
                return 1;
            }
        } else if (deadline_date.getTime() - latestTime.getTime() < 0) {
            System.out.println("bidClosingDTOs의 길이만큼 비교하면서 삽입");
            System.out.println("latestTime: " + latestTime);
            System.out.println("bidClosingDTOs.size(): " + bidClosingDTOs.size());
            // bidClosingDTOs의 길이만큼 비교하면서 삽입
            for(int i = 0; i < bidClosingDTOs.size(); i++) {  
                System.out.println("bidClosingDTOs.get(i).getDeadline_date().getTime()" + bidClosingDTOs.get(i).getDeadline_date().getTime() + " " + i);                                                  
                if (deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime() < 0){
                    System.out.println("deadline_date.getTime() = " +  deadline_date.getTime());
                    System.out.println("bidClosingDTOs.get(i).getDeadline_date().getTime() = " +  bidClosingDTOs.get(i).getDeadline_date().getTime());
                    System.out.println("deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime() = " +  (deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime())); 
                    bidClosingDTOs.add(i, new BidClosingDTO(auction_Id, deadline_date));
                    break;
                } 
                    
            }
            for(int i = 0; i < bidClosingDTOs.size(); i++) 
                    System.out.println(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
            return 1;
        }
        return 0;
    }
}
