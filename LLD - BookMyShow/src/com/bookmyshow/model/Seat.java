package com.bookmyshow.model;

import com.bookmyshow.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
    private final String seatId;
    private final SeatType seatType;
    private final int row, col;
}
