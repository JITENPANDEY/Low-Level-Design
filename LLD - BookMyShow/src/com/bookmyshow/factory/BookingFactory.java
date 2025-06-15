package com.bookmyshow.factory;

import com.bookmyshow.model.Booking;
import com.bookmyshow.model.Movie;
import com.bookmyshow.model.Screen;
import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;
import com.bookmyshow.model.User;

import java.util.List;

public class BookingFactory {
    // This class can be used to create instances of Booking-related classes
    // For example, it can create instances of Booking, Ticket, etc.

    // Currently, this class is empty but can be expanded in the future
    // to include methods for creating bookings, tickets, etc.

     public static Booking createBooking(User user, Show show, Screen screen, List<Seat> seats, double totalAmount) {
         return new Booking(user, show, seats, totalAmount);
     }
}
