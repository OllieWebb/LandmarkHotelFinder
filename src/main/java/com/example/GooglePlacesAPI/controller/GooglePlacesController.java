package com.example.GooglePlacesAPI.controller;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Services.GooglePlacesService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class GooglePlacesController {

    @Value("${google.places.api.key}")
    private String apiKey;

    private GooglePlacesService googlePlacesService = new GooglePlacesService();

    @Operation(summary = "Get places based on location and radius")
    @GetMapping("/testing")
    public String getLandmark(
            @Parameter(description = "text for searching") @RequestParam String text,
            @Parameter(description = "Radius in meters") @RequestParam int radius
    ) throws IOException {
        String urlLandmark = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + text + "&key="+apiKey;
        RestTemplate restTemplateLandmark = new RestTemplate();
        String jsonResponseLandmark = restTemplateLandmark.getForObject(urlLandmark, String.class);

        LandmarkResponse landmarkResponse = googlePlacesService.parseLandmarkResponse(jsonResponseLandmark);

        double lat = landmarkResponse.getResults().getFirst().getGeometry().getLocation().getLat();
        double lng = landmarkResponse.getResults().getFirst().getGeometry().getLocation().getLng();

        String location = lat+","+lng;
        String urlHotel = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location + "&radius=" + radius + "&type=hotel&key="+apiKey;

        RestTemplate restTemplateHotel = new RestTemplate();
        return restTemplateHotel.getForObject(urlHotel, String.class);
    }
}
