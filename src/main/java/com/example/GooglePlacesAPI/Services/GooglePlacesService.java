package com.example.GooglePlacesAPI.Services;

import com.example.GooglePlacesAPI.Hotel.HotelResponse;
import com.example.GooglePlacesAPI.Hotel.PlacesItem;
import com.example.GooglePlacesAPI.HotelsModel.ResultsItem;
import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.example.GooglePlacesAPI.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GooglePlacesService {

    @Value("${google.places.api.key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public LandmarkResponse getLandmarks(String text) throws IOException {
        String urlLandmark = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + text + "&key=" + apiKey;
        String jsonResponseLandmark = restTemplate.getForObject(urlLandmark, String.class);
        return Utils.parseLandmarkResponse(jsonResponseLandmark);
    }

    public String getNearbyHotels(double lat, double lng, int radius) throws IOException {
        String urlNearbySearch = "https://places.googleapis.com/v1/places:searchNearby";
        String requestBody = String.format(
                "{\"includedTypes\": [\"hotel\"], \"maxResultCount\": 10, \"locationRestriction\": {\"circle\": {\"center\": {\"latitude\": %f, \"longitude\": %f}, \"radius\": %f}}}",
                lat, lng, (double) radius
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Goog-Api-Key", apiKey);
        headers.set("X-Goog-FieldMask", "places.displayName,places.rating,places.websiteUri,places.reviews,places.internationalPhoneNumber,places.formattedAddress");

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(urlNearbySearch, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    public List<String> getNearbyHotelNames(double lat, double lng, int radius) throws IOException {
        String jsonResponse = getNearbyHotels(lat, lng, radius);
        List<String> hotelNames = new ArrayList<>();

        try {
            HotelResponse hotelResponse = parseHotelResponse(jsonResponse);
            for (PlacesItem hotel : hotelResponse.getPlaces()) {
                hotelNames.add(hotel.getDisplayName().getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelNames;
    }

    private HotelResponse parseHotelResponse(String jsonResponse) throws IOException {
        return objectMapper.readValue(jsonResponse, HotelResponse.class);
    }
    public boolean isValidLandmark(String landmark){
        if (landmark == null || landmark.isEmpty()) {
            return false;
        }
        if (!landmark.trim().equals(landmark)) {
            return false;
        }
        for (char c : landmark.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return false;
            }
        }
        return true;
    }
    public boolean isValidRadius(Integer radius) {
        if (radius == null) {
            return false;
        }
        return radius > 0;
    }
}