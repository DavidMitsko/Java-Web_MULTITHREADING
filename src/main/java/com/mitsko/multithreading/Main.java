package com.mitsko.multithreading;

import com.mitsko.multithreading.entity.Lot;
import com.mitsko.multithreading.action.Participant;
import com.mitsko.multithreading.action.Auction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Auction auction = Auction.getInstance();

        List<Lot> lots = new ArrayList<>();
        lots.add(new Lot("Phone", 1000));
        lots.add(new Lot("PC", 1500));
        lots.add(new Lot("Picture", 2500000));
        auction.setLots(lots);

        Participant participant0 = new Participant();
        Participant participant1 = new Participant();


        Thread part1 = new Thread(participant0);
        part1.start();
        Thread part2 = new Thread(participant1);
        part2.start();

        auction.start();
    }
}
