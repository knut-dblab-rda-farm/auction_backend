package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class AlertDTO {

    private Integer alert_id;
    private Integer d_status;
    private String time;
    private Integer auction_Id;
    private Integer consumer_id;
    private Integer pre_consumer_id;
    private Integer farm_id;
    private Integer checked;
    private String auction_name;
    private String product_img_name;
    private String f_farm_name;
    private String c_name;

    public AlertDTO(Integer auction_Id, String auction_name, Integer pre_consumer_id, Integer consumer_id, Integer farm_id, Integer d_status, String product_img_name, String f_farm_name, String c_name){
        this.auction_Id = auction_Id;
        this.auction_name = auction_name;
        this.pre_consumer_id = pre_consumer_id;
        this.consumer_id = consumer_id;
        this.farm_id = farm_id;
        this.d_status = d_status;
        this.product_img_name = product_img_name;
        this.f_farm_name = f_farm_name;
        this.c_name = c_name;
    }
}
