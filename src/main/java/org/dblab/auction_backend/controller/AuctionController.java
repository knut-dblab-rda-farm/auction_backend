package org.dblab.auction_backend.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    private Logger log = LoggerFactory.getLogger(MemberController.class);

    // #################################################### 경매 CURD #####################################################

    @PostMapping(value = "/registAuction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public int registAuction(@ModelAttribute AuctionDTO auctionDTO) {
        System.out.println(auctionDTO.toString());
        return auctionService.registAuction(auctionDTO);
    }

    @GetMapping(value = "/auctionInfo/{auction_Id}")
    public AuctionDTO getAuctionInfo(@PathVariable("auction_Id") int auction_Id){
        log.info("aucitonInfo" + auction_Id);
        return auctionService.auctionInfo(auction_Id);
    }
    
    @PatchMapping(value = "/updateAuction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public int updateAuction(@ModelAttribute AuctionDTO auctionDTO) {

        // 이미지 변경했는지 여부확인하기
        System.out.println(auctionDTO.toString());

        return auctionService.updateAuction(auctionDTO);
    }

    @DeleteMapping(value = "/deleteAuction")
    public int deleteAuction(@RequestBody Map<String, Integer> map) {

        log.info("/deleteAuction : " + map.get("auction_Id") + "-" + map.get("product_id"));

        return auctionService.deleteAuction(map.get("auction_Id"), map.get("product_id"));
    }


    // #################################################### 상품 U #####################################################
    @PatchMapping(value = "/updateProduct")
    public int updateProduct(@ModelAttribute ProductDTO productDTO) {

        // 이미지 변경했는지 여부, 이전 이미지 이름
        System.out.println(productDTO.toString());

        return auctionService.updateProduct(productDTO);
    }

    // #################################################### 리뷰 CRUD #####################################################
    
    @PostMapping(value = "/AuctionReview")
    public int registAuctionReview(@ModelAttribute AuctionReviewDTO auctionReviewDTO) {
        System.out.println(auctionReviewDTO.toString());
        return auctionService.registAuctionReview(auctionReviewDTO);
    }

    @GetMapping(value = "/AuctionReview/{checkUser}/{id}")
    public List<Map<String, Object>> getAuctionReview(@PathVariable("checkUser") String checkUser, @PathVariable("id") int id) {

        return auctionService.getAuctionReview(checkUser, id);
    }

    @PatchMapping(value = "/AuctionReview")
    public int updateAuctionReview(@ModelAttribute AuctionReviewDTO auctionReviewDTO) {

        return auctionService.updateAuctionReview(auctionReviewDTO);
    }

    @DeleteMapping(value = "/AuctionReview")
    public int deleteAuctionReview(@RequestBody AuctionReviewDTO auctionReviewDTO) {

        return auctionService.deleteAuctionReview(auctionReviewDTO);
    }

    @GetMapping(value = "/ProductInfo/{product_id}")
    public List<Map<String, Object>> ProductInfo(@PathVariable("product_id") int product_id){
        return auctionService.getProductInfo(product_id);
    }




    // #################################################### 검색 기능 #####################################################

    //경매 검색 기능
    @GetMapping(value = "/searchAuction/{checkUser}/{id}/{keyword}/{startLimit}")
    public List<AuctionDTO> searchAuction(HttpServletRequest request, @PathVariable("checkUser") String checkUser, @PathVariable("id") int id, @PathVariable("keyword") String keyword, @PathVariable("startLimit") int startLimit) {
        
        log.info("keyword: " + keyword);
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
    public List<Map<String, Object>> getMypageAuctionDetails(@PathVariable("checkUser") String checkUser, @PathVariable("id") int id, @PathVariable("limit") int limit) {
        log.info("limit: " + limit);
        return auctionService.getMypageAuctionDetails(checkUser, id, limit);
    }

    // ############################################## 찜 ####################################################
    
    @PostMapping(value = "/registWish")
    public int registWish(@RequestBody WishDTO wishDTO){

        return auctionService.registWish(wishDTO);

    }

    @DeleteMapping(value = "/deleteWish/{auction_id}/{consumer_id}")
    public int deleteWish(@PathVariable("auction_id") int auction_id, @PathVariable("consumer_id") int consumer_id){
        
        return auctionService.deleteWish(auction_id, consumer_id);

    }

    @GetMapping(value = "/checkWish/{auction_id}/{consumer_id}")
    public int checkWish(@PathVariable("auction_id") int auction_id, @PathVariable("consumer_id") int consumer_id){
        log.info(" ddafsdfasdfasdfasdfasdfasdfd"+auctionService.checkWish(auction_id, consumer_id));
        //log.info(" auction_id ,consumer_id "+auction_id+" "+consumer_id);
        System.out.println(" auction_id ,consumer_id "+auction_id+" "+consumer_id);
        //System.out.println(" auction_id ,consumer_id "+auctionService.checkWish(auction_id, consumer_id));
        
        return auctionService.checkWish(auction_id, consumer_id);
    }

    @GetMapping(value ="/getWishList/{consumer_id}/{limit}")
    public List<Map<String, Object>> getWishList(@PathVariable("consumer_id") int consumer_id, @PathVariable("limit") int limit){
        log.info("limit"+limit);
        log.info("consumer"+consumer_id);
        return auctionService.getWishList(consumer_id, limit);
    }

    @GetMapping(value ="/consumerPachiPoint/{consumer_id}")
    public int consumerPachiPoint(@PathVariable("consumer_id") int consumer_id){
        return auctionService.consumerPachiPoint(consumer_id);
    }

    @GetMapping(value ="/farmPachiPoint/{farm_id}")
    public int farmPachiPoint(@PathVariable("farm_id") int farm_id){
        return auctionService.farmPachiPoint(farm_id);
    }

    @GetMapping(value = "/consumerCountAuction/{consumer_id}")
    public int consumerCountAuction(@PathVariable("consumer_id") int consumer_id){
        return auctionService.consumerCountAuction(consumer_id);
    }
    @GetMapping(value = "/farmCountAuction/{farm_id}")
    public int farmCountAuction(@PathVariable("farm_id") int farm_id){
        return auctionService.farmCountAuction(farm_id);
    }

}
