package com.bookmyshow.model;

import com.bookmyshow.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<Seat> bookedSeats;
    private final BookingStatus bookingStatus;
    private final double totalAmount;

    public Booking(User user, Show show, List<Seat> bookedSeats,double totalAmount) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.bookingStatus = BookingStatus.CONFIRMED;
        this.totalAmount = totalAmount;
    }

}
