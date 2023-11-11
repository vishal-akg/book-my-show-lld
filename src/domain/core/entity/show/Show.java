package domain.core.entity.show;

import domain.core.entity.BaseEntity;
import domain.core.entity.Movie;
import domain.core.entity.screen.Screen;
import domain.core.entity.screen.SeatConfiguration;
import domain.core.entity.seat.Seat;
import domain.core.entity.theatre.Theatre;
import domain.core.valueobject.Money;
import domain.core.valueobject.ShowId;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Show extends BaseEntity<ShowId> {
    private Movie movie;
    private Screen screen;
    private Theatre theatre;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Seat> seats;
    private Money price;

    private Show(Builder builder) {
        super(new ShowId(builder.id));
        this.theatre = builder.theatre;
        this.screen = builder.screen;
        this.movie = builder.movie;
        this.price = builder.price;
        this.startTime = builder.startTime;
        this.endTime = builder.startTime.plusMinutes(movie.getDuration());
        this.seats = new ArrayList<>();
        SeatConfiguration seatConfiguration = screen.getSeatConfiguration();

        for (int i = 0; i < seatConfiguration.getRows(); i++ ) {
            for (int j = 0; j < seatConfiguration.getColumns(); j++) {
                seats.add(new Seat(i, j));
            }
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = scheduler.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, LocalDateTime.now().until(startTime, ChronoUnit.SECONDS), TimeUnit.SECONDS);
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getPrice() {
        return price.getAmount();
    }

    public static class Builder {
        private Integer id;
        private Movie movie;
        private LocalDateTime startTime;
        private Screen screen;
        private Theatre theatre;
        private Money price;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder movie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder screen(Screen screen) {
            this.screen = screen;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder theatre(Theatre theatre) {
            this.theatre = theatre;
            return this;
        }

        public Show build() {
            return new Show(this);
        }
    }
}
