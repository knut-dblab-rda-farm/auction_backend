package org.dblab.auction_backend.config;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ThreadControl {

    private final BidDeadlineTimer bidDeadlineTimer;
    private static Thread timerThread;

    public void start(){
        timerThread = new Thread(bidDeadlineTimer);
        timerThread.start();
    }
    public static void checkBidding(int auction_Id, Date deadline_date) {
        if (BidDeadlineTimer.checkBidding(auction_Id, deadline_date) == 1) timerThread.interrupt();
    }
    
}
