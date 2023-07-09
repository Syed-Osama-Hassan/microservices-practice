package com.hassan.osama.syed.movieinfoservice.controller;

import com.hassan.osama.syed.movieinfoservice.models.Movie;
import com.hassan.osama.syed.movieinfoservice.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    private final MovieService movieService;

    public MovieInfoController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        return movieService.getMovieById(movieId);
    }
}
