package com.mitsko.multithreading.entity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Lot {
    private String description;
    private int startingPrice;
    private AtomicInteger currentPrice;
    private boolean sales;

    public Lot(String description, int startingPrice) {
        this.description = description;
        this.startingPrice = startingPrice;
        this.currentPrice = new AtomicInteger(startingPrice);
        this.sales = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getCurrentPrice() {
        return currentPrice.get();
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice.set(currentPrice);
    }

    public boolean isSales() {
        return sales;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return startingPrice == lot.startingPrice &&
                sales == lot.sales &&
                description.equals(lot.description) &&
                currentPrice.equals(lot.currentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, startingPrice, currentPrice, sales);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                ", currentPrice=" + currentPrice +
                ", sales=" + sales +
                '}';
    }
}
