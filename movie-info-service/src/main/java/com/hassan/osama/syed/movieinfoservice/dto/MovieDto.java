package com.hassan.osama.syed.movieinfoservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hassan.osama.syed.movieinfoservice.models.MovieSummary;

import java.util.List;

public class MovieDto {
    @JsonProperty("Search")
    private List<MovieSummary> movieSummaries;

    public MovieDto() {}

    public List<MovieSummary> getMovieSummaries() {
        return movieSummaries;
    }

    public void setMovieSummaries(List<MovieSummary> movieSummaries) {
        this.movieSummaries = movieSummaries;
    }
}
