package org.dblab.auction_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.dblab.auction_backend.service.AuctionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlertController {

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final AuctionService auctionService;

    @GetMapping(value = "/subscribeAlert/{checkUser}/{id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable("checkUser") String checkUser, @PathVariable("id") int id) {

        System.out.println("/subscribeAlert/{checkUser}/{id}-----------------------------" + checkUser + "/" + id);
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        auctionService.registEmitter(checkUser, id, emitter);

        return emitter;
    }

    @PatchMapping(value = "/checkedAlert/{alert_id}")
    public int checkedAlert(@PathVariable("alert_id") int alert_id) {

        System.out.println("checkedAlert: "  + alert_id);

        return auctionService.checkedAlert(alert_id);
    }
}
