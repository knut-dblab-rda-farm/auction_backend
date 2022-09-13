package org.dblab.auction_backend.controller;

import java.util.List;

import org.dblab.auction_backend.domain.AuctionDTO;
import org.dblab.auction_backend.domain.Bidding;
import org.dblab.auction_backend.service.AuctionService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/socket")
@Controller
@RequiredArgsConstructor
public class SocketController {

    private final AuctionService auctionService;

    @MessageMapping("/receive_limit/{id}")
    @SendTo("/send_auction_data/{id}")
    public List<AuctionDTO> getAuction(int limit){

        System.out.println("limit: " + limit);

        return auctionService.getAuction(limit);
    }


    @MessageMapping("/receive_bidding")
    @SendTo("/send_bidding")
    public Bidding bid(Bidding bidding){

        int result = auctionService.updateBidding(bidding);
        System.out.println("updateBidding result: " + result);

        if(result != 1) return null;

        return bidding;
    }
}
