package domain.core.entity.ticket;

import java.util.List;

public class TicketManager {
    private List<Ticket> tickets;

    public TicketManager(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void lockSeats() {
        tickets.forEach(Ticket::lockSeat);
    }

    public void unlockSeats() {
        tickets.forEach(Ticket::unlockSeat);
    }

    public void reserveSeats() {
        tickets.forEach(Ticket::reserveSeat);
    }
}
