package domain.event;

import domain.core.entity.show.Show;

public class ShowAddedEvent implements DomainEvent {
    private Show show;

    public ShowAddedEvent(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }
}
