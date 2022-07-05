package com.prashik.movie.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// If we run this application when already movie-catalog-service is running, we will
// be running into port issues (the port is already in use)

// Now, we want to have specific annotation to tell ourselves that this is a Eureka Client
// There is no need to this annotation to be there, but this is to tell us that this application behaves as a Eureka Client
// Adding org.springframework.cloud:spring-cloud-starter-netflix-eureka-client dependency is enough to
// make the application register itself to Eureka Discovery Server.
@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieInfoServiceApplication.class, args);
    }

}
