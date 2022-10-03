package org.dblab.auction_backend.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.OrderDTO;
import org.dblab.auction_backend.domain.ProductDTO;
import org.dblab.auction_backend.domain.WishDTO;
import org.dblab.auction_backend.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuctionController {
    /*
        경매 및 리뷰 CRUD 코드
        리뷰 테이블 수정되면 바꾸기!!
    */
    private final AuctionService auctionService;
    private Logger log = LoggerFactory.getLogger(AuctionController.class);

    // #################################################### 경매 CURD #####################################################

    @PostMapping(value = "/registAuction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public Integer registAuction(@ModelAttribute AuctionDTO auctionDTO) {
        log.info(auctionDTO.toString());
        return auctionService.registAuction(auctionDTO);
    }

    @GetMapping(value = "/auctionInfo/{auction_Id}")
    public AuctionDTO getAuctionInfo(@PathVariable("auction_Id") Integer auction_Id){
        log.info("aucitonInfo" + auction_Id);
        return auctionService.auctionInfo(auction_Id);
    }

    @DeleteMapping(value = "/auction/{auction_Id}/{product_id}/{product_img_name}")
    public Integer deleteAuction(@PathVariable("auction_Id") Integer auction_Id, @PathVariable("product_id") Integer product_id, @PathVariable("product_img_name") String product_img_name) {

        log.info("/deleteAuction : " + auction_Id + "-" + product_id  + "-" + product_img_name);

        return auctionService.deleteAuction(auction_Id, product_id, product_img_name);
    }


    // #################################################### 상품 U #####################################################
    @PatchMapping(value = "/product")
    public Integer updateProduct(@ModelAttribute ProductDTO productDTO) {

        // 이미지 변경했는지 여부, 이전 이미지 이름
        log.info(productDTO.toString());

        return auctionService.updateProduct(productDTO);
    }

    // #################################################### 리뷰 CRUD #####################################################
    
    @PostMapping(value = "/AuctionReview")
    public Integer registAuctionReview(@ModelAttribute AuctionReviewDTO auctionReviewDTO) {
        log.info(auctionReviewDTO.toString());
        return auctionService.registAuctionReview(auctionReviewDTO);
    }

    @GetMapping(value = "/AuctionReview/{checkUser}/{id}")
    public List<Map<String, Object>> getAuctionReview(@PathVariable("checkUser") String checkUser, @PathVariable("id") Integer id) {

        return auctionService.getAuctionReview(checkUser, id);
    }

    @PatchMapping(value = "/AuctionReview")
    public Integer updateAuctionReview(@ModelAttribute AuctionReviewDTO auctionReviewDTO) {

        return auctionService.updateAuctionReview(auctionReviewDTO);
    }

    @DeleteMapping(value = "/auctionReview/{auction_Id}/{consumer_id}/{review_img_name}")
    public Integer deleteAuctionReview(@PathVariable("auction_Id") Integer auction_Id, @PathVariable("consumer_id") Integer consumer_id, String review_img_name) {

        return auctionService.deleteAuctionReview(auction_Id, consumer_id, review_img_name);
    }

    @GetMapping(value = "/ProductInfo/{product_id}")
    public List<Map<String, Object>> ProductInfo(@PathVariable("product_id") Integer product_id){
        return auctionService.getProductInfo(product_id);
    }

    // #################################################### 검색 기능 #####################################################

    //경매 검색 기능
    @GetMapping(value = "/searchAuction/{checkUser}/{id}/{keyword}/{startLimit}")
    public List<AuctionDTO> searchAuction(HttpServletRequest request, @PathVariable("checkUser") String checkUser, @PathVariable("id") Integer id, @PathVariable("keyword") String keyword, @PathVariable("startLimit") Integer startLimit) {
        
        log.info("keyword: " + keyword.toString());
        log.info("ip: " + request.getRemoteAddr());

        return auctionService.searchAuction(request.getRemoteAddr(), checkUser, id, keyword, startLimit);
    }


    // 인기 검색어 5개 가져오기
    @GetMapping(value = "/popularKeyword")
    public List<String> getPopularKeyword() {
        return auctionService.getPopularKeyword();
    }

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    @GetMapping(value = "/mypageAuctionDetails/{checkUser}/{id}/{limit}")
    public List<Map<String, Object>> getMypageAuctionDetails(@PathVariable("checkUser") String checkUser, @PathVariable("id") Integer id, @PathVariable("limit") Integer limit) {
        log.info("limit: " + limit);
        return auctionService.getMypageAuctionDetails(checkUser, id, limit);
    }

    // ############################################## 찜 ####################################################
    
    @PostMapping(value = "/registWish")
    public Integer registWish(@RequestBody WishDTO wishDTO){

        return auctionService.registWish(wishDTO);

    }

    @DeleteMapping(value = "/deleteWish/{auction_id}/{consumer_id}")
    public Integer deleteWish(@PathVariable("auction_id") int auction_id, @PathVariable("consumer_id") Integer consumer_id){
        
        return auctionService.deleteWish(auction_id, consumer_id);

    }

    @GetMapping(value = "/checkWish/{auction_id}/{consumer_id}")
    public Integer checkWish(@PathVariable("auction_id") Integer auction_id, @PathVariable("consumer_id") Integer consumer_id){
        log.info(" ddafsdfasdfasdfasdfasdfasdfd"+auctionService.checkWish(auction_id, consumer_id));
        //log.info(" auction_id ,consumer_id "+auction_id+" "+consumer_id);
        log.info(" auction_id ,consumer_id "+auction_id+" "+consumer_id);
        //log.info(" auction_id ,consumer_id "+auctionService.checkWish(auction_id, consumer_id));
        
        return auctionService.checkWish(auction_id, consumer_id);
    }

    @GetMapping(value ="/getWishList/{consumer_id}/{limit}")
    public List<Map<String, Object>> getWishList(@PathVariable("consumer_id") Integer consumer_id, @PathVariable("limit") int limit){
        log.info("limit"+limit);
        log.info("consumer"+consumer_id);
        return auctionService.getWishList(consumer_id, limit);
    }

    @GetMapping(value ="/consumerPachiPoint/{consumer_id}")
    public Integer consumerPachiPoint(@PathVariable("consumer_id") Integer consumer_id){
        return auctionService.consumerPachiPoint(consumer_id);
    }

    @GetMapping(value ="/farmPachiPoint/{farm_id}")
    public Integer farmPachiPoint(@PathVariable("farm_id") Integer farm_id){
        return auctionService.farmPachiPoint(farm_id);
    }

    @GetMapping(value = "/consumerCountAuction/{consumer_id}")
    public Integer consumerCountAuction(@PathVariable("consumer_id") Integer consumer_id){
        return auctionService.consumerCountAuction(consumer_id);
    }
    @GetMapping(value = "/farmCountAuction/{farm_id}")
    public Integer farmCountAuction(@PathVariable("farm_id") Integer farm_id){
        return auctionService.farmCountAuction(farm_id);
    }

    // #################################################### 결제 및 배송 #####################################################
    @PostMapping(value = "/payment")
    public OrderDTO registAuction(@RequestBody OrderDTO orderDTO) {
        log.info(orderDTO.toString());
        return auctionService.registOrder(orderDTO);
    }
}
