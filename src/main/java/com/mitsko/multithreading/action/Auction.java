package com.mitsko.multithreading.action;

import com.mitsko.multithreading.entity.Lot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {
    private final static Logger logger = LogManager.getLogger(Auction.class);
    private static Auction instance;

    private static ReentrantLock lock = new ReentrantLock();

    private List<Lot> lots;
    private int index;
    private AtomicInteger count;

    private Auction() {
        lots = new ArrayList<>();
        index = 0;
        count = new AtomicInteger(0);
    }

    public static Auction getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new Auction();
            }
            lock.unlock();
        }
        return instance;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public int getIndex() {
        int local;
        lock.lock();
        local = index;
        lock.unlock();
        return local;
    }

    public Lot getLot(int index) {
        Lot temp;
        lock.lock();
        temp = lots.get(index);
        lock.unlock();
        return temp;
    }

    public void setCount(int count) {
        this.count.getAndSet(count);
    }

    public boolean isEmpty() {
        lock.lock();
        if (lots.size() == index){
            lock.unlock();
            return false;
        } else {
            lock.unlock();
            return true;
        }
    }

    public int getCount() {
        return count.get();
    }

    public void start() {
        try {
            while (lots.size() != index) {
                Thread.sleep(1000);
                if (count.get() == 3) {
                    lock.lock();
                    count.set(0);
                    if (lots.get(index).getCurrentPrice() == lots.get(index).getStartingPrice()) {
                        logger.info("Not sales " + lots.get(index).getDescription());
                    } else {
                        logger.info("Sales " + lots.get(index).getDescription() + " " + lots.get(index).getCurrentPrice());
                    }
                    index += 1;
                    lock.unlock();
                } else {
                    count.getAndSet(count.get() + 1);
                    logger.info("Count " + count);
                }
            }
        } catch (InterruptedException ex) {

        }
    }
}
