package domain.event;

import domain.core.entity.show.Show;

public class ShowStartedEvent implements DomainEvent{
    private Show show;

    public ShowStartedEvent(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }
}
