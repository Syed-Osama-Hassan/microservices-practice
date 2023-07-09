package com.hassan.osama.syed.moviecatalogservice.service;

import com.hassan.osama.syed.moviecatalogservice.model.CatalogItem;
import com.hassan.osama.syed.moviecatalogservice.model.Movie;
import com.hassan.osama.syed.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieInfoService {
    private final static String MOVIE_INFO_SERVICE_URL = "http://movie-info-service/movies/";
    private final RestTemplate restTemplate;

    public MovieInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject(MOVIE_INFO_SERVICE_URL + rating.getMovieId(), Movie.class);
//                    Movie movie = webClientBuilder.build()
//                            .get()
//                            .uri(MOVIE_INFO_SERVICE_URL + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
        return new CatalogItem(movie.getName(), "description", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Fallback Response", "", 0);
    }
}
