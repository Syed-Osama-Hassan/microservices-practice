package com.hassan.osama.syed.moviecatalogservice.controller;

import com.hassan.osama.syed.moviecatalogservice.model.CatalogItem;
import com.hassan.osama.syed.moviecatalogservice.model.dto.RatingDto;
import com.hassan.osama.syed.moviecatalogservice.service.MovieInfoService;
import com.hassan.osama.syed.moviecatalogservice.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    private final RatingService ratingService;
    private final MovieInfoService movieInfoService;

    public MovieCatalogController(RatingService ratingService, MovieInfoService movieInfoService) {
        this.ratingService = ratingService;
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalogById(@PathVariable String userId) {
        RatingDto ratingDto = ratingService.getRating(userId);

        return ratingDto.getRatings()
                .stream()
                .map(movieInfoService::getCatalogItem)
                .collect(Collectors.toList());
    }
}
