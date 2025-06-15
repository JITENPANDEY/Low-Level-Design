package com.bookmyshow.cache;

import com.bookmyshow.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieCache {
    private final Map<String, Movie> movieMap = new HashMap<>();

    public void addMovie(Movie movie) {
        movieMap.put(movie.getMovieId(), movie);
    }

    public Movie getMovieById(String id) {
        return movieMap.get(id);
    }
}
