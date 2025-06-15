package com.bookmyshow.model;

import com.bookmyshow.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime showTime;
    Map<Seat, SeatStatus> seatMap = new HashMap<>();

    public Show(String showId, Movie movie, Screen screen, LocalDateTime showTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
        initializeSeatMap();
    }

    private void initializeSeatMap() {
        for (Seat seat : screen.getSeats()) {
            seatMap.put(seat, SeatStatus.AVAILABLE);
        }
    }

    public boolean isAvailable(Seat seat) {
        return seatMap.get(seat) == SeatStatus.AVAILABLE;
    }
}
