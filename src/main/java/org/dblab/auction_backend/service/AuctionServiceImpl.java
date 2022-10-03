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
import org.dblab.auction_backend.domain.OrderDTO;
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
    private Integer FIRST_BID_ALERT = 0;    // 열거형 
    private Integer BID_ALERT = 1;
    private Integer EARLY_CLOSING_AUCTION_ALERT = 2;
    private Integer END_AUCTION_ALERT = 3;
    private Integer CONSUMER_REIVEW_ALERT = 4;
    private Integer FARM_REIVEW_ALERT = 5;
    private Integer PAYMENT_ALERT = 6;
    private Integer ALERT_INIT_START_LIMIT = 0;

    // #################################################### 경매 CURD #####################################################

    @Override
    public Integer registAuction(AuctionDTO auctionDTO) {
        log.info("registAuction.........." + auctionDTO);

        // 유저 이름 + 날짜, 임시로 농가 ID 사용!
        String product_img_name = auctionDTO.getFarm_id() + "," + LocalDateTime.now().toString().substring(0, 19);
        // 이미지 개수
        Integer numberOfImg = auctionDTO.getProductDTO().getProduct_img_files().size();
        log.info("numberOfImg: " + numberOfImg);
        try {
            for (Integer i=0; i<numberOfImg; i++){
                auctionDTO.getProductDTO().getProduct_img_files().get(i).transferTo(new File(PRODUCT_IMG_PATH + product_img_name + "(" + i + ")" + numberOfImg +".png"));
            }
            log.info(product_img_name + " 파일 저장 완료");
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
            log.info("bidClosingDTO.getAuction_Id() != null");
            ThreadControl.checkBidding(bidClosingDTO.getAuction_Id(), bidClosingDTO.getDeadline_date());
        }
        return 1;
    }

    @Override
    public List<AuctionDTO> getAuction(Integer limit) {
        log.info("getAuction.........." + limit);
        List<AuctionDTO> auctionDTOs = auctionMapper.getAuction(limit);
        log.info(auctionDTOs.toString());

        return auctionDTOs;
    }

    @Override
    public Integer deleteAuction(Integer auction_Id, Integer product_id, String product_img_name) {
        log.info("deleteAuction..........");
        auctionMapper.deleteAuctionWish(auction_Id);
        auctionMapper.deleteAlert(auction_Id);
        // auctionMapper.deleteAuctionReview(auction_Id);       // 입찰하지 않은 경매만 리뷰 삭제 불필요..
        auctionMapper.deleteAuction(auction_Id);
        deleteProductImages(product_img_name);
        return auctionMapper.deleteProduct(product_id);
    }

    @Override
    public Integer updateBidding(Bidding bidding) {
        log.info(bidding.toString());
        if(bidding.getIsMaxPrice() == 1){
            if(auctionMapper.updateMaxPriceBidding(bidding) == 0) return 0;                 // 경매가 이전에 삭제된 경우
            return earlyCloseBidding(bidding);
        } 
        if(auctionMapper.updateBidding(bidding)==0) return 0;                               // 경매가 이전에 삭제된 경우

        Integer d_status = FIRST_BID_ALERT;
        Integer alertResult = 0;

        if (bidding.getAuction_consumer_id() != null){
            d_status = BID_ALERT;
        }
        alertResult = registAlert(bidding, d_status);
        return alertResult;
    }

    public AuctionDTO auctionInfo(Integer auction_Id) {
        AuctionDTO tmp = auctionMapper.auctionInfo(auction_Id);
        log.info(tmp.toString());
        return tmp;
    }

    // #################################################### 상품 U #####################################################
    
    @Override
    public Integer updateProduct(ProductDTO productDTO) {
        log.info("updateProduct..........");

        deleteProductImages(productDTO.getProduct_img_name());

        Integer numberOfProductImg=productDTO.getProduct_img_files().size();
        String p_img_name = productDTO.getProduct_id()+"."+LocalDateTime.now().toString().substring(0,19);
        log.info(PRODUCT_IMG_PATH + productDTO.getProduct_img_name());
        try {
            for(Integer i=0; i< numberOfProductImg;i++){
                productDTO.getProduct_img_files().get(i).transferTo(new File(PRODUCT_IMG_PATH + p_img_name + "("+i+")"+ numberOfProductImg+ ".png"));
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productDTO.setProduct_img_name(p_img_name + "(0)" + numberOfProductImg);
        log.info(productDTO.getProduct_img_name() + " 새로운 상품 이미지 저장 완료");

        return auctionMapper.updateProduct(productDTO);
    }

    // 상품 이미지 삭제 코드
    public void deleteProductImages(String product_img_name){
        if(product_img_name != null){
            try {
                Integer product_img_name_length = Integer.parseInt(product_img_name.substring(product_img_name.indexOf(")")+1));
                product_img_name = product_img_name.substring(0, product_img_name.indexOf("("));

                for(Integer i = 0; i<product_img_name_length;i++){
                    File productImageFile = new File(PRODUCT_IMG_PATH+product_img_name+"("+i+")"+product_img_name_length+".png");
                    if(productImageFile.exists()){
                        if(productImageFile.delete()){
                            log.info(product_img_name + "상품이미지 삭제 성공!!!!!~~");
                        }else{
                            log.info(product_img_name + "상품이미지 삭제 실패!!!!!~~");
                        }
                    }else{
                        log.info("상품이미지 파일이 없습니다...");
                    }
                }
            } catch (Exception e) {
                log.info(e.toString());
                log.info("잘못된 이미지 이름입니다!");
            }
        }
    }

    @Override
    public Integer registWish(WishDTO wishDTO){
        log.info("registWish....." + wishDTO.toString());
        if(auctionMapper.checkWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id())==0){
            return auctionMapper.registWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id());
        } else {
            return auctionMapper.deleteWish(wishDTO.getAuction_id(), wishDTO.getConsumer_id());
        }
    }
    @Override
    public Integer checkWish(Integer auction_id, Integer consumer_id){
        log.info(auction_id+" "+consumer_id);

        return auctionMapper.checkWish(auction_id, consumer_id);
    }

    @Override
    public Integer deleteWish(Integer auction_id, Integer consumer_id){
        return auctionMapper.deleteWish(auction_id, consumer_id);
    }

    // #################################################### 리뷰 CRUD #####################################################

    @Override
    public Integer registAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("registAuctionReview.........." + auctionReview.toString());
        Integer d_status = CONSUMER_REIVEW_ALERT;
        if(auctionReview.getCheckUser().equals("consumer")){
            auctionMapper.plusConsumerPachiPoint(auctionReview.getConsumer_id());
            // 리뷰 이미지 저장
            if(auctionReview.getReview_img_file() != null){
                auctionReview.setReview_img_name(auctionReview.getAuction_Id() + "_" + LocalDateTime.now().toString().substring(0, 19));
                try {
                    auctionReview.getReview_img_file().transferTo(new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + auctionReview.getReview_img_name() + ".png"));
                    log.info(auctionReview.getAuction_name() + " 새로운 리뷰 이미지 저장 완료");
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            auctionMapper.registConsumerAuctionReview(auctionReview);
        } else {
            d_status = FARM_REIVEW_ALERT;
            auctionMapper.plusFarmPachiPoint(auctionReview.getFarm_id());
            auctionMapper.registFarmAuctionReview(auctionReview);
            log.info("registAuctionReview.........." + auctionReview.toString());
        }

        return registAlert(new Bidding(auctionReview.getAuction_Id(), auctionReview.getAuction_name(), auctionReview.getFarm_id(),
                                        auctionReview.getConsumer_id(), auctionReview.getProduct_img_name(), 
                                        auctionReview.getF_farm_name(), auctionReview.getC_name()), d_status);
    }

    @Override
    public List<Map<String, Object>> getAuctionReview(String checkUser, Integer id) {
        log.info("getAuctionReview.........." + checkUser + " " +  id );
        return checkUser.equals("consumer") ? auctionMapper.getConsumerAuctionReview(id) : auctionMapper.getFarmAuctionReview(id);
    }

    public void deleteAuctionReviewImage(String review_img_name){
        // 이전 리뷰 이미지 삭제
        File auctionReviewImageFile = new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + review_img_name+ ".png");

        if (auctionReviewImageFile.exists()){
            if (auctionReviewImageFile.delete()){
                log.info(review_img_name + " 리뷰 이미지 삭제 성공");
            } else {
                log.info(review_img_name + " 리뷰 이미지 삭제 실패...");
            }
        } else{
    		log.info("리뷰 이미지 파일이 존재하지 않습니다.");
    	}
    }

    @Override
    public Integer updateAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("updateAuctionReview.........." + auctionReview.toString());
        
        if(auctionReview.getCheckUser().equals("consumer")){
            deleteAuctionReviewImage(auctionReview.getReview_img_name());

            if(auctionReview.getReview_img_file() != null){
                auctionReview.setReview_img_name(auctionReview.getAuction_Id() + "_" + LocalDateTime.now().toString().substring(0, 19));
                try{
                    auctionReview.getReview_img_file().transferTo(new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH + auctionReview.getReview_img_name()+".png"));
                    log.info(auctionReview.getReview_img_name() + " 새로운 리뷰 이미지 저장 완료");

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return auctionMapper.updateConsumerAuctionReview(auctionReview);
        } else {
            log.info("updateAuctionReview.......                ..." + auctionReview.toString());
            log.info("auctionReview Id" + auctionReview.getAuction_Id() + "   " + auctionReview.getFarm_review());
            auctionMapper.updateFarmAuctionReview(auctionReview.getAuction_Id(), auctionReview.getFarm_review());
            return registAlert(new Bidding(auctionReview.getAuction_Id(), auctionReview.getAuction_name(), auctionReview.getFarm_id(),
                                        auctionReview.getConsumer_id(), auctionReview.getProduct_img_name(), 
                                        auctionReview.getF_farm_name(), auctionReview.getC_name()), FARM_REIVEW_ALERT);
        }
        
        

        //return auctionReview.getCheckUser().equals("consumer") ? auctionMapper.updateConsumerAuctionReview(auctionReview) : auctionMapper.updateFarmAuctionReview(auctionReview);
    }

    @Override
    public Integer deleteAuctionReview(Integer auction_Id, Integer consumer_id, String review_img_name) {
        log.info("deleteAuctionReview..........");
        deleteAuctionReviewImage(review_img_name);

        auctionMapper.minusConsumerPachiPoint(auction_Id);
        return auctionMapper.deleteAuctionReview(auction_Id);
    }

    @Override
    public List<Map<String, Object>> getProductInfo(Integer product_id) {
        log.info("getProductInfo");
        return auctionMapper.getProductInfo(product_id);
    }

    
    // #################################################### 알림 #####################################################

    @Override
    public SseEmitter registEmitter(String checkUser, Integer id, SseEmitter emitter) {
        
        try {
            log.info("id: " + id);
            emitter.send(SseEmitter.event().name(""+id));

            // checkUser와 id를 이용해서 Alert 데이터 가져와서 emitter에 저장하기
            if (checkUser.equals("consumer")){
                List<Map<String, Object>> alertDTOs = auctionMapper.getConsumerAlert(id, ALERT_INIT_START_LIMIT);
                if(consumerEmitters.get(id) != null) consumerEmitters.remove(id);                    // 존재하면 삭제 후 다시 만들기
                // emitter.onCompletion(() -> consumerEmitters.remove(id));
                emitter.send(SseEmitter.event().name("init").data(alertDTOs));
                
                consumerEmitters.put(id, emitter);
            } else {
                List<Map<String, Object>> alertDTOs = auctionMapper.getFarmAlert(id, ALERT_INIT_START_LIMIT);
                if(farmEmitters.get(id) != null) farmEmitters.remove(id); 
                emitter.send(SseEmitter.event().name("init").data(alertDTOs));
                // emitter.onCompletion(() -> farmEmitters.remove(id));
                farmEmitters.put(id, emitter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emitter;
    }
    
    // 경매 중일 때 알림 생성하고 보내기, 이름 수정 하기
    @Override
    public Integer registAlert(Bidding bidding, Integer d_status) {
        /*
         * d_status = 0 = FIRST_BID_ALERT, 첫 입찰인 경우
         * d_status = 1 = BID_ALERT, 이전 입찰자가 있는 경우
         * d_status = 2 = EARLY_CLOSING_AUCTION_ALERT, 경매 조기 마감 후 알림
         * d_status = 3 = END_AUCTION_ALERT, 경매 정시 마감 후 알림
         * d_status = 4 = CONSUMER_REIVEW_ALERT, 경매 종료 후 알림
         * d_status = 5 = FARM_REIVEW_ALERT, 경매 리뷰 생성 후 알림
         */
        log.info("registAlert.........." + bidding.toString());
        AlertDTO alertDto = new AlertDTO(bidding.getAuction_Id(), bidding.getAuction_name(), bidding.getAuction_consumer_id(), bidding.getConsumer_id(), 
                                        bidding.getFarm_id(), d_status, bidding.getProduct_img_name(), bidding.getF_farm_name(), bidding.getC_name());

        SseEmitter farmEmitter = farmEmitters.get(bidding.getFarm_id());
        SseEmitter consumerEmitter = consumerEmitters.get(bidding.getConsumer_id());
        // SseEmitter consumerEmitter = null;
        log.info("registAlert.........." + alertDto.toString());
        // log.info("registAlert.........." + farmEmitter.toString());
        // log.info("registAlert.........." + consumerEmitter.toString());
        // if (bidding.getConsumer_id() != null) consumerEmitter = consumerEmitters.get(bidding.getConsumer_id());

        alertDto.setAlert_id(auctionMapper.registAlert(alertDto));

        // alert time error로 인한 코드
        alertDto.setTime(auctionMapper.getAlertTime(alertDto.getAlert_id()));
        log.info("---------------");
        log.info(alertDto.toString());

        if (bidding.getAuction_consumer_id() != null) {                             // 이전 입찰자가 있는 경우
            SseEmitter auctionConsumerEmitter = consumerEmitters.get(bidding.getAuction_consumer_id());
            if(auctionConsumerEmitter != null){
                // 이전 입찰자에게 알림
                log.info("auctionConsumerEmitter: " + alertDto.toString());
                snedEvent(auctionConsumerEmitter, alertDto, "consumer", bidding.getAuction_consumer_id());
            }
        }
        if(consumerEmitter != null){
            // 새로운 입찰자 혹은 낙찰자에게 알림
            log.info("consumerEmitter: " + alertDto.toString());
            snedEvent(consumerEmitter, alertDto, "consumer", bidding.getConsumer_id());
        }
        if(farmEmitter != null){
            // 농가에게 알림
            snedEvent(farmEmitter, alertDto, "farm", bidding.getFarm_id());
        }
        
        return 1;
    }

    public void snedEvent(SseEmitter emitter, AlertDTO alertDto, String checkUser, Integer id){
        try {
            emitter.send(SseEmitter.event().name("alert").data(alertDto));
        } catch (IOException e) {
            if (checkUser.equals("consumer")){
                log.info("consumerEmitters.remove " + id);
                consumerEmitters.remove(id);
            } else {
                log.info("farmEmitters.remove " + id);
                farmEmitters.remove(id);
            }
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getAlert(String checkUser, Integer id, Integer startLimit){
        log.info("getAlert.....");
        return checkUser.equals("consumer") ? auctionMapper.getConsumerAlert(id, startLimit) : auctionMapper.getFarmAlert(id, startLimit);
    }

    @Override
    public Integer checkedAlert(Integer alert_id) {
        return auctionMapper.updateCheckedAlert(alert_id);
    }


    // #################################################### 검색 기능 #####################################################

    @Override
    public List<AuctionDTO> searchAuction(String ip, String checkUser, Integer id, String keyword, Integer startLimit){
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
        log.info(keywords.toString());
        return keywords;
    };


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid(){
        log.info("getRecentlyBid..........");

        return auctionMapper.getRecentlyBid();
    }

    // 경매 조기 마감
    public Integer earlyCloseBidding(Bidding bidding){ 
        log.info("earlyCloseBidding..........");
        plusPoint(bidding);
        return registAlert(bidding, EARLY_CLOSING_AUCTION_ALERT);
    }
    
    // 경매 정시 마감, 마감된 경매 상태 업데이트 & 알림
    public void closeBidding(Integer auction_Id){
        log.info("closeBidding.........." + auction_Id);

        Integer test = auctionMapper.getBidStatus(auction_Id);
        log.info("closeBidding.......... test: " + test);
        // 이미 조기 마감된 경매인 경우 예외 처리
        if(test == 1){
            auctionMapper.updateBidStatus(auction_Id);           
            Map<String, Object> closedBidding = auctionMapper.getClosedBidding(auction_Id);
            log.info("closeBidding.......... closedBidding: " + closedBidding.toString());
            Integer consumer_id = null;
            if(closedBidding.get("consumer_id") != null){
                log.info("closeBidding.......... consumer_id: ");
                consumer_id = (Integer) closedBidding.get("consumer_id");
                closedBidding.put("consumer_id", "0");
                log.info("closeBidding.......... closedBidding: " + consumer_id);
            }
            for( String key : closedBidding.keySet()){
                log.info(key + "   " + closedBidding.get(key).getClass().getName());
                log.info(key + "   " + closedBidding.get(key).toString());
            }
            Bidding bidding = new Bidding((Integer) closedBidding.get("auction_Id"), String.valueOf(closedBidding.get("auction_name")), (Integer) closedBidding.get("farm_id"), 
                                                    consumer_id, String.valueOf(closedBidding.get("product_img_name")), String.valueOf((closedBidding.get("f_farm_name"))), String.valueOf(closedBidding.get("c_name")));
            log.info("closeBidding.........." + bidding.toString());
            plusPoint(bidding);
            registAlert(bidding, END_AUCTION_ALERT);
        }
    }

    // 파치 포인트와 경매 횟수 추가
    public void plusPoint(Bidding bidding){
        log.info("plusPoint.........." + bidding.toString());
        if(bidding.getConsumer_id() != null){
            auctionMapper.plusConsumerPachiPoint(bidding.getConsumer_id());
            auctionMapper.plusConsumerAuctionCount(bidding.getConsumer_id());  
            auctionMapper.plusFarmPachiPoint(bidding.getFarm_id());
            auctionMapper.plusFarmAuctionCount(bidding.getFarm_id()); 
        }
    }

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    public List<Map<String, Object>> getMypageAuctionDetails(String checkUser, Integer id, Integer limit){
        return checkUser.equals("consumer") ? auctionMapper.getMypageConsumerAuctionDetails(id, limit) : auctionMapper.getMypageFarmAuctionDetails(id, limit); 
    }

    //마이페이지 나의 찜 목록 가져오기
    public List<Map<String, Object>> getWishList(Integer consumer_id, Integer limit){
        log.info("limit22---"+limit);
        log.info("consumer22---"+consumer_id);
        log.info("리스트"+auctionMapper.getWishList(consumer_id, limit));


        return auctionMapper.getWishList(consumer_id, limit);

    }

    public Integer consumerPachiPoint(Integer consumer_id){
        return auctionMapper.consumerPachiPoint(consumer_id);
    }

    public Integer farmPachiPoint(Integer farm_id){
        return auctionMapper.farmPachiPoint(farm_id);
    }

    public Integer consumerCountAuction(Integer consumer_id){
        return auctionMapper.consumerCountAuction(consumer_id);
    }

    public Integer farmCountAuction(Integer farm_id){
        return auctionMapper.farmCountAuction(farm_id);
    }

    // #################################################### 결제 및 배송 #####################################################
    public OrderDTO registOrder(OrderDTO orderDTO){
        auctionMapper.updatePaymentStatus(orderDTO.getPaymentDTO().getAuction_Id());
        orderDTO.setPaymentDTO(auctionMapper.registPayment(orderDTO.getPaymentDTO()));
        log.info("-----" + orderDTO.getPaymentDTO().toString());
        orderDTO.getDeliveryDTO().setPayment_id(orderDTO.getPaymentDTO().getPayment_id());
        log.info("-----" + orderDTO.getDeliveryDTO().toString());
        orderDTO.setDeliveryDTO(auctionMapper.registDelivery(orderDTO.getDeliveryDTO()));
        log.info("-----" + orderDTO.getDeliveryDTO().toString());

        registAlert(orderDTO.getBidding(), PAYMENT_ALERT);

        log.info("registAlert 이후:  " + orderDTO.toString());
        return orderDTO;
    }

    // public OrderDTO getOrder(Integer auction_Id){
    //     log.info("getOrder......" + auction_Id);
    //     OrderDTO orderDTO = auctionMapper.getOrderDTO(auction_Id);
    //     log.info(orderDTO.toString());
    //     return orderDTO
    // }

}
