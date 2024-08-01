package com.example.GooglePlacesAPI.controller;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Services.GooglePlacesService;
import com.example.GooglePlacesAPI.exceptions.InvalidLandmarkException;
import com.example.GooglePlacesAPI.exceptions.InvalidRadiusException;
import com.example.GooglePlacesAPI.exceptions.NoResultsFoundException;
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
            @RequestParam(required = false) Integer radius
    ) throws IOException, InvalidRadiusException, InvalidLandmarkException, NoResultsFoundException {
        if(!googlePlacesService.isValidLandmark(text)){
            throw new InvalidLandmarkException();
        }
        if(!googlePlacesService.isValidRadius(radius)){
            throw new InvalidRadiusException();
        }
        LandmarkResponse landmarkResponse = googlePlacesService.getLandmarks(text);
        if (landmarkResponse.getResults().isEmpty() || landmarkResponse.getResults().size() == 3) {
            throw new NoResultsFoundException();
        }

        double lat = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLat();
        double lng = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLng();

        return googlePlacesService.getNearbyHotels(lat, lng, radius);
    }
}