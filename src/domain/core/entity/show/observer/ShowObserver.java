package domain.core.entity.show.observer;

import domain.event.DomainEvent;

public interface ShowObserver {
    void update(DomainEvent event);
}
