package com.parkinglot.strategy;

import com.parkinglot.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricingStrategy implements PricingStrategy {

    private static final double BASE_RATE = 10.0; // Base rate per hour
    private static final double ADDITIONAL_RATE = 5.0; // Additional rate per hour after the first hour

    @Override
    public double calculatePrice(Ticket ticket) {
        long durationInHours = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();

        if (durationInHours <= 1) {
            return BASE_RATE; // First hour is charged at the base rate
        } else {
            return BASE_RATE + (durationInHours - 1) * ADDITIONAL_RATE; // Additional hours are charged at the additional rate
        }
    }
}
