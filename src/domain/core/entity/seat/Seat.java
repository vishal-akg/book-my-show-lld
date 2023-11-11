package domain.core.entity.seat;

import domain.core.entity.seat.state.SeatAvailableState;
import domain.core.entity.seat.state.SeatState;

import java.util.Objects;

public class Seat {
    private int row;
    private int column;
    private SeatState seatState;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.seatState = new SeatAvailableState();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatState getSeatState() {
        return seatState;
    }

    @Override
    public String toString() {
        return row + ":" + column;
    }

    public void lock() {
        seatState.lock();
    }

    public void unlock() {
        seatState.unlock();
    }

    public void reserve() {
        seatState.reserve();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
