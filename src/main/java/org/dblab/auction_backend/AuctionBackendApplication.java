package org.dblab.auction_backend;

import java.io.File;
import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.dblab.auction_backend.mapper")
@SpringBootApplication
public class AuctionBackendApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		
		String MEMBER_PROFILE_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/member_profile_images";  	// 사용자 이미지 폴더 경로
		String PRODUCT_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/product_images"; 		 			// 상품 이미지 폴더 경로
		String BANK_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/bank_images"; 		 				// 은행 사본 이미지 폴더 경로
		String FARM_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/farm_images";						// 농가 업체 이미지 폴더 경로
		String AUCTION_SLIDE_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/auciton_slide_images";		// 경매 슬라이드 이미지 폴더 경로
		String AUCTION_REVIEW_IMAGES_FOLDER_PATH = "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/auciton_review_images";				// 경매 리뷰 이미지 폴더 경로

		File memberProfileImagesFolder = new File(MEMBER_PROFILE_IMAGES_FOLDER_PATH);
		File productImagesFolder = new File(PRODUCT_IMAGES_FOLDER_PATH);
		File bankImagesFolder = new File(BANK_IMAGES_FOLDER_PATH);
		File farmImagesFolder = new File(FARM_IMAGES_FOLDER_PATH);
		File auctionSlideImagesFolder = new File(AUCTION_SLIDE_IMAGES_FOLDER_PATH);
		File auctionReviewImagesFolder = new File(AUCTION_REVIEW_IMAGES_FOLDER_PATH);

		// 이미지 보관 폴더 생성
		if (!memberProfileImagesFolder.exists()) {
			try{
				memberProfileImagesFolder.mkdir();
				System.out.println("member_profile 폴더가 생성되었습니다.");
			} catch(Exception e){
				e.getStackTrace();
		}} else {
			System.out.println("이미 member_profile 폴더가 생성되어 있습니다.");
		}
		if (!productImagesFolder.exists()) {
			try{
				productImagesFolder.mkdir();
				System.out.println("product_images 폴더가 생성되었습니다.");
				} catch(Exception e){
				e.getStackTrace();
		}} else {
			System.out.println("이미 product_images 폴더가 생성되어 있습니다.");
		}
		if (!bankImagesFolder.exists()) {
			try{
				bankImagesFolder.mkdir();
				System.out.println("bank_images 폴더가 생성되었습니다.");
				} catch(Exception e){
				e.getStackTrace();
		}} else {
			System.out.println("이미 bank_images 폴더가 생성되어 있습니다.");
		}
		if (!farmImagesFolder.exists()) {
			try{
				farmImagesFolder.mkdir();
				System.out.println("farm_images 폴더가 생성되었습니다. ");
				} catch(Exception e){
				e.getStackTrace();
		}} else{
			System.out.println("이미 farm_images 폴더가 생성되어 있습니다.");
		}
		if (!auctionSlideImagesFolder.exists()) {
			try{
				auctionSlideImagesFolder.mkdir();
				System.out.println("auciton_slide_images 폴더가 생성되었습니다. ");
				} catch(Exception e){
				e.getStackTrace();
		}} else{
			System.out.println("이미 auciton_review_images 폴더가 생성되어 있습니다.");
		}
		if (!auctionReviewImagesFolder.exists()) {
			try{
				auctionReviewImagesFolder.mkdir();
				System.out.println("auciton_review_images 폴더가 생성되었습니다. ");
				} catch(Exception e){
				e.getStackTrace();
		}} else{
			System.out.println("이미 auciton_review_images 폴더가 생성되어 있습니다.");
		}


		SpringApplication.run(AuctionBackendApplication.class, args);
	}
}
