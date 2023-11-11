package domain.core.entity.catalogue;

import domain.core.entity.Movie;
import domain.core.entity.show.Show;

import java.util.*;

public class ShowCatalogue {
    private Map<Movie, List<Show>> showMap;

    public ShowCatalogue() {
        showMap = new HashMap<>();
    }

    public List<Show> getShows(Movie movie) {
        return showMap.get(movie);
    }

    public void addShow(Show show) {
        showMap.computeIfAbsent(show.getMovie(), k -> new ArrayList<>()).add(show);
    }

    public void removeShow(Show show) {
        showMap.computeIfPresent(show.getMovie(), (movie, movieShows) -> {
            movieShows.remove(show);
            if (movieShows.isEmpty()) {
                return null; // Remove the entry if the list is now empty
            }
            return movieShows;
        });
    }
}
