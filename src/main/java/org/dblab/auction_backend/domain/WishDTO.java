package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class WishDTO {

    private Integer auction_id;
    private Integer consumer_id;
    private Integer startLimit;

}