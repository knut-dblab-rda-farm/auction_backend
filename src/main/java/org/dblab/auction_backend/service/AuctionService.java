package org.dblab.auction_backend.service;

import java.util.List;
import java.util.Map;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.OrderDTO;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.WishDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AuctionService {

    // #################################################### 경매 CURD #####################################################
    
    public Integer registAuction(AuctionDTO auctionDTO);
    
    public List<AuctionDTO> getAuction(Integer limit);

    public Integer deleteAuction(Integer auction_Id, Integer product_id, String product_img_name);

    public Integer updateBidding(Bidding bidding);

    public AuctionDTO auctionInfo(Integer auction_Id);


    // #################################################### 상품 U #####################################################

    public Integer updateProduct(ProductDTO productDTO);

    public void deleteProductImages(String product_img_name);

    // public Integer registWish(Integer auction_id, Integer consumer_id);

    public Integer registWish(WishDTO wishDTO);

    public Integer deleteWish(Integer auction_id, Integer consumer_id);

    public Integer checkWish(Integer auction_id, Integer consumer_id);

    // #################################################### 리뷰 CRUD #####################################################

    public Integer registAuctionReview(AuctionReviewDTO auctionReview);

    public List<Map<String, Object>> getAuctionReview(String checkUser, Integer id);

    public Integer updateAuctionReview(AuctionReviewDTO auctionReview);

    public Integer deleteAuctionReview(Integer auction_Id, Integer consumer_id, String review_img_name);

    public List<Map<String, Object>> getProductInfo(Integer product_id);

    // #################################################### 알림 #####################################################

    public SseEmitter registEmitter(String checkUser, Integer id, SseEmitter emitter);

    public Integer registAlert(Bidding bidding, Integer d_status);

    public List<Map<String, Object>> getAlert(String checkUser, Integer id, Integer startLimit);

    public Integer checkedAlert(Integer alert_id);


    // #################################################### 검색 기능 #####################################################

    public List<AuctionDTO> searchAuction(String ip, String checkUser, Integer id, String keyword, Integer startLimit);

    public List<String> getPopularKeyword();


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid();

    public Integer earlyCloseBidding(Bidding bidding);

    public void closeBidding(Integer auction_Id);

    public void plusPoint(Bidding bidding);

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    public List<Map<String, Object>> getMypageAuctionDetails(String checkUser, Integer id, Integer limit);

    //마이페이지 나의 찜 목록 가져오기
    public List<Map<String, Object>> getWishList(Integer consumer_id, Integer limit);

    public Integer consumerPachiPoint(Integer consumer_id);

    public Integer farmPachiPoint(Integer farm_id);
    
    public Integer farmCountAuction(Integer farm_id);

    public Integer consumerCountAuction(Integer consumer_id);

    // #################################################### 결제 및 배송 #####################################################

    public OrderDTO registOrder(OrderDTO orderDTO);

    // public OrderDTO getOrder(Integer auction_Id)
}
