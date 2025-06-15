import com.bookmyshow.cache.MovieCache;
import com.bookmyshow.enums.SeatType;
import com.bookmyshow.model.Booking;
import com.bookmyshow.model.Movie;
import com.bookmyshow.model.Screen;
import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;
import com.bookmyshow.model.Theatre;
import com.bookmyshow.model.User;
import com.bookmyshow.service.BookingService;
import com.bookmyshow.strategy.SeatTypeBasedPricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMyShowMain {
    public static void main(String[] args) {
        // create user
        User jiten = new User("Jiten Pandey", "jiten@mail.com");
        // create movie
        Movie movie = new Movie("Avengers: Endgame");
        MovieCache movieCache = new MovieCache();
        movieCache.addMovie(movie);

        //create seats
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            seats.add(new Seat("A" + i, SeatType.REGULAR, 0, i));
        }

        Screen screen = new Screen("SC1", seats);
        Theatre theatre = new Theatre("T1", "PVR", "Bangalore", List.of(screen));

        // create show
        Show show = new Show("SH1", movie, screen, LocalDateTime.now().plusHours(2));

        // book a seat
        BookingService bookingService = new BookingService(new SeatTypeBasedPricingStrategy());
        Booking b1 = bookingService.bookSeats(jiten, show, List.of(seats.get(0), seats.get(1)));
        Booking b2 = bookingService.bookSeats(jiten, show, List.of(seats.get(0), seats.get(3)));


    }
}