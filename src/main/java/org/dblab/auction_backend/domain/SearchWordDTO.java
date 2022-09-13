package org.dblab.auction_backend.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class SearchWordDTO {

    private Integer search_word_id;
    private String keyword;
    private Date date;
    private String ip;
    private Integer consumer_id;
    private Integer farm_id;
}
