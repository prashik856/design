package com.prashik.movie.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// If we run this application when already movie-catalog-service is running, we will
// be running into port issues (the port is already in use)

@SpringBootApplication
public class MovieInfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieInfoServiceApplication.class, args);
    }

}
