package com.parkinglot.strategy;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VehicleBasedFeeStrategy implements PricingStrategy {

    private final Map<VehicleType, Double> vehicleTypeBasePrices;

    public VehicleBasedFeeStrategy() {
        this.vehicleTypeBasePrices = new HashMap<>();
        vehicleTypeBasePrices.put(VehicleType.BIKE, 10.0);
        vehicleTypeBasePrices.put(VehicleType.CAR, 20.0);
        vehicleTypeBasePrices.put(VehicleType.TRUCK, 30.0);
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        Double vehicleTicketPrice = vehicleTypeBasePrices.getOrDefault(ticket.getVehicle().getType(), 20.0);
        long timeDuration = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
        long timeDurationFactor = Math.max(1, timeDuration);
        return vehicleTicketPrice * timeDurationFactor;
    }
}
