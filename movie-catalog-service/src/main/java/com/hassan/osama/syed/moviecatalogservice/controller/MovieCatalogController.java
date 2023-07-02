package com.hassan.osama.syed.moviecatalogservice.controller;

import com.hassan.osama.syed.moviecatalogservice.model.CatalogItem;
import com.hassan.osama.syed.moviecatalogservice.model.Movie;
import com.hassan.osama.syed.moviecatalogservice.model.Rating;
import com.hassan.osama.syed.moviecatalogservice.model.dto.RatingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    private final static String MOVIE_INFO_SERVICE_URL = "http://localhost:8081/movies/";
    private final static String RATING_INFO_SERVICE_URL = "http://localhost:8082/ratings/users/";
    private final RestTemplate restTemplate;

    public MovieCatalogController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalogById(@PathVariable String userId) {
        RatingDto ratingDto = restTemplate.getForObject(RATING_INFO_SERVICE_URL+"foo", RatingDto.class);

        return ratingDto.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject(MOVIE_INFO_SERVICE_URL + rating.getMovieId(), Movie.class);
//                    Movie movie = webClientBuilder.build()
//                            .get()
//                            .uri(MOVIE_INFO_SERVICE_URL + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
                    return new CatalogItem(movie.getName(), "description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
