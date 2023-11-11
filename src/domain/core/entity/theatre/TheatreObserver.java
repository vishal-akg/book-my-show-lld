package domain.core.entity.theatre;

import domain.event.DomainEvent;

public interface TheatreObserver {
    void update(DomainEvent event);
}
