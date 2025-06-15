package com.bookmyshow.strategy;

import com.bookmyshow.model.Seat;

import java.util.List;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}
