package org.dblab.auction_backend.service;

import java.util.List;
import java.util.Map;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.ProductDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AuctionService {

    // #################################################### 경매 CURD #####################################################
    
    public int registAuction(AuctionDTO auctionDTO);
    
    public List<AuctionDTO> getAuction(int limit);

    public int updateAuction(AuctionDTO auctionDTO);

    public int deleteAuction(int auction_Id);

    public int updateBidding(Bidding bidding);


    // #################################################### 상품 URD #####################################################

    public List<ProductDTO> getProduct();

    public int updateProduct(ProductDTO productDTO);

    public int deleteProduct(int product_id);

    // #################################################### 리뷰 CRUD #####################################################

    public int registAuctionReview(AuctionReviewDTO auctionReview);

    public List<Map<String, Object>> getAuctionReview(String checkUser, int id);

    public int updateAuctionReview(AuctionReviewDTO auctionReview);

    public int deleteAuctionReview(int auction_Id);


    // #################################################### 알림 #####################################################

    public SseEmitter registEmitter(String checkUser, int id, SseEmitter emitter);

    public int registAlert(Bidding bidding, int d_status);

    public int checkedAlert(int alert_id);


    // #################################################### 검색 기능 #####################################################

    public List<AuctionDTO> searchAuction(String ip, String checkUser, int id, String keyword);

    public List<String> getPopularKeyword();


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid();

    public void closeBidding(int auction_Id);
    
}
