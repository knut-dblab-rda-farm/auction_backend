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
    private Integer isMaxPrice;
    private String product_img_name;
    private String f_farm_name;
    private String c_name;

    public Bidding() {
        
    }

    public Bidding(Integer auction_Id, String auction_name, Integer farm_id, Integer consumer_id, String product_img_name, String f_farm_name, String c_name){
        this.auction_Id = auction_Id;
        this.auction_name = auction_name;
        this.farm_id = farm_id;
        this.consumer_id = consumer_id;
        this.product_img_name = product_img_name;
        this.f_farm_name = f_farm_name;
        this.c_name = c_name;
    }
}
