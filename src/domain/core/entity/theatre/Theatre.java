package domain.core.entity.theatre;

import domain.core.entity.BaseEntity;
import domain.core.entity.Movie;
import domain.core.entity.show.Show;
import domain.core.entity.screen.Screen;
import domain.core.valueobject.City;
import domain.core.valueobject.Money;
import domain.core.valueobject.TheatreId;
import domain.event.DomainEvent;
import domain.event.ShowAddedEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Theatre extends BaseEntity<TheatreId> implements TheatreObservable {
    private List<Screen> screens;
    private String name;
    private City city;
    private List<TheatreObserver> observers;

    public Theatre(int theatreId, String name, City city) {
        super(new TheatreId(theatreId));
        this.name = name;
        this.city = city;
        this.screens = new ArrayList<>();
        this.observers = new LinkedList<>();
    }

    public void addScreens(List<Screen> screens) {
        this.screens.addAll(screens);
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public void addShow(int screenId, int showId, Movie movie, double price, LocalDateTime startTime) {
        Screen screen = screens.get(screenId);
        if (screen != null) {
            Show show = new Show.Builder()
                    .id(showId)
                    .theatre(this)
                    .screen(screen)
                    .movie(movie)
                    .startTime(startTime)
                    .price(new Money(BigDecimal.valueOf(price)))
                    .build();
            screen.addShow(show);
            notifyObservers(new ShowAddedEvent(show));
        }
    }

    @Override
    public void addObserver(TheatreObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(TheatreObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(DomainEvent event) {
        observers.forEach(observer -> observer.update(event));
    }
}
