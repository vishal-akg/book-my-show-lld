package domain.core.entity.ticket;

import domain.core.entity.BaseEntity;
import domain.core.entity.seat.Seat;
import domain.core.valueobject.TicketId;

public class Ticket extends BaseEntity<TicketId> {
    private Seat seat;

    public Ticket(TicketId id, Seat seat) {
        super(id);
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public void lockSeat() {
        seat.lock();
    }

    public void unlockSeat() {
        seat.unlock();
    }

    public void reserveSeat() {
        seat.reserve();
    }
}
