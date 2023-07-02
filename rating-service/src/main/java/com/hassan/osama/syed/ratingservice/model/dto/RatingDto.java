package com.hassan.osama.syed.ratingservice.model.dto;

import com.hassan.osama.syed.ratingservice.model.Rating;

import java.util.List;

public class RatingDto {
    private List<Rating> ratings;

    public RatingDto() {}

    public RatingDto(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
