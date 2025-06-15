package com.bookmyshow.strategy;

import com.bookmyshow.enums.SeatType;
import com.bookmyshow.model.Seat;

import java.util.List;
import java.util.Map;

public class SeatTypeBasedPricingStrategy implements PricingStrategy {

    private final Map<SeatType, Double> seatTypePrices = Map.of(
        SeatType.REGULAR, 150.0,
        SeatType.PREMIUM, 200.0,
        SeatType.RECLINER, 300.0
    );

    @Override
    public double calculatePrice(List<Seat> seats) {
        return seats.stream()
            .mapToDouble(seat -> seatTypePrices.getOrDefault(seat.getSeatType(), 0.0))
            .sum();
    }
}
