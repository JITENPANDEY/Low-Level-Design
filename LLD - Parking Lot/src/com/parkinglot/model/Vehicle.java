package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
}
