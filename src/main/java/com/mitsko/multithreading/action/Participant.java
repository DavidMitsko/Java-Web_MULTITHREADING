package com.mitsko.multithreading.action;

import com.mitsko.multithreading.entity.Lot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Participant implements Runnable {
    private final static Logger logger = LogManager.getLogger(Participant.class);

    private Auction auction;

    private int startingPrice;
    private int currentPrice;
    private Lot lot;

    public Participant() {
        auction = Auction.getInstance();
    }

    @Override
    public void run() {
        logger.info("Create participant " + this);
        try {
            while (auction.isEmpty()) {
                lot = auction.getLot(auction.getIndex());
                if (random()) {
                    makeBet();
                }
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {

        }
    }

    private void makeBet() {
        startingPrice = lot.getStartingPrice();
        currentPrice = lot.getCurrentPrice();
        int newPrice = currentPrice + (int) (startingPrice * 0.15);
        lot.setCurrentPrice(newPrice);
        auction.setCount(0);
        logger.info(this + "get new price = " + newPrice + " to lot number " + auction.getIndex());
    }

    private boolean random() {
        Random random = new Random();
        int r = random.nextInt(10);
        return r > 5;
    }
}
