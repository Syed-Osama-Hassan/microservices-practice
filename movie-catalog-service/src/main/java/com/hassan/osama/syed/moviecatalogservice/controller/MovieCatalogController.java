package com.hassan.osama.syed.moviecatalogservice.controller;

import com.hassan.osama.syed.moviecatalogservice.model.CatalogItem;
import com.hassan.osama.syed.moviecatalogservice.model.Movie;
import com.hassan.osama.syed.moviecatalogservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    private final static String MOVIE_INFO_SERVICE_URL = "http://localhost:8081/movies/";
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalogById(@PathVariable String userId) {
        List<Rating> ratings = List.of(new Rating("1", 4), new Rating("2", 5));

        return ratings.stream()
                .map(rating -> {
                    //Movie movie = restTemplate.getForObject(MOVIE_INFO_SERVICE_URL + rating.getMovieId(), Movie.class);
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri(MOVIE_INFO_SERVICE_URL + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getName(), "description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
