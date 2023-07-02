package com.hassan.osama.syed.ratingservice.controller;

import com.hassan.osama.syed.ratingservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatingById(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

}
