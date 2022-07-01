package com.prashik.ratings.data.resources;

import com.prashik.ratings.data.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public List<Rating> getUserRating(@PathVariable("userId") String userId) {
        // Please don't do this, as it is returning a list of json. the root is a list.
        // This is not good though.
        return Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );
    }
}
