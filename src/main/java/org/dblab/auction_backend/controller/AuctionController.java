package org.dblab.auction_backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.AuctionReviewDTO;
import org.dblab.auction_backend.domain.ProductDTO;
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
    private Logger log = LoggerFactory.getLogger(MemberController.class);

    // #################################################### 경매 CURD #####################################################

    @PostMapping(value = "/registAuction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public int registAuction(@ModelAttribute AuctionDTO auctionDTO) {
        System.out.println(auctionDTO.toString());
        return auctionService.registAuction(auctionDTO);
    }

    // 경매 가져오기(limit부터 2개)
    @GetMapping(value = "/getAuction/{limit}")
    public List<AuctionDTO> getAuction(@PathVariable("limit") int limit) {
        log.info("limit: " + limit);
        return auctionService.getAuction(limit);
    }
    
    @PatchMapping(value = "/updateAuction", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public int updateAuction(@ModelAttribute AuctionDTO auctionDTO) {

        // 이미지 변경했는지 여부, 이전 이미지 이름
        System.out.println(auctionDTO.toString());

        return auctionService.updateAuction(auctionDTO);
    }

    @DeleteMapping(value = "/deleteAuction/{auction_Id}")
    public int deleteAuction(@PathVariable("auction_Id") int auction_Id) {

        log.info("deleteAuction/{auction_Id}: " + auction_Id);

        return auctionService.deleteAuction(auction_Id);
    }


    // #################################################### 상품 RUD #####################################################

    @GetMapping(value = "/getProduct", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public List<ProductDTO> getProduct() {
        return auctionService.getProduct();
    }

    @PatchMapping(value = "/updateProduct")
    public int updateProduct(@ModelAttribute ProductDTO productDTO) {

        // 이미지 변경했는지 여부, 이전 이미지 이름
        System.out.println(productDTO.toString());

        return auctionService.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/deleteProduct/{product_id}")
    public int deleteProduct(@PathVariable("product_id") int product_id) {

        log.info("deleteProduct/{product_id}: " + product_id);

        return auctionService.deleteProduct(product_id);
    }


    // #################################################### 리뷰 CRUD #####################################################
    
    @PostMapping(value = "/registAuctionReview")
    public int registAuctionReview(@RequestBody AuctionReviewDTO auctionReviewDTO) {

        return auctionService.registAuctionReview(auctionReviewDTO);
    }

    @GetMapping(value = "/getAuctionReview/{checkUser}/{id}")
    public List<Map<String, Object>> getAuctionReview(@PathVariable("checkUser") String checkUser, @PathVariable("id") int id) {

        return auctionService.getAuctionReview(checkUser, id);
    }

    @PatchMapping(value = "/updateAuctionReview")
    public int updateAuctionReview(@RequestBody AuctionReviewDTO auctionReviewDTO) {

        return auctionService.updateAuctionReview(auctionReviewDTO);
    }

    @DeleteMapping(value = "/deleteAuctionReview")
    public int deleteAuctionReview(@RequestBody AuctionReviewDTO auctionReviewDTO) {

        return auctionService.deleteAuctionReview(auctionReviewDTO);
    }


    // #################################################### 검색 기능 #####################################################

    // 경매 검색 기능
    @GetMapping(value = "/searchAuction/{checkUser}/{id}/{keyword}")
    public List<AuctionDTO> searchAuction(HttpServletRequest request, @PathVariable("checkUser") String checkUser, @PathVariable("id") int id, @PathVariable("keyword") String keyword) {
        
        log.info("keyword: " + keyword);
        log.info("ip: " + request.getRemoteAddr());

        return auctionService.searchAuction(request.getRemoteAddr(), checkUser, id, keyword);
    }

    // 인기 검색어 5개 가져오기
    @GetMapping(value = "/popularKeyword")
    public List<String> getPopularKeyword() {
        return auctionService.getPopularKeyword();
    }

    // ############################################## 마이페이지 ####################################################
    
    // 소비자, 농가 경매내역 가져오기
    @GetMapping(value = "/getMypageAuctionDetails/{checkUser}/{limit}")
    public List<Map<String, Object>> getMypageAuctionDetails(@PathVariable("checkUser") String checkUser, @PathVariable("limit") int limit) {
        log.info("limit: " + limit);
        return auctionService.getMypageAuctionDetails(checkUser, limit);
    }
}
