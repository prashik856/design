package com.prashik.movie.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

// Tells our java code that this is a sprint bool application
// Most Sprint applications has main method.
@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

    // loadBalanced annotation does service discovery with Load Balanced way
    // Now, we need to provide a service to restTemplate so that it will know which service to call.
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        // With the help of the annotation, this method will execute only once
        // and then anyone who wants restTemplate object, this bean will be used.
        return new RestTemplate();
    }

    // For Asynchronous Calls.
    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    // We can run this main method, but it doesn't have anything
    // if we open localhost:8080, we will get an error page about not finding an error page.
    // Basically chrome will search if I can find anything at "/". But it doesn't. So it searches for "/error", which is
    // again not implemented. Thus chrome shows error of not finding error page.
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
