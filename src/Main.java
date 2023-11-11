import domain.application.BookMyShow;
import domain.core.entity.Movie;
import domain.core.entity.User;
import domain.core.entity.booking.Booking;
import domain.core.entity.screen.*;
import domain.core.entity.seat.Seat;
import domain.core.entity.show.Show;
import domain.core.entity.theatre.Theatre;
import domain.core.entity.ticket.Ticket;
import domain.core.payment.factory.CreditCardPaymentFactory;
import domain.core.payment.strategy.CreditCardPayment;
import domain.core.payment.strategy.Payment;
import domain.core.valueobject.City;
import domain.core.valueobject.Genre;
import domain.core.valueobject.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BookMyShow bookMyShow = BookMyShow.getInstance();

        City ghaziabad = new City("Ghaziabad");
        City delhi = new City("Delhi");
        bookMyShow.addCities(List.of(ghaziabad, delhi));


        Theatre apsara = new Theatre(1, "Apsara Cinema", ghaziabad);
        apsara.addScreens(List.of(
                new FrontProjection(1, new SeatConfiguration(6, 20)),
                new RearProjection(2, new SeatConfiguration(10, 12))
                )
        );

        Theatre miraj = new Theatre(2, "Miraj Cinema M4U Cineplex", ghaziabad);
        miraj.addScreens(
                List.of(
                        new FrontProjection(1, new SeatConfiguration(6, 20))
                )
        );
        Theatre pvr = new Theatre(3, "PVR VVIP", ghaziabad);
        pvr.addScreens(List.of(
                new FrontProjection(1, new SeatConfiguration(10, 15)),
                new ThreeDimensionProjection(2, new SeatConfiguration(6, 10))
        ));

        Theatre akshara = new Theatre(4, "Akshara Theatre", delhi);
        akshara.addScreens(List.of(
                new FrontProjection(1, new SeatConfiguration(6, 20)),
                new RearProjection(2, new SeatConfiguration(10, 12))
        ));
        Theatre meghdoot = new Theatre(5, "Meghdoot Theatre", delhi);
        meghdoot.addScreens(List.of(
                new FrontProjection(1, new SeatConfiguration(6, 20)),
                new ThreeDimensionProjection(2, new SeatConfiguration(10, 12))
        ));
        bookMyShow.addTheatres(List.of(apsara, miraj, pvr, akshara, meghdoot));

        Movie marvels =  Movie.Builder.builder()
                .id(1)
                .title("The Marvels")
                .genres(Set.of(Genre.FANTASY, Genre.THRILLER))
                .director("Nila DaCosta")
                .duration(105)
                .build();

        Movie tiger3 =  Movie.Builder.builder()
                .id(2)
                .title("Tiger3")
                .genres(Set.of(Genre.THRILLER, Genre.ACTION))
                .director("Maneesh Sharma")
                .duration(150)
                .build();
        bookMyShow.addMovies(List.of(marvels, tiger3));

        miraj.addShow(0, 1,
                marvels,
                200.997,
                LocalDateTime.of(2023, 12, 1, 12, 0)
        );
        pvr.addShow(1, 1,
                marvels,
                300.29,
                LocalDateTime.of(2023, 12, 5, 9, 30)
        );

        List<Show> marvelMovieShows = bookMyShow.getMovieShows(ghaziabad, marvels);
        for (Show show: marvelMovieShows) {
            System.out.println("Show start time := " + show.getStartTime());
            System.out.println("Show end time := " + show.getEndTime());
            System.out.println("Show price := " + show.getPrice());
            System.out.println("Theatre := " + show.getTheatre().getName());
            System.out.println("Screen := " + show.getScreen());
        }

        Show marvelShow = marvelMovieShows.get(0);
        List<Seat> seats = marvelShow.getSeats();

        User vishal = new User("vishal_cms");
        CreditCardPaymentFactory creditCardPaymentFactory = new CreditCardPaymentFactory();
        Payment payment = creditCardPaymentFactory.createPayment("1234123412341234", "06/29");

        Booking booking = bookMyShow.createBooking(vishal, marvelShow, payment, List.of(seats.get(10), seats.get(11), seats.get(12)));
        booking.pay();
        System.out.println("Booking status := " + booking.getBookingState());
        for (Ticket ticket: booking.getTickets()) {
            System.out.println("Seat booked := " + ticket.getSeat());
        }
        System.out.println("Show start time := " + booking.getShow().getStartTime());
    }
}