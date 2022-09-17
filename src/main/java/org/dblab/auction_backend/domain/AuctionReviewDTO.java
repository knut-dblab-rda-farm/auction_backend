package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class AuctionReviewDTO {
    private String checkUser;
    private Integer auction_Id;
    private Integer grade_point;
    private String consumer_review;
    private String farm_review;
}
