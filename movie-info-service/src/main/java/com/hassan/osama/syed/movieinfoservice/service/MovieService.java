package com.hassan.osama.syed.movieinfoservice.service;

import com.hassan.osama.syed.movieinfoservice.dto.MovieDto;
import com.hassan.osama.syed.movieinfoservice.models.Movie;
import com.hassan.osama.syed.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {
    @Value("${api.key}")
    private String apiKey;
    private static final String MOVIE_API = "https://www.omdbapi.com/?s=#title#&apiKey=#apiKey#";
    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovieById(String movieId) {
        String url = MOVIE_API.replace("#apiKey#", this.apiKey)
                .replace("#title#", movieId);
        MovieDto movieDto = restTemplate.getForObject(url, MovieDto.class);
        MovieSummary movieSummary = movieDto.getMovieSummaries().get(0);
        return new Movie(movieSummary.getImdbID(), movieSummary.getTitle());
    }
}
