package com.example.GooglePlacesAPI.Services;

import com.example.GooglePlacesAPI.HotelsModel.HotelResponse;
import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GooglePlacesService {

    public LandmarkResponse parseLandmarkResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, LandmarkResponse.class);
    }

    public HotelResponse parseHotelResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, HotelResponse.class);
    }

}
