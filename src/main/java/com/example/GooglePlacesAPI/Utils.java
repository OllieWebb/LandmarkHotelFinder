package com.example.GooglePlacesAPI;

import com.example.GooglePlacesAPI.Hotel.HotelResponse;
import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Utils {
    static ObjectMapper objectMapper = new ObjectMapper();
    public static LandmarkResponse parseLandmarkResponse(String jsonResponse) throws IOException {
        return objectMapper.readValue(jsonResponse, LandmarkResponse.class);
    }

    public static HotelResponse parseHotelResponse(String jsonResponse) throws IOException {
        return objectMapper.readValue(jsonResponse, HotelResponse.class);
    }
}
