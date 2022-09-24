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
    private int isMaxPrice;

    public Bidding() {
        
    }

    public Bidding(Integer auction_Id, String auction_name, Integer consumer_id){
        this.auction_Id = auction_Id;
        this.auction_name = auction_name;
        this.consumer_id = consumer_id;
    }
}
