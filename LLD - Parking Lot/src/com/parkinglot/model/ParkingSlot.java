package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;
import lombok.Getter;

public class ParkingSlot {
    @Getter
    private final String slotId;
    @Getter
    private final VehicleType vehicleType;
    private boolean isOccupied;

    public ParkingSlot(String slotId, VehicleType vehicleType) {
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public boolean isFree() {
        return !isOccupied;
    }

    public void occupy() {
        if (isOccupied) {
            throw new IllegalStateException("Slot is already occupied");
        }
        isOccupied = true;
    }

    public void vacate() {
        if (!isOccupied) {
            throw new IllegalStateException("Slot is already vacant");
        }
        isOccupied = false;
    }
}
