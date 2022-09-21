package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class WishDTO {

    private Integer auction_Id;
    private Integer consumer_Id;
    private Integer startLimit;

}