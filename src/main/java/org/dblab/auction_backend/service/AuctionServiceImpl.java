package org.dblab.auction_backend.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.dblab.auction_backend.config.ThreadControl;
import org.dblab.auction_backend.domain.AlertDTO;
import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.SearchWordDTO;
import org.dblab.auction_backend.domain.WishDTO;
import org.dblab.auction_backend.mapper.AuctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService{

    private final AuctionMapper auctionMapper;
    private Logger log = LoggerFactory.getLogger(AuctionServiceImpl.class);
    private String PRODUCT_IMG_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/product_images/";
    private String AUCTION_REVIEW_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/auciton_review_images/";
    private HashMap<Integer,SseEmitter> consumerEmitters = new HashMap<Integer, SseEmitter>();
    private HashMap<Integer,SseEmitter> farmEmitters = new HashMap<Integer, SseEmitter>();
    private int FIRST_BID_ALERT = 0;    // 열거형 
    private int BID_ALERT = 1;
    private int END_AUCTION_ALERT = 2;
    private int REIVEW_ALERT = 3;
    private Boolean EARLY_CLOSING = true;

    // #################################################### 경매 CURD #####################################################

    @Override
    public int registAuction(AuctionDTO auctionDTO) {
        log.info("registAuction.........." + auctionDTO);

        // 유저 이름 + 날짜, 임시로 농가 ID 사용!
        String product_img_name = auctionDTO.getFarm_id() + "," + LocalDateTime.now().toString().substring(0, 19);
        // 이미지 개수
        int numberOfImg = auctionDTO.getProductDTO().getProduct_img_files().size();
        System.out.println("numberOfImg: " + numberOfImg);
        try {
            for (int i=0; i<numberOfImg; i++){
                auctionDTO.getProductDTO().getProduct_img_files().get(i).transferTo(new File(PRODUCT_IMG_PATH + product_img_name + "(" + i + ")" + numberOfImg +".png"));
            }
            System.out.println(product_img_name + " 파일 저장 완료");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        auctionDTO.getProductDTO().setProduct_img_name(product_img_name + "(0)" + numberOfImg);
        auctionDTO.getProductDTO().setFarm_id(auctionDTO.getFarm_id());

        auctionMapper.registProduct(auctionDTO.getProductDTO());
        log.info("product_id: " + auctionDTO.getProductDTO().getProduct_id());
        log.info(auctionDTO.toString());
        
        // casting 오류
        BidClosingDTO bidClosingDTO = auctionMapper.registAuction(auctionDTO);

        log.info(bidClosingDTO.toString());
        // 새롭게 등록된 경매의 마감일을 마감경매 타이머 리스트에 저장된 데이터의 경매 마감일들과 비교한다. 
        if (bidClosingDTO.getAuction_Id() != null) {
            ThreadControl.checkBidding(bidClosingDTO.getAuction_Id(), bidClosingDTO.getDeadline_date());
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<AuctionDTO> getAuction(int limit) {
        log.info("getAuction.........." + limit);
        List<AuctionDTO> auctionDTOs = auctionMapper.getAuction(limit);
        log.info(auctionDTOs.toString());

        return auctionDTOs;
    }

    @Override
    public int updateAuction(AuctionDTO auctionDTO) {
        log.info("updateAuction..........");
        // 이미지 변경했는지 여부 확인하기, 이전 이미지 이름
        return auctionMapper.updateAuction(auctionDTO);
    }

    @Override
    public int deleteAuction(int auction_Id, int product_id) {
        log.info("deleteAuction..........");
        auctionMapper.deleteAuction(auction_Id);
        return auctionMapper.deleteProduct(product_id);
    }

    @Override
    public int updateBidding(Bidding bidding) {
        System.out.println(bidding.toString());
        if(bidding.getIsMaxPrice() == 1){
            auctionMapper.updateMaxPriceBidding(bidding);
            closeBidding(bidding.getAuction_Id(), EARLY_CLOSING);
            return 1;
        } 
        auctionMapper.updateBidding(bidding);

        int d_status = FIRST_BID_ALERT;
        int alertResult = 0;

        if (bidding.getAuction_consumer_id() != null){
            d_status = BID_ALERT;
        }
        alertResult = registAlert(bidding, d_status);
        return alertResult;
    }

    public AuctionDTO auctionInfo(int auction_Id) {
        AuctionDTO tmp = auctionMapper.auctionInfo(auction_Id);
        System.out.println(tmp.toString());
        return tmp;
    }

    // #################################################### 상품 U #####################################################
    
    @Override
    public int updateProduct(ProductDTO productDTO) {

        // 이미지 변경 시 처리 코드 
        return auctionMapper.updateProduct(productDTO);
    }

    @Override
    public int registWish(WishDTO wishDTO){
        if(auctionMapper.checkWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id())==0){
            return auctionMapper.registWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id());
        } else {
            return auctionMapper.deleteWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id());
        }
    }
    @Override
    public int checkWish(int auction_id, int consumer_id){
        System.out.println(auction_id+" "+consumer_id);

        return auctionMapper.checkWish(auction_id, consumer_id);
    }

    @Override
    public int deleteWish(int auction_id, int consumer_id){
        return auctionMapper.deleteWish(auction_id, consumer_id);
    }

    // #################################################### 리뷰 CRUD #####################################################

    @Override
    public int registAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("registAuctionReview.........." + auctionReview.toString());

        if(auctionReview.getCheckUser().equals("consumer")){
            auctionMapper.plusConsumerPachiPoint(auctionReview.getConsumer_id());
            // 리뷰 이미지 저장
            if(auctionReview.getReview_img_file() != null){
                auctionReview.setReview_img_name(auctionReview.getAuction_Id() + "_" + LocalDateTime.now().toString().substring(0, 19));
                try {
                    auctionReview.getReview_img_file().transferTo(new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + auctionReview.getReview_img_name() + ".png"));
                    System.out.println(auctionReview.getAuction_name() + " 새로운 리뷰 이미지 저장 완료");
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            auctionMapper.registConsumerAuctionReview(auctionReview);
        } else {
            auctionMapper.plusFarmPachiPoint(auctionReview.getFarm_id());
            auctionMapper.registFarmAuctionReview(auctionReview);
        }
        

        return registAlert(new Bidding(auctionReview.getAuction_Id(), auctionReview.getAuction_name(), auctionReview.getConsumer_id()), REIVEW_ALERT);
    }

    @Override
    public List<Map<String, Object>> getAuctionReview(String checkUser, int id) {
        log.info("getAuctionReview..........");
        return checkUser.equals("consumer") ? auctionMapper.getConsumerAuctionReview(id) : auctionMapper.getFarmAuctionReview(id);
    }

    @Override
    public int updateAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("updateAuctionReview..........");
        
        if(auctionReview.getCheckUser().equals("consumer")){
            File newAuctionReviewImg=new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + auctionReview.getReview_img_name() + ".png");

            if(newAuctionReviewImg.exists()){
                if(newAuctionReviewImg.delete()){
                    System.out.println(auctionReview.getReview_img_name()+"리뷰 이미지 삭제 성공");
                } else{
                    System.out.println(auctionReview.getReview_img_name()+"리뷰 이미지 삭제 실패");
                }
            } else{
                System.out.println("리뷰 사진 파일이 존재하지 않습니다.");
            }

            auctionReview.setReview_img_name(auctionReview.getAuction_Id() + "_" + LocalDateTime.now().toString().substring(0, 19));
            try{
                auctionReview.getReview_img_file().transferTo(new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + auctionReview.getReview_img_name()+".png"));
                System.out.println(auctionReview.getReview_img_name() + " 새로운 리뷰 이미지 저장 완료");

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return auctionMapper.updateConsumerAuctionReview(auctionReview);
        } else {
            return auctionMapper.updateFarmAuctionReview(auctionReview);
        }
        
        

        //return auctionReview.getCheckUser().equals("consumer") ? auctionMapper.updateConsumerAuctionReview(auctionReview) : auctionMapper.updateFarmAuctionReview(auctionReview);
    }

    @Override
    public int deleteAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("deleteAuctionReview..........");
        auctionMapper.minusConsumerPachiPoint(auctionReview.getConsumer_id());
        return auctionMapper.deleteAuctionReview(auctionReview);
    }

    @Override
    public List<Map<String, Object>> getProductInfo(int product_id) {
        log.info("getProductInfo");
        return auctionMapper.getProductInfo(product_id);
    }

    
    // #################################################### 알림 #####################################################

    @Override
    public SseEmitter registEmitter(String checkUser, int id, SseEmitter emitter) {
        
        try {
            System.out.println("id: " + id);
            emitter.send(SseEmitter.event().name(""+id));

            // checkUser와 id를 이용해서 Alert 데이터 가져와서 emitter에 저장하기
            if (checkUser.equals("consumer")){
                List<Map<String, Object>> alertDTOs = auctionMapper.getConsumerAlert(id);

                emitter.send(SseEmitter.event().name("init").data(alertDTOs));
                emitter.onCompletion(() -> consumerEmitters.remove(id));
                consumerEmitters.put(id, emitter);
            } else {
                List<Map<String, Object>> alertDTOs = auctionMapper.getFarmAlert(id);
                emitter.send(SseEmitter.event().name("init").data(alertDTOs));
                emitter.onCompletion(() -> farmEmitters.remove(id));
                farmEmitters.put(id, emitter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emitter;
    }
    
    // 경매 중일 때 알림 생성하고 보내기, 이름 수정 하기
    @Override
    public int registAlert(Bidding bidding, int d_status) {
        /*
         * d_status = 0 = FIRST_BID_ALERT, 첫 입찰인 경우
         * d_status = 1 =  BID_ALERT, 이전 입찰자가 있는 경우
         * d_status = 2 = END_AUCTION_ALERT, 경매 종료 후 알림
         * d_status = 3 = REIVEW_ALERT, 경매 리뷰 생성 후 알림
         */

        AlertDTO alertDto = new AlertDTO(bidding.getAuction_Id(), bidding.getAuction_name(), bidding.getConsumer_id(), d_status);

        SseEmitter farmEmitter = farmEmitters.get(bidding.getFarm_id());
        SseEmitter consumerEmitter = consumerEmitters.get(bidding.getConsumer_id());
        // SseEmitter consumerEmitter = null;

        // if (bidding.getConsumer_id() != null) consumerEmitter = consumerEmitters.get(bidding.getConsumer_id());

        alertDto = auctionMapper.registAlert(alertDto);
        
        // alert time error로 인한 코드
        alertDto.setTime(auctionMapper.getAlertTime(alertDto.getAlert_id()));
        System.out.println("---------------");
        System.out.println(alertDto.toString());

        if (d_status == BID_ALERT) {                             // 이전 입찰자가 있는 경우
            SseEmitter auctionConsumerEmitter = consumerEmitters.get(bidding.getAuction_consumer_id());
            if(auctionConsumerEmitter != null){
                // 이전 입찰자에게 알림
                System.out.println("auctionConsumerEmitter: " + alertDto.toString());
                snedEvent(auctionConsumerEmitter, alertDto, "consumer", bidding.getAuction_consumer_id());
            }
        }
        if(consumerEmitter != null){
            // 새로운 입찰자 혹은 낙찰자에게 알림
            System.out.println("consumerEmitter: " + alertDto.toString());
            snedEvent(consumerEmitter, alertDto, "consumer", bidding.getConsumer_id());
        }
        if(farmEmitter != null){
            // 농가에게 알림
            snedEvent(farmEmitter, alertDto, "farm", bidding.getFarm_id());
        }
        
        return 1;
    }

    public void snedEvent(SseEmitter emitter, AlertDTO alertDto, String checkUser, int id){
        try {
            emitter.send(SseEmitter.event().name("alert").data(alertDto));
        } catch (IOException e) {
            if (checkUser.equals("consumer")){
                consumerEmitters.remove(id);
            } else {
                farmEmitters.remove(id);
            }
            e.printStackTrace();
        }
    }

    @Override
    public int checkedAlert(int alert_id) {
        return auctionMapper.updateCheckedAlert(alert_id);
    }


    // #################################################### 검색 기능 #####################################################

    @Override
    public List<AuctionDTO> searchAuction(String ip, String checkUser, int id, String keyword, int startLimit){
        List<AuctionDTO> auctionDTOs = auctionMapper.searchAuction("%" + keyword + "%", startLimit);

        // 검색된 경매가 있다면 search_word 테이블에 추가
        if (!auctionDTOs.isEmpty()){
            log.info(auctionDTOs.toString());

            // 키워드 추가
            SearchWordDTO searchWordDTO = new SearchWordDTO();

            searchWordDTO.setIp(ip);
            searchWordDTO.setKeyword(keyword);

            if (checkUser.equals("consumer")) {
                searchWordDTO.setConsumer_id(id);
                auctionMapper.registConsumerKeyword(searchWordDTO);
            } else {
                searchWordDTO.setFarm_id(id);
                auctionMapper.registFarmKeyword(searchWordDTO);
            }
            
            // auctionMapper.registKeyword(searchWordDTO);
        }

        return auctionDTOs;
    }

    @Override
    public List<String> getPopularKeyword(){

        List<String> keywords = auctionMapper.getPopularKeyword();
        System.out.println(keywords.toString());
        return keywords;
    };


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid(){
        log.info("getRecentlyBid..........");

        return auctionMapper.getRecentlyBid();
    }

    
    // 마감된 경매 상태 업데이트 & 알림
    public void closeBidding(int auction_Id, Boolean ealryClosing){
        log.info("closeBidding..........");

        // 이미 조기 마감된 경매인 경우
        if(ealryClosing || auctionMapper.getBidStatus(auction_Id)){
            auctionMapper.updateBidStatus(auction_Id);           
            Bidding closedBidding = auctionMapper.getClosedBidding(auction_Id);
            auctionMapper.plusFarmPachiPoint(closedBidding.getFarm_id());
            auctionMapper.plusFarmAuctionCount(closedBidding.getFarm_id());
            auctionMapper.plusConsumerPachiPoint(closedBidding.getConsumer_id());
            auctionMapper.plusConsumerAuctionCount(closedBidding.getConsumer_id());
            registAlert(closedBidding, END_AUCTION_ALERT);
        }
        
    }

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    public List<Map<String, Object>> getMypageAuctionDetails(String checkUser, int id, int limit){
        return checkUser.equals("consumer") ? auctionMapper.getMypageConsumerAuctionDetails(id, limit) : auctionMapper.getMypageFarmAuctionDetails(id, limit); 
    }

    //마이페이지 나의 찜 목록 가져오기
    public List<Map<String, Object>> getWishList(int consumer_id, int limit){
        log.info("limit22---"+limit);
        log.info("consumer22---"+consumer_id);
        log.info("리스트"+auctionMapper.getWishList(consumer_id, limit));


        return auctionMapper.getWishList(consumer_id, limit);

    }

    public int consumerPachiPoint(int consumer_id){
        return auctionMapper.consumerPachiPoint(consumer_id);
    }

    public int farmPachiPoint(int farm_id){
        return auctionMapper.farmPachiPoint(farm_id);
    }

    public int consumerCountAuction(int consumer_id){
        return auctionMapper.consumerCountAuction(consumer_id);
    }

    public int farmCountAuction(int farm_id){
        return auctionMapper.farmCountAuction(farm_id);
    }
}
