package domain.core.entity;

import domain.core.valueobject.Genre;
import domain.core.valueobject.MovieId;

import java.util.Set;

public class Movie extends BaseEntity<MovieId> {
    private String title;
    private int duration;
    private Set<Genre> genres;
    private String director;

    private Movie(Builder builder) {
        super(new MovieId(builder.id));
        title = builder.title;
        duration = builder.duration;
        genres = builder.genres;
        director = builder.director;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public static final class Builder {
        private int id;
        private String title;
        private int duration;
        private Set<Genre> genres;
        private String director;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder duration(int val) {
            duration = val;
            return this;
        }

        public Builder genres(Set<Genre> val) {
            genres = val;
            return this;
        }

        public Builder director(String val) {
            director = val;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
