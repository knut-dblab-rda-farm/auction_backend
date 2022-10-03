package org.dblab.auction_backend.domain;

import lombok.Data;

@Data
public class OrderDTO{

    private PaymentDTO paymentDTO;
    private DeliveryDTO deliveryDTO;
    private Bidding bidding;
}
