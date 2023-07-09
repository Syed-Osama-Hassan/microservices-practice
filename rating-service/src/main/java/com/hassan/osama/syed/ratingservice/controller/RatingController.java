package com.hassan.osama.syed.ratingservice.controller;

import com.hassan.osama.syed.ratingservice.model.Rating;
import com.hassan.osama.syed.ratingservice.model.dto.RatingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatingById(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public RatingDto getRatingsByUserId(@PathVariable String userId) {
        List<Rating> ratings = List.of(
                new Rating("Italian Spiderman", 4),
                new Rating("Superman, Spiderman or Batman", 5)
        );
        return new RatingDto(ratings);
    }
}
