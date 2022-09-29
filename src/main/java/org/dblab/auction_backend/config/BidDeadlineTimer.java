package org.dblab.auction_backend.config;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.service.AuctionService;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BidDeadlineTimer extends Thread {
    private static Logger log = LoggerFactory.getLogger(BidDeadlineTimer.class);
    private static List<BidClosingDTO> bidClosingDTOs = null;
    private static long DEADLINE_STANDARD_TIME = 1000 * 60 * 60 * 3;             // 현재시간으로부터 마감경매를 찾는 기준 시간, 3시간(=10800000ms, 1000 * 60 * 60 * 3)
    private static long sleepTime = DEADLINE_STANDARD_TIME;
    private static Date latestTime = null;
    private static Boolean mustSleep = false;
    private final AuctionService auctionService;

    @Override
    public void run() {
        log.info("BidDeadlineTimerThread 실행");     
        
        while(true){
            try {
                if (bidClosingDTOs == null || bidClosingDTOs.size() == 0) {
                    log.info("mustSleep: " + mustSleep);
                    if(mustSleep){
                        sleepTime = 30000;
                        mustSleep = false;
                    } else{
                        bidClosingDTOs = auctionService.getRecentlyBid(); 

                        // 3시간 이내 마감될 경매가 없는 경우, 3시간 sleep 
                        if (bidClosingDTOs.size() == 0){
                            sleepTime = DEADLINE_STANDARD_TIME;
                            latestTime = null;
                            log.info("########## : " + sleepTime);
                        } else {
                            latestTime = bidClosingDTOs.get(bidClosingDTOs.size()-1).getDeadline_date();
                            sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                            log.info(bidClosingDTOs.get(0).toString());
                        }
                        if (sleepTime < 0) sleepTime = 30000;

                        for(int i = 0; i < bidClosingDTOs.size(); i++) 
                            log.info(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
                        mustSleep = false;
                    }
                    log.info("-----------------------------------------" + System.currentTimeMillis() + "  sleepTime : "  + sleepTime);
                } else {
                    mustSleep = false;
                    // 현재 시간에서 가까운 값 빼기(초 단위)
                    sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                    log.info("bidClosingDTOs.get(0).getDeadline_date().getTime(): " + bidClosingDTOs.get(0).getDeadline_date().getTime());
                    log.info("System.currentTimeMillis(): " + System.currentTimeMillis());
                    log.info("sleepTime : " + sleepTime);
                    
                    log.info("마감경매 검사");
                    log.info("sleepTime : " + sleepTime);
                    log.info("bidClosingDTOs.auction_Id()" + bidClosingDTOs.get(0).getAuction_Id());

                    // 0.01 초 미만 시
                    if (sleepTime < 10) {
                        log.info("마감경매 추가!!");
                        auctionService.closeBidding(bidClosingDTOs.get(0).getAuction_Id());
                        bidClosingDTOs.remove(0);
                        if(bidClosingDTOs.size() > 0){
                            sleepTime = bidClosingDTOs.get(0).getDeadline_date().getTime() - System.currentTimeMillis();
                        }
                    }
                }
                if(sleepTime < 0) sleepTime = 60000;
                log.info("sleep time : " + sleepTime);
                Thread.sleep(sleepTime);    // 헤당 시간 만큼 sleep
            } catch (InterruptedException interruptedException) {
                log.info(interruptedException.toString());
                log.info("마감경매 인터럽트!!");
            } catch (Exception e){
                log.info(e.toString());
                log.info("SQL 에러!! 30초 sleep");
                bidClosingDTOs = null;
                mustSleep = true;
            }
        }
    }

    // 마감 시간 확인 후 삽입 메소드
    public static int checkBidding(int auction_Id, Date deadline_date){
        log.info("auction_Id: " + auction_Id);
        log.info("deadline_date: " + deadline_date.getTime());
        log.info("latestTime: " + latestTime);
        if (latestTime == null ) {
            long tmpSleepTime = deadline_date.getTime() - System.currentTimeMillis();
            log.info("latestTime == null");
            log.info("tmpSleepTime: " + tmpSleepTime + " DEADLINE_STANDARD_TIME: " + DEADLINE_STANDARD_TIME);
            if ((tmpSleepTime < DEADLINE_STANDARD_TIME)){
                bidClosingDTOs.add(new BidClosingDTO(auction_Id, deadline_date));
                latestTime = deadline_date;
                sleepTime = tmpSleepTime;
                for(int i = 0; i < bidClosingDTOs.size(); i++) 
                    log.info(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
                return 1;
            }
        } else if (deadline_date.getTime() - latestTime.getTime() < 0) {
            log.info("bidClosingDTOs의 길이만큼 비교하면서 삽입");
            log.info("latestTime: " + latestTime);
            log.info("bidClosingDTOs.size(): " + bidClosingDTOs.size());
            // bidClosingDTOs의 길이만큼 비교하면서 삽입
            for(int i = 0; i < bidClosingDTOs.size(); i++) {  
                log.info("bidClosingDTOs.get(i).getDeadline_date().getTime()" + bidClosingDTOs.get(i).getDeadline_date().getTime() + " " + i);                                                  
                if (deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime() < 0){
                    log.info("deadline_date.getTime() = " +  deadline_date.getTime());
                    log.info("bidClosingDTOs.get(i).getDeadline_date().getTime() = " +  bidClosingDTOs.get(i).getDeadline_date().getTime());
                    log.info("deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime() = " +  (deadline_date.getTime() - bidClosingDTOs.get(i).getDeadline_date().getTime())); 
                    bidClosingDTOs.add(i, new BidClosingDTO(auction_Id, deadline_date));
                    break;
                } 
                    
            }
            for(int i = 0; i < bidClosingDTOs.size(); i++) 
                    log.info(bidClosingDTOs.get(i).getAuction_Id() + "  " + bidClosingDTOs.get(i).getDeadline_date().getTime());
            return 1;
        }
        return 0;
    }
}
