package com.parkinglot.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {
    private final String ticketId;
    private final String slotId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;

    public Ticket(String ticketId, String slotId, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.slotId = slotId;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }
}
