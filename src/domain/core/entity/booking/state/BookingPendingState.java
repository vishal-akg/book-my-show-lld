package domain.core.entity.booking.state;

import domain.core.entity.booking.Booking;

import java.util.Timer;
import java.util.TimerTask;

public class BookingPendingState implements BookingState {
    private Booking booking;

    public BookingPendingState(Booking booking) {
        this.booking = booking;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                booking.expire();
            }
        }, 10000);
    }

    @Override
    public void pay() {
        booking.setBookingState(new BookingPaidState(booking));
    }

    @Override
    public void cancel() {

    }

    @Override
    public String toString() {
        return "Payment Pending!";
    }
}
