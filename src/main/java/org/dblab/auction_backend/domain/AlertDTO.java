package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class AlertDTO {

    private Integer alert_id;
    private Integer d_status;
    private String time;
    private Integer auction_Id;
    private Integer consumer_id;
    private Integer checked;
    private String auction_name;

    public AlertDTO(Integer auction_Id, String auction_name, Integer consumer_id, Integer d_status){
        this.auction_Id = auction_Id;
        this.auction_name = auction_name;
        this.consumer_id = consumer_id;
        this.d_status = d_status;
    }
}
