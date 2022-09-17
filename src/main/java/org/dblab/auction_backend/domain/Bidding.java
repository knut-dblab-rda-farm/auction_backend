package org.dblab.auction_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bidding {

    private Integer auction_Id;
    private Integer bid_price;
    private Integer farm_id;
    private Integer auction_consumer_id;  // 이전 입찰자
    private Integer consumer_id;
    private String auction_name;
}
