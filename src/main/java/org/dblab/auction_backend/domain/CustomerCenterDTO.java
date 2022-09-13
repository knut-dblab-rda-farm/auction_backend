package org.dblab.auction_backend.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerCenterDTO {
    
    private int inquiry_id;
    private String title;
    private String content;
    private Date inquiry_date;
}
