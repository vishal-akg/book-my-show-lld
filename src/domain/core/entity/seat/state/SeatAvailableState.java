package domain.core.entity.seat.state;

public class SeatAvailableState implements SeatState{
    @Override
    public void lock() {

    }

    @Override
    public void reserve() {

    }

    @Override
    public void unlock() {

    }

    @Override
    public String toString() {
        return "available";
    }
}
