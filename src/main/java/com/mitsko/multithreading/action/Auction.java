package com.mitsko.multithreading.action;

import com.mitsko.multithreading.entity.Lot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Auction {
    private final static Logger logger = LogManager.getLogger(Auction.class);
    private final static Auction instance = new Auction();

    private List<Lot> lots;
    private int index;
    private AtomicInteger count;

    private Auction() {
        lots = new ArrayList<>();
        index = 0;
        count = new AtomicInteger(0);
    }

    public static Auction getInstance() {
        return instance;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public int getIndex() {
        return index;
    }

    public Lot getLot(int index) {
        return lots.get(index);
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public int size() {
        return lots.size();
    }

    public int getCount() {
        return count.get();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void start() {
        try {
            while (lots.size() != index) {
                Thread.sleep(1000);
                if (count.get() == 3) {
                    index += 1;
                    count.set(0);
                    logger.info("Sales " + lots.get(index - 1).getCurrentPrice());
                } else {
                    count.set(count.get() + 1);
                    logger.info("Count " + count);
                }
            }
        } catch (InterruptedException ex) {

        }
    }
}
