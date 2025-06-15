package com.bookmyshow.service;

import com.bookmyshow.enums.SeatStatus;
import com.bookmyshow.factory.BookingFactory;
import com.bookmyshow.model.Booking;
import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;
import com.bookmyshow.model.User;
import com.bookmyshow.observer.NotificationService;
import com.bookmyshow.strategy.PricingStrategy;

import java.util.List;

public class BookingService {

    private final PricingStrategy pricingStrategy;
    private final NotificationService notificationService;

    public BookingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        this.notificationService = new NotificationService();
    }

    public Booking bookSeats(User user, Show show, List<Seat> requestedSeats) {
        // Validate the requested seats
        if (requestedSeats == null || requestedSeats.isEmpty()) {
            throw new IllegalArgumentException("No seats requested for booking.");
        }
        synchronized (show) {
            // Check if the requested seats are available
            for (Seat seat : requestedSeats) {
                if (!show.isAvailable(seat)) {
                    throw new IllegalArgumentException("Requested seat " + seat.getSeatId() + " is not available.");
                }
            }

            // Mark the requested seats as booked
            for (Seat seat : requestedSeats) {
                show.getSeatMap().put(seat, SeatStatus.BOOKED);
            }
        }
        // Calculate the total amount for the booking
        double amount = pricingStrategy.calculatePrice(requestedSeats);
        Booking booking = BookingFactory.createBooking(user, show, show.getScreen(), requestedSeats, amount);

        // Notify the user about the booking
        notificationService.registerObserver(user.getNotificationObserver());
        notificationService.notifyObservers("Booking confirmed for show: " + show.getShowId() + " and Movie name: " + show.getMovie().getMovieName() + ", Total Amount: " + amount);
        return booking;
    }
}
