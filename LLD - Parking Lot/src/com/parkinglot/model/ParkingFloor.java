package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class ParkingFloor {
    private final String floorId;
    private final Map<VehicleType, List<ParkingSlot>> slots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.slots = new HashMap<>();
        for (VehicleType type : VehicleType.values()) {
            slots.put(type, new ArrayList<>());
        }
    }

    public void addSlot(ParkingSlot slot) {
        if (slot == null || !slots.containsKey(slot.getVehicleType())) {
            throw new IllegalArgumentException("Invalid parking slot");
        }
        slots.get(slot.getVehicleType()).add(slot);
    }

    public Optional<ParkingSlot> getFreeSlot(VehicleType type) {
        return slots.get(type).stream().filter(ParkingSlot::isFree).findFirst();
    }

    public void vacateSlot(String slotId) {
        for (List<ParkingSlot> slotList : slots.values()) {
            for (ParkingSlot slot : slotList) {
                if (slot.getSlotId().equals(slotId)) {
                    slot.vacate();
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Slot not found: " + slotId);
    }


}
