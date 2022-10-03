package org.dblab.auction_backend.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class AuctionDTO {

    private Integer auction_Id;
    private String auction_name;
    private String start_date;
    private String deadline_date;
    private Integer a_starting_price;
    private Integer a_max_price;
    private Integer bid_price;
    private Integer bid_num;
    private Integer bid_status;
    private Date bid_time;
    private Integer product_id;
    private Integer farm_id;
    private Integer consumer_id;
    private String c_name;
    private String f_farm_name;
    private Boolean payment_status; 

    private String f_name;
    private Integer f_num;
    private String f_location;
    private String f_profile_img;
    private String f_img;
    private Integer f_phonenum;


    private ProductDTO productDTO;

}
