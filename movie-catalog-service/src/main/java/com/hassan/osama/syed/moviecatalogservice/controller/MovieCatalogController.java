package com.hassan.osama.syed.moviecatalogservice.controller;

import com.hassan.osama.syed.moviecatalogservice.model.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalogById(@PathVariable String userId) {
        return List.of(new CatalogItem("Star Wars", "This is the first movie", 4));
    }
}
