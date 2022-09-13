package org.dblab.auction_backend.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BidClosingDTO {
    
    private Integer auction_Id;
    private Date deadline_date;

    public BidClosingDTO(Integer auction_Id, Date deadline_date){
        this.auction_Id = auction_Id;
        this.deadline_date = deadline_date;
    }
}
