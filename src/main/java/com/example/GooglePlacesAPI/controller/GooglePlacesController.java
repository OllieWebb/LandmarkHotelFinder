package com.example.GooglePlacesAPI.controller;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Services.GooglePlacesService;
import com.example.GooglePlacesAPI.Services.SeleniumService;
import com.example.GooglePlacesAPI.exceptions.InvalidLandmarkException;
import com.example.GooglePlacesAPI.exceptions.InvalidRadiusException;
import com.example.GooglePlacesAPI.exceptions.NoResultsFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GooglePlacesController {

    @Autowired
    private GooglePlacesService googlePlacesService;

    @Autowired
    private SeleniumService seleniumService;

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

    @Operation(summary = "Get availability of hotels based on location and radius")
    @GetMapping("/available")
    public Map<String, Boolean> getAvailability(
            @Parameter(description = "text for searching") @RequestParam String text,
            @Parameter(description = "Radius in meters") @RequestParam int radius,
            @Parameter(description = "Check in date YYYY-MM-DD format") @RequestParam String checkInDate,
            @Parameter(description = "Check out date YYYY-MM-DD format") @RequestParam String checkOutDate

    ) throws IOException {
        LandmarkResponse landmarkResponse = googlePlacesService.getLandmarks(text);
        if (landmarkResponse.getResults().isEmpty()) {
            return new HashMap<>();
        }

        double lat = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLat();
        double lng = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLng();
        List<String> hotelNames = googlePlacesService.getNearbyHotelNames(lat, lng, radius);
        Map<String, Boolean> availabilityMap = new HashMap<>();
        for (String hotel : hotelNames){
            Map<String, Boolean> hotelAvailability = seleniumService.getHotelAvailability(hotel, checkInDate, checkOutDate);
            availabilityMap.putAll(hotelAvailability);
        }
        return availabilityMap;
    }
}