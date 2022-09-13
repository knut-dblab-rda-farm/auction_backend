package org.dblab.auction_backend.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class DeliveryDTO {
    
    private int delivery_id;
    private int d_status;
    private Date departure_date;
    private Date arrival_date;
}
