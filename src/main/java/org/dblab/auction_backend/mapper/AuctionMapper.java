package org.dblab.auction_backend.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.dblab.auction_backend.domain.AlertDTO;
import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.BidClosingDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.domain.DeliveryDTO;
import org.dblab.auction_backend.domain.OrderDTO;
import org.dblab.auction_backend.domain.PaymentDTO;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.SearchWordDTO;
import org.dblab.auction_backend.domain.WishDTO;

public interface AuctionMapper {

    // #################################################### 경매 CURD #####################################################

    public BidClosingDTO registAuction(AuctionDTO auction);
    
    public List<AuctionDTO> getAuction(@Param("startLimit") Integer startLimit);

    public Integer updateAuction(AuctionDTO auction);

    public Integer deleteAuction(@Param("auction_Id") Integer auction_Id);

    public Integer updateBidding(Bidding bidding);

    public Integer updateMaxPriceBidding(Bidding bidding);

    public AuctionDTO auctionInfo(@Param("auction_Id") Integer auction_Id);
    // #################################################### 상품 CURD #####################################################

    public Integer registProduct(ProductDTO product);

    public Integer updateProduct(ProductDTO productDTO);

    public Integer deleteProduct(@Param("product_id") Integer product_id);

    public Integer checkWish(@Param("auction_id") Integer auction_id, @Param("consumer_id") Integer consumer_id);

    public Integer registWish(@Param("auction_id") Integer auction_id, @Param("consumer_id") Integer consumer_id);
    
    public Integer deleteWish(@Param("auction_id") Integer auction_id, @Param("consumer_id") Integer consumer_id);

    public Integer deleteAuctionWish(@Param("auction_id") Integer auction_id);

    // #################################################### 리뷰 CRUD #####################################################

    public Integer registConsumerAuctionReview(AuctionReviewDTO auctionReview);

    public Integer registFarmAuctionReview(AuctionReviewDTO auctionReview);

    public List<Map<String, Object>> getConsumerAuctionReview(@Param("consumer_id") Integer consumer_id);

    public List<Map<String, Object>> getFarmAuctionReview(@Param("farm_id") Integer farm_id);

    public Integer updateConsumerAuctionReview(AuctionReviewDTO auctionReview);

    public Integer updateFarmAuctionReview(@Param("auction_Id") Integer auction_Id, @Param("farm_review") String farm_review);
    //{"auction_Id" : 1, "consumer_id": 5}

    //public Integer deleteAuctionReview(@Param("auction_Id") Integer auction_Id);

    public Integer minusFarmPachiPoint(@Param("farm_id") Integer farm_id);

    public Integer minusConsumerPachiPoint(@Param("consumer_id") Integer consumer_id);

    public Integer deleteAuctionReview(@Param("auction_Id") Integer auction_Id);

    public List<Map<String, Object>> getProductInfo(@Param("product_id") Integer product_id);

    
    // #################################################### 알림 CRUD #####################################################

    public Integer registAlert(AlertDTO alertDTO);

    public String getAlertTime(@Param("alert_id") Integer alert_id);

    public List<Map<String, Object>> getConsumerAlert(@Param("consumer_id") Integer consumer_id, @Param("startLimit") Integer startLimit);
    
    public List<Map<String, Object>> getFarmAlert(@Param("farm_id") Integer farm_id, @Param("startLimit") Integer startLimit);

    public Integer updateCheckedAlert(@Param("alert_id") Integer alert_id);

    public Integer deleteAlert(@Param("auction_Id") Integer auction_Id);


    // #################################################### 검색 기능 #####################################################

    public List<AuctionDTO> searchAuction(@Param("keyword") String keyword , @Param("startLimit") Integer startLimit);

    public Integer registKeyword(SearchWordDTO searchWordDTO);

    public Integer registConsumerKeyword(SearchWordDTO searchWordDTO);

    public Integer registFarmKeyword(SearchWordDTO searchWordDTO);

    public List<String> getPopularKeyword();


    // ############################################## 경매 종료 ####################################################

    public List<BidClosingDTO> getRecentlyBid();

    public Integer getBidStatus(@Param("auction_Id") Integer auction_Id);

    public Integer updateBidStatus(@Param("auction_Id") Integer auction_Id);

    public Map<String, Object> getClosedBidding(@Param("auction_Id") Integer auction_Id);

    public Integer plusFarmPachiPoint(@Param("farm_id") Integer farm_id);

    public Integer plusConsumerPachiPoint(@Param("consumer_id") Integer consumer_id);

    public Integer plusConsumerAuctionCount(@Param("consumer_id") Integer consumer_id);

    public Integer plusFarmAuctionCount(@Param("farm_id") Integer farm_id);

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    
    public List<Map<String, Object>> getMypageConsumerAuctionDetails(@Param("consumer_id") Integer consumer_id, @Param("startLimit") Integer startLimit);

    public List<Map<String, Object>> getMypageFarmAuctionDetails(@Param("farm_id") Integer farm_id, @Param("startLimit") Integer startLimit);

    public List<Map<String, Object>> getWishList(@Param("consumer_id") Integer consumer_id, @Param("startLimit") Integer startLimit);

    public Integer consumerPachiPoint(@Param("consumer_id") Integer consumer_id);

    public Integer farmPachiPoint(@Param("farm_id") Integer farm_id);

    public Integer farmCountAuction(@Param("farm_id") Integer farm_id);
    
    public Integer consumerCountAuction(@Param("consumer_id") Integer consumer_id);

    // #################################################### 결제 및 배송 #####################################################
    public Integer updatePaymentStatus(@Param("auction_Id") Integer auction_Id);

    public PaymentDTO registPayment(PaymentDTO paymentDTO);

    public DeliveryDTO registDelivery(DeliveryDTO deliveryDTO);

    // public OrderDTO getOrder(@Param("auction_Id") Integer auction_Id);
}