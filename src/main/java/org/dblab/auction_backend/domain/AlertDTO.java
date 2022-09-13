package org.dblab.auction_backend.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AlertDTO {

    private int alert_id;
    private int d_status;
    private Date time;
    private int auction_Id;
    private int consumer_id;
    private int checked;
    private String auction_name;

    public AlertDTO(int auction_Id, String auction_name, int consumer_id, int d_status){
        this.auction_Id = auction_Id;
        this.auction_name = auction_name;
        this.consumer_id = consumer_id;
        this.d_status = d_status;
    }
}
