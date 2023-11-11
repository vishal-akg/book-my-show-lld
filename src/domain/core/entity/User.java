package domain.core.entity;

import domain.core.entity.booking.Booking;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Booking> bookings;
    private String username;

    public User(String username) {
        this.username = username;
        this.bookings = new ArrayList<>();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getUsername() {
        return username;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
}
