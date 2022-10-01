package org.dblab.auction_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dblab.auction_backend.service.AuctionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private Logger log = LoggerFactory.getLogger(AlertController.class);

    @GetMapping(value = "/subscribeAlert/{checkUser}/{id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable("checkUser") String checkUser, @PathVariable("id") int id) {
        log.info("/subscribeAlert/{checkUser}/{id}-----------------------------" + checkUser + "/" + id);
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        auctionService.registEmitter(checkUser, id, emitter);
        return emitter;
    }

    @GetMapping(value = "/alert/{checkUser}/{id}/{startLimit}")
    public List<Map<String, Object>> getAlert(@PathVariable("checkUser") String checkUser, @PathVariable("id") Integer id, @PathVariable("startLimit") Integer startLimit) {
        log.info("getAlert,  checkUser: "  + checkUser + "  id: " + id + "  startLimit: " + startLimit);
        return auctionService.getAlert(checkUser, id, startLimit);
    }

    @PatchMapping(value = "/checkedAlert/{alert_id}")
    public int checkedAlert(@PathVariable("alert_id") int alert_id) {
        log.info("checkedAlert: "  + alert_id);
        return auctionService.checkedAlert(alert_id);
    }
}
