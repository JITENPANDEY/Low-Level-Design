package com.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Screen {
    private final String screenId;
    private final List<Seat> seats;
}
