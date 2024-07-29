package com.example.GooglePlacesAPI.controller;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Services.GooglePlacesService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

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

        double lat = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLat();
        double lng = landmarkResponse.getResults().get(0).getGeometry().getLocation().getLng();

        String urlHotel = "https://places.googleapis.com/v1/places:searchNearby?fields=*";

        // Create request body
        String requestBody = String.format(
                "{\"includedTypes\": [\"restaurant\"], \"locationRestriction\": {\"circle\": {\"center\": {\"latitude\": %f, \"longitude\": %f}, \"radius\": %d}}}",
                lat, lng, radius
        );

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + apiKey);

        // Create entity with headers and body
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplateHotel = new RestTemplate();
        ResponseEntity<String> response = restTemplateHotel.exchange(urlHotel, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
