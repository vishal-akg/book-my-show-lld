package domain.core.entity.screen;

import domain.core.entity.BaseEntity;
import domain.core.entity.show.Show;
import domain.core.entity.theatre.Theatre;
import domain.core.valueobject.ScreenId;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen extends BaseEntity<ScreenId> {
    private List<Show> shows;
    private SeatConfiguration seatConfiguration;

    public Screen(int id, SeatConfiguration seatConfiguration) {
        super(new ScreenId(id));
        this.shows = new ArrayList<>();
        this.seatConfiguration = seatConfiguration;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }

    public SeatConfiguration getSeatConfiguration() {
        return seatConfiguration;
    }

}
