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
		
		String MEMBER_PROFILE_FOLDER_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/member_profile";  // 사용자 이미지 폴더 경로
		String PRODUCT_IMAGES_FOLDER_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/product_images"; // 상품 이미지 폴더 경로

		File memberProfileFolder = new File(MEMBER_PROFILE_FOLDER_PATH);
		File productImagesFolder = new File(PRODUCT_IMAGES_FOLDER_PATH);

		// 이미지 보관 폴더 생성
		if (!memberProfileFolder.exists()) {
			try{
				memberProfileFolder.mkdir();
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
				} 
				catch(Exception e){
				e.getStackTrace();
			}} else {
			System.out.println("이미 product_images 폴더가 생성되어 있습니다.");
		}

		SpringApplication.run(AuctionBackendApplication.class, args);
	}
}
