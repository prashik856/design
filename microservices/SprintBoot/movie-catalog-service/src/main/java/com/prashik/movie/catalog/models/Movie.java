package com.prashik.movie.catalog.models;

public class Movie {
    private String movieId;
    private String name;

    // When we have a java unmarshall (deserializer from string to class), we need
    // an empty constructor. Else, we will be getting errors from the deserializer.
    public Movie() { };

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
