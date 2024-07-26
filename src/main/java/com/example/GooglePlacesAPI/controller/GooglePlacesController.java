package com.example.GooglePlacesAPI.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GooglePlacesController {

    @Value("${google.places.api.key}")
    private String apiKey;

    @Operation(summary = "Get places based on location and radius")
    @GetMapping("/landmarks")
    public String getLandmarkCoordinates(
            @Parameter(description = "text for searching") @RequestParam String text
    ) {
        //String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants%20in%20Sydney&key=YOUR_API_KEY;
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + text + "&key="+apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @Operation(summary = "Get places based on location and radius")
    @GetMapping("/Places")
    public String getPlaces(
            @Parameter(description = "Location in the format of lat,lng") @RequestParam String location,
            @Parameter(description = "Radius in meters") @RequestParam int radius
    ) {
        //String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location + "&radius=" + radius + "&key=" + apiKey;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location + "&radius=" + radius + "&type=hotel&key="+apiKey;
        //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=YOUR_API_KEY
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
