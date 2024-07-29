package com.example.GooglePlacesAPI.controller;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Services.GooglePlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class GooglePlacesController {

    @Autowired
    private GooglePlacesService googlePlacesService;

    @GetMapping("/hotel")
    public String getHotels(
            @RequestParam String text,
            @RequestParam int radius
    ) throws IOException {
        LandmarkResponse landmarkResponse = googlePlacesService.getLandmarks(text);
        if (landmarkResponse.getResults().isEmpty()) {
            return "No results found.";
        }

        double lat = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLat();
        double lng = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLng();

        return googlePlacesService.getNearbyHotels(lat, lng, radius);
    }
}