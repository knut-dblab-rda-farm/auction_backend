package org.dblab.auction_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.dblab.auction_backend.domain.Post;
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

        System.out.println(checkUser + "/" + id);
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        auctionService.registEmitter(checkUser, id, emitter);

        return emitter;
    }

    @PatchMapping(value = "/checkedAlert/{alert_id}")
    public int checkedAlert(@PathVariable("alert_id") int alert_id) {

        System.out.println("checkedAlert: "  + alert_id);

        return auctionService.checkedAlert(alert_id);
    }






    // ---------------------------------------- 아래 쪽은 테스트 예제입니다! ----------------------------------------------------------

    // @GetMapping(value = "/api/SSEsubscribe/{id}", consumes = MediaType.ALL_VALUE)
    @GetMapping(value = "/api/SSEsubscribe/{id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable("id") String id) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        // SseEmitter emitter = new SseEmitter(300000L);
        try {
            System.out.println("id: " + id);
            emitter.send(SseEmitter.event().name("id"));
            // emitter.send(SseEmitter.event().data("id"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        emitter.onCompletion(() -> emitters.remove(emitter));

        emitters.add(emitter);
        return emitter;
    }

    // method for dispatching events to all clients produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    @PostMapping(value = "/api/dispatchEvent")
    public void dispatchEventToClients (@RequestParam String freshNews){
        
        for ( SseEmitter emitter : emitters){
            try {
                Post post = new Post(freshNews, "dsdfsdfd");
                System.out.println("freshNews: " + freshNews);
                emitter.send(SseEmitter.event().name("latestNews").data(post));
                // emitter.send(SseEmitter.event().data("id"));
            } catch (IOException e) {
                emitters.remove(emitter);
                e.printStackTrace();
            }
        }
    }
}
