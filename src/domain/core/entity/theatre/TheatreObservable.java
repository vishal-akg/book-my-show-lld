package domain.core.entity.theatre;

import domain.event.DomainEvent;

public interface TheatreObservable {
    void addObserver(TheatreObserver observer);
    void removeObserver(TheatreObserver observer);
    void notifyObservers(DomainEvent event);
}
