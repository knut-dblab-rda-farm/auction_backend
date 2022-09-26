package org.dblab.auction_backend.domain;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class AuctionReviewDTO {
    private String checkUser;
    private Integer auction_Id;
    private Integer grade_point;
    private String consumer_review;
    private String farm_review;
    private Integer farm_id;
    private Integer consumer_id;
    private String auction_name;
    private String review_img_name;
    private String product_img_name;
    private String f_farm_name;
    private String c_name;
    private MultipartFile review_img_file;
}