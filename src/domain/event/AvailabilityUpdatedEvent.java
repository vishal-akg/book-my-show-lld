package domain.event;

import domain.core.entity.theatre.Theatre;

public class AvailabilityUpdatedEvent implements DomainEvent{
    private Theatre theatre;

    public AvailabilityUpdatedEvent(Theatre theatre) {
        this.theatre = theatre;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
