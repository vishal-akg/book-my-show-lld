package domain.core.entity.booking;

import domain.core.entity.BaseEntity;
import domain.core.entity.seat.Seat;
import domain.core.entity.show.Show;
import domain.core.entity.booking.state.BookingCancelledState;
import domain.core.entity.ticket.Ticket;
import domain.core.entity.User;
import domain.core.entity.booking.state.BookingPaidState;
import domain.core.entity.booking.state.BookingPendingState;
import domain.core.entity.booking.state.BookingState;
import domain.core.entity.ticket.TicketManager;
import domain.core.payment.strategy.Payment;
import domain.core.valueobject.BookingId;
import domain.core.valueobject.Money;

import java.time.LocalDateTime;
import java.util.List;

public class Booking extends BaseEntity<BookingId> {
    private Show show;
    private LocalDateTime bookingTime;
    private User user;
    private TicketManager ticketManager;
    private BookingState bookingState;
    private Money price;
    private Payment payment;

    public Show getShow() {
        return show;
    }

    public List<Ticket> getTickets() {
        return ticketManager.getTickets();
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public User getUser() {
        return user;
    }

    public BookingState getBookingState() {
        return bookingState;
    }

    public Money getPrice() {
        return price;
    }

    private Booking(Builder builder) {
        super(new BookingId(builder.id));
        bookingTime = LocalDateTime.now();
        user = builder.user;
        price = builder.price;
        show = builder.show;
        payment = builder.payment;
        bookingState = new BookingPendingState(this);
        ticketManager = new TicketManager(builder.tickets);
    }

    public void expire() {
        if (bookingState instanceof BookingPendingState) {
            ticketManager.unlockSeats();
            bookingState = new BookingCancelledState(this);
        }
    }

    public void pay() {
        System.out.println(bookingState);
        bookingState.pay();
    }

    public void setBookingState(BookingState state) {
        this.bookingState = state;
        if (state instanceof BookingPaidState) {
            ticketManager.reserveSeats();
        } else if (state instanceof BookingCancelledState) {
            ticketManager.unlockSeats();
        }
    }

    public static class Builder {
        private int id;
        private Show show;
        private User user;
        private List<Ticket> tickets;
        private Money price;
        private Payment payment;

        public Builder id(int val) {
             id = val;
             return this;
        }

        public Builder show(Show val) {
            show = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder tickets(List<Ticket> val) {
            tickets = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder payment(Payment val) {
            payment = val;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
