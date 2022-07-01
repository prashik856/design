package com.prashik.movie.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

// Tells our java code that this is a sprint bool application
// Most Sprint applications has main method.
@SpringBootApplication
public class MovieCatalogServiceApplication {

    // We can run this main method, but it doesn't have anything
    // if we open localhost:8080, we will get an error page about not finding an error page.
    // Basically chrome will search if I can find anything at "/". But it doesn't. So it searches for "/error", which is
    // again not implemented. Thus chrome shows error of not finding error page.
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
