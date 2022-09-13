package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class AuctionReviewDTO {

    private int auction_Id;
    private int grade_point;
    private String review;
}
