package com.hassan.osama.syed.moviecatalogservice.service;

import com.hassan.osama.syed.moviecatalogservice.model.Rating;
import com.hassan.osama.syed.moviecatalogservice.model.dto.RatingDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RatingService {
    private final static String RATING_INFO_SERVICE_URL = "http://rating-service/ratings/users/";
    private final RestTemplate restTemplate;

    public RatingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackRating")
    public RatingDto getRating(String userId) {
        RatingDto ratingDto = restTemplate.getForObject(RATING_INFO_SERVICE_URL + userId, RatingDto.class);
        return ratingDto;
    }

    private RatingDto getFallbackRating(String userId) {
        return new RatingDto(List.of(new Rating("No Rating", 0)));
    }
}
