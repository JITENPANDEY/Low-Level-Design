package com.parkinglot.service;

import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.strategy.HourlyPricingStrategy;
import com.parkinglot.strategy.PricingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class ParkingLot {

    private static ParkingLot INSTANCE;
    private final List<ParkingFloor> floors;
    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        floors = new ArrayList<>();
        pricingStrategy = new HourlyPricingStrategy();
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            synchronized (ParkingLot.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ParkingLot();
                }
            }

        }
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        if (floor == null || floors.contains(floor)) {
            throw new IllegalArgumentException("Invalid parking floor");
        }
        floors.add(floor);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSlot> freeSlot = floor.getFreeSlot(vehicle.getType());
            if(freeSlot.isPresent()) {
                ParkingSlot slot = freeSlot.get();
                slot.occupy();
                String ticketId = UUID.randomUUID().toString();
                System.out.println("Vehicle with ID " + vehicle.getLicenseNumber() + " parked in slot " + slot.getSlotId() + " on floor " + floor.getFloorId());
                return new Ticket(ticketId, slot.getSlotId(), vehicle);
            }
        }
        throw new IllegalArgumentException("Slot not available or invalid");
    }

    public double unparkVehicle(Ticket ticket) {
        if (ticket == null || ticket.getSlotId() == null || ticket.getVehicle() == null) {
            throw new IllegalArgumentException("Invalid ticket");
        }

        double price  = pricingStrategy.calculatePrice(ticket);
        for (ParkingFloor floor : floors) {
            try {
                floor.vacateSlot(ticket.getSlotId());
                System.out.println("Vehicle " + ticket.getVehicle().getLicenseNumber() + " with ticket ID " + ticket.getTicketId() + " has been vacated from slot " + ticket.getSlotId() + " on floor " + floor.getFloorId());
                System.out.println("Total price for parking: " + price);
                return price;
            } catch (IllegalArgumentException e) {
                System.out.println("Slot not found in floor: " + floor.getFloorId());
            }
        }

        throw new IllegalArgumentException("Ticket not found or already vacated");
    }
}
