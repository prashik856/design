package com.prashik.movie.catalog.resources;

import com.prashik.movie.catalog.models.CatalogItem;
import com.prashik.movie.catalog.models.Movie;
import com.prashik.movie.catalog.models.Rating;
import com.prashik.movie.catalog.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Just with this one annotation, now this class is a rest controller
// We also want to tell sprintboot that if /api/v1/catalog api is called, it needs to bootup this API
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    // We are telling Sprint boot that we already have a bean of type RestTemplate. Please inject us that.
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // We want Springboot to treat this method as an API
    // it should be available at /api/v1/catalog/[userId]
    // The curly braces around userID tells springboot that it is a parameter.
    // the PathVariable Annotation tells getCatalog method to pass the userID variable
    // api request to method.

    // Again, this method should not return a list
    @RequestMapping("/{userId}")
    List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        // Create new resttemplate to store the response
        // It is bad to create restTemplate instance inside getCatalog method.
        // Why? Because it is created and disposed of every time we make a rest call. This is bad.
        // We should be creating the restTemplate object once and reusing it.
        // How do we do that? -> We create a Bean.
        // We use Dependency Injection to create this instance.
//        RestTemplate restTemplate = new RestTemplate();

        // For asynchronous calls

        // Before getting the Catalog, we need to make sure that
        // Get all rated movie IDs
        // Assume that this is the response that we got from movie-ratings-service
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );

        // Rather than having a hard coded value of ratings, let's say we want to make a rest call using restTemplate
        // Which class do we pass? cannot pass a list.
//        List<Rating> ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId,
//                ParameterizedType<List<Rating>>())
        // We need to use parameterizedType as above when we don't have a single class. This makes things complicated.
        // Rather, we should always have a single class
//        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        // Rather than using a whole url, we will use service discovery here
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId,
                UserRating.class);

        // For each movie ID, call move info and get movie details
        // These names and description whould actually come from the api.
        // We need to make a rest call to movie-info-service
        // then put these all together
        // An actual rest call is here.
        return userRating.getRating().stream().map(rating -> {
            // We are doing a lot of things wrong here.
            // First thing is hardcoding the url. This is plainly bad. Why? -> It should actually be discovering the service.
            // The URL should be coming from somewhere else.
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            // The call above is synchronous call. We can do the same with asynchronous call.
            // We just need to define a lambda, once we get the response. Lambda is just a things we need to do once
            // We get the response from api

            // Now, we need async calls using webClientBuilder
            // We are doing a get call here. For post, we do build().post().
            // .build() method is giving us a client
            // Then we have a chaining mechanism.
            // We then do a get method (Post if our api is post, or put or delete)
            // Then we give the uri
            // Then we retrieve
            // bodyToMono is asking client to convert the body received as response to Movie class.
            // What is Mono? -> Reactive way of saying that you will get an object back from the api call but not right away
            // This will happen sometime in the future (async call). Mono is kinda a promise.
            // Now, the getCatalog method is itself return a list of catalog items. That means spring needs to return
            // this item no matter what. That thus means that we need to wait around for the async call to complete
            // so that we can generate the list of catalog items.
            // This is why we are using block() method. This is to stop any executions till we receive the response.
            // We are async classes to do sync calls
//            Movie movie = webClientBuilder.build()
//                                            .get()
//                                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                                            .retrieve()
//                                            .bodyToMono(Movie.class)
//                                            .block();

            return new CatalogItem(movie.getName(), "Hard Coded Description", rating.getRating());
        }).collect(Collectors.toList());

//        List<CatalogItem> catalogItemList = new ArrayList<>();
//        catalogItemList.add(new CatalogItem("Transformers", "Move about robots and stuff", 3));
//        catalogItemList.add(new CatalogItem("Hangover", "Move about 4 frinds bucking around", 4));
//        catalogItemList.add(new CatalogItem("Harry Potter", "Magic boy", 4));
//        catalogItemList.add(new CatalogItem("Bad Boys", "Cop Story", 3));
//        catalogItemList.add(new CatalogItem("Deadpool", "Superhero Movie", 5));
//        catalogItemList.add(new CatalogItem("Spider-man", "Friendly neighbourhood Spidy", 6));
//        return catalogItemList;
    }
}
