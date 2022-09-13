package org.dblab.auction_backend.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.dblab.auction_backend.config.BidDeadlineTimer;
import org.dblab.auction_backend.config.ThreadControl;
import org.dblab.auction_backend.domain.AlertDTO;
import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.SearchWordDTO;
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
    private HashMap<Integer,SseEmitter> consumerEmitters = new HashMap<Integer, SseEmitter>();
    private HashMap<Integer,SseEmitter> farmEmitters = new HashMap<Integer, SseEmitter>();

    // #################################################### 경매 CURD #####################################################

    @Override
    public int registAuction(AuctionDTO auctionDTO) {
        log.info("registAuction.........." + auctionDTO);

        // 유저 이름 + 날짜, 임시로 농가 ID 사용!
        String product_img_name = auctionDTO.getFarm_id() + "," + LocalDateTime.now().toString().substring(0, 19);
        // 이미지 개수
        int numberOfImg = auctionDTO.getProductDTO().getProduct_img_file().size();
        try {
            for (int i=0; i<numberOfImg; i++){
                auctionDTO.getProductDTO().getProduct_img_file().get(i).transferTo(new File(PRODUCT_IMG_PATH + product_img_name + "(" + i + ")" + numberOfImg +".png"));
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
        
        BidClosingDTO bidClosingDTO = auctionMapper.registAuction(auctionDTO);

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
        log.info("getAuction..........");
        List<AuctionDTO> auctionDTOs = auctionMapper.getAuction(limit);
        log.info(auctionDTOs.toString());

        return auctionDTOs;
    }

    @Override
    public int updateAuction(AuctionDTO auctionDTO) {
        log.info("updateAuction..........");
        return auctionMapper.updateAuction(auctionDTO);
    }

    @Override
    public int deleteAuction(int auction_Id) {
        log.info("deleteAuction..........");
        return auctionMapper.deleteAuction(auction_Id);
    }

    @Override
    public int updateBidding(Bidding bidding) {

        int mapperResult = auctionMapper.updateBidding(bidding);
        int d_status = 1;
        int alertResult = 0;

        if(mapperResult == 1) {
            if (bidding.getAuction_consumer_id() != null){
                d_status = 2;
            }
            alertResult = registAlert(bidding, d_status);
        }
        return alertResult;
    }


    // #################################################### 상품 CURD #####################################################

    @Override
    public List<ProductDTO> getProduct() {
        log.info("getProduct..........");
        return auctionMapper.getProduct();
    }

    @Override
    public int updateProduct(ProductDTO productDTO) {

        // 이미지 변경 시 처리 코드 
        return auctionMapper.updateProduct(productDTO);
    }

    @Override
    public int deleteProduct(int product_id) {

        return auctionMapper.deleteProduct(product_id);
    }


    // #################################################### 리뷰 CRUD #####################################################

    @Override
    public int registAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("registAuctionReview.........." + auctionReview.toString());
        return auctionMapper.registAuctionReview(auctionReview);
    }

    @Override
    public List<Map<String, Object>> getAuctionReview(String checkUser, int id) {
        log.info("getAuctionReview..........");

        if (checkUser.equals("consumer")) {
            return auctionMapper.getConsumerAuctionReview(id);
        } else {
            return auctionMapper.getFarmAuctionReview(id);
        }
    }

    @Override
    public int updateAuctionReview(AuctionReviewDTO auctionReview) {
        log.info("updateAuctionReview..........");

        return auctionMapper.updateAuctionReview(auctionReview);
    }

    @Override
    public int deleteAuctionReview(int auction_Id) {
        log.info("deleteAuctionReview..........");

        return auctionMapper.deleteAuctionReview(auction_Id);
    }

    
    // #################################################### 알림 #####################################################

    @Override
    public SseEmitter registEmitter(String checkUser, int id, SseEmitter emitter) {
        
        try {
            System.out.println("id: " + id);
            emitter.send(SseEmitter.event().name(""+id));

            // checkUser와 id를 이용해서 Alert 데이터 가져와서 emitter에 저장하기
            if (checkUser.equals("consumer")){
                List<AlertDTO> alertDTOs = auctionMapper.getConsumerAlert(id);

                emitter.send(SseEmitter.event().name("init").data(alertDTOs));
                emitter.onCompletion(() -> consumerEmitters.remove(id));
                consumerEmitters.put(id, emitter);
            } else {
                // List<AlertDTO> alertDTOs = auctionMapper.getFarmAlert(id);
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
         * d_status = 1, 첫 입찰인 경우
         * d_status = 2, 이전 입찰자가 있는 경우
         * d_status = 3, ???
         * d_status = 4, 경매 종료 후 알림
         */
        AlertDTO alertDto = new AlertDTO(bidding.getAuction_Id(), bidding.getAuction_name(), bidding.getConsumer_id(), d_status);

        SseEmitter farmEmitter = farmEmitters.get(bidding.getFarm_id());
        SseEmitter consumerEmitter = null;

        if (bidding.getConsumer_id() != null) consumerEmitter = consumerEmitters.get(bidding.getConsumer_id());

        alertDto = auctionMapper.registAlert(alertDto);

        if (d_status == 2) {                             // 이전 입찰자가 있는 경우
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
    public List<AuctionDTO> searchAuction(String ip, String checkUser, int id, String keyword){
        List<AuctionDTO> auctionDTOs = auctionMapper.searchAuction("%" + keyword + "%");

        // 검색된 경매가 있다면 search_word 테이블에 추가
        if (!auctionDTOs.isEmpty()){
            log.info(auctionDTOs.toString());

            // 키워드 중복 추가 방지를 위한 ip, id 확인 코드

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
    public void closeBidding(int auction_Id){
        log.info("closeBidding..........");

        auctionMapper.updateBidStatus(auction_Id);           
        Bidding closedBidding = auctionMapper.getClosedBidding(auction_Id);

        registAlert(closedBidding, 4);
    }

}
