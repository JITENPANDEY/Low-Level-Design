package com.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Movie {
    private final String movieId;
    private final String movieName;

    public Movie(String movieName) {
        this.movieId = UUID.randomUUID().toString();
        this.movieName = movieName;
    }
}
