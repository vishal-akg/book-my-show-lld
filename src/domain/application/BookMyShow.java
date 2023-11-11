package domain.application;

import domain.core.entity.Movie;
import domain.core.entity.User;
import domain.core.entity.booking.Booking;
import domain.core.entity.seat.Seat;
import domain.core.entity.show.Show;
import domain.core.entity.catalogue.ShowCatalogue;
import domain.core.entity.show.observer.ShowObserver;
import domain.core.entity.theatre.Theatre;
import domain.core.entity.theatre.TheatreObserver;
import domain.core.entity.ticket.Ticket;
import domain.core.payment.strategy.Payment;
import domain.core.valueobject.City;
import domain.core.valueobject.Money;
import domain.core.valueobject.TicketId;
import domain.event.DomainEvent;
import domain.event.ShowAddedEvent;
import domain.event.ShowStartedEvent;

import javax.xml.catalog.Catalog;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BookMyShow implements TheatreObserver, ShowObserver {
    private static BookMyShow instance;
    private Map<City, ShowCatalogue> catalogueMap;
    private List<Theatre> theatres;
    private List<Movie> movies;
    private List<City> cities;
    private List<Booking> bookings;

    private BookMyShow() {
        catalogueMap = new TreeMap<>();
        theatres = new LinkedList<>();
        movies = new LinkedList<>();
        cities = new LinkedList<>();
        bookings = new ArrayList<>();
    }

    public static BookMyShow getInstance() {
        if (instance == null) {
            synchronized (BookMyShow.class) {
                if (instance == null) {
                    instance = new BookMyShow();
                }
            }
        }
        return instance;
    }

    @Override
    public void update(DomainEvent event) {
        if (event instanceof ShowAddedEvent) {
            Show show = ((ShowAddedEvent) event).getShow();
            ShowCatalogue catalogue = catalogueMap.get(show.getTheatre().getCity());
            catalogue.addShow(show);
        } else if (event instanceof ShowStartedEvent) {
            Show show = ((ShowStartedEvent) event).getShow();
            ShowCatalogue catalogue = catalogueMap.get(show.getTheatre().getCity());
            catalogue.removeShow(show);
        }
    }

    public void addTheatres(List<Theatre> theatres) {
        this.theatres.addAll(theatres);
        theatres.forEach(theatre -> theatre.addObserver(this));
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public void addCities(List<City> cities) {
        this.cities.addAll(cities);
        cities.forEach(city -> {
            catalogueMap.putIfAbsent(city, new ShowCatalogue());
        });
    }

    public List<Show> getMovieShows(City city, Movie movie) {
        ShowCatalogue catalog = this.catalogueMap.get(city);
        if (catalog != null) {
            return catalog.getShows(movie);
        }
        return null;
    }

    public Booking createBooking(User user, Show show, Payment payment, List<Seat> seats) {
        List<Ticket> tickets = seats.stream()
                .map(seat -> new Ticket(
                        new TicketId(seat.getRow() * 10 + seat.getColumn()),
                        seat
                )).toList();
        Booking booking = new Booking.Builder()
                .id(bookings.size())
                .tickets(tickets)
                .price(new Money(BigDecimal.valueOf(show.getPrice() * tickets.size())))
                .show(show)
                .user(user)
                .payment(payment)
                .build();

        this.bookings.add(booking);
        user.addBooking(booking);

        return booking;
    }
}
