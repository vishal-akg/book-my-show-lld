package domain.core.entity.booking.state;

import domain.core.entity.booking.Booking;

public class BookingCancelledState implements BookingState{
    private Booking booking;

    public BookingCancelledState(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void pay() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String toString() {
        return "Cancelled!";
    }
}
