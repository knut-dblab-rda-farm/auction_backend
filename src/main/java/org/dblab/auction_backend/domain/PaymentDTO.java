package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class PaymentDTO{
    private Integer payment_id;
    private Integer auction_Id;
    private Integer payment_amount;
    private String pay_method;
}
