package org.dblab.auction_backend.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.dblab.auction_backend.domain.AlertDTO;
import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.SearchWordDTO;
import org.dblab.auction_backend.domain.WishDTO;

public interface AuctionMapper {

    // #################################################### 경매 CURD #####################################################

    public BidClosingDTO registAuction(AuctionDTO auction);
    
    public List<AuctionDTO> getAuction(@Param("startLimit") int startLimit);

    public int updateAuction(AuctionDTO auction);

    public int deleteAuction(@Param("auction_Id") int auction_Id);

    public int updateBidding(Bidding bidding);

    public int updateMaxPriceBidding(Bidding bidding);

    public AuctionDTO auctionInfo(@Param("auction_Id") int auction_Id);
    // #################################################### 상품 CURD #####################################################

    public int registProduct(ProductDTO product);

    public int updateProduct(ProductDTO productDTO);

    public int deleteProduct(@Param("product_id") int product_id);

    public int checkWish(@Param("auction_id") int auction_id, @Param("consumer_id") int consumer_id);

    public int registWish(@Param("auction_id") int auction_id, @Param("consumer_id") int consumer_id);
    
    public int deleteWish(@Param("auction_id") int auction_id, @Param("consumer_id") int consumer_id);


    // #################################################### 리뷰 CRUD #####################################################

    public int registConsumerAuctionReview(AuctionReviewDTO auctionReview);

    public int registFarmAuctionReview(AuctionReviewDTO auctionReview);

    public List<Map<String, Object>> getConsumerAuctionReview(@Param("consumer_id") int consumer_id);

    public List<Map<String, Object>> getFarmAuctionReview(@Param("farm_id") int farm_id);

    public int updateConsumerAuctionReview(AuctionReviewDTO auctionReview);

    public int updateFarmAuctionReview(AuctionReviewDTO auctionReview);
    //{"auction_Id" : 1, "consumer_id": 5}

    //public int deleteAuctionReview(@Param("auction_Id") int auction_Id);

    public int minusFarmPachiPoint(@Param("farm_id") int farm_id);

    public int minusConsumerPachiPoint(@Param("consumer_id") int consumer_id);

    public int deleteAuctionReview(AuctionReviewDTO auctionReview);

    
    // #################################################### 알림 CRUD #####################################################

    public AlertDTO registAlert(AlertDTO alertDTO);

    public String getAlertTime(@Param("alert_id") int alert_id);

    public List<Map<String, Object>> getConsumerAlert(@Param("consumer_id") int consumer_id);
    
    public List<Map<String, Object>> getFarmAlert(@Param("farm_id") int farm_id);

    public int updateCheckedAlert(@Param("alert_id") int alert_id);

    public int deleteAlert(@Param("alert_id") int alert_id);


    // #################################################### 검색 기능 #####################################################

    public List<AuctionDTO> searchAuction(@Param("keyword") String keyword , @Param("startLimit") int startLimit);

    public int registKeyword(SearchWordDTO searchWordDTO);

    public int registConsumerKeyword(SearchWordDTO searchWordDTO);

    public int registFarmKeyword(SearchWordDTO searchWordDTO);

    public List<String> getPopularKeyword();


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid();

    public Boolean getBidStatus(@Param("auction_Id") int auction_Id);

    public int updateBidStatus(@Param("auction_Id") int auction_Id);

    public Bidding getClosedBidding(@Param("auction_Id") int auction_Id);

    public int plusFarmPachiPoint(@Param("farm_id") int farm_id);

    public int plusConsumerPachiPoint(@Param("consumer_id") int consumer_id);

    public int plusConsumerAuctionCount(@Param("consumer_id") int consumer_id);

    public int plusFarmAuctionCount(@Param("farm_id") int farm_id);

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    
    public List<Map<String, Object>> getMypageConsumerAuctionDetails(@Param("consumer_id") int consumer_id, @Param("startLimit") int startLimit);

    public List<Map<String, Object>> getMypageFarmAuctionDetails(@Param("farm_id") int farm_id, @Param("startLimit") int startLimit);

    public List<Map<String, Object>> getWishList(@Param("consumer_id") int consumer_id, @Param("startLimit") int startLimit);

    public int consumerPachiPoint(@Param("consumer_id") int consumer_id);

    public int farmPachiPoint(@Param("farm_id") int farm_id);

    public int farmCountAuction(@Param("farm_id") int farm_id);
    
    public int consumerCountAuction(@Param("consumer_id") int consumer_id);
}