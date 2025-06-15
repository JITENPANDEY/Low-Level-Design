package com.bookmyshow.strategy;

import com.bookmyshow.model.Seat;

import java.util.List;

public class FixedPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.size() * 150.0;
    }
}
