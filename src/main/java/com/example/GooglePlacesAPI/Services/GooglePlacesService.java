package com.example.GooglePlacesAPI.Services;

import com.example.GooglePlacesAPI.Entities.LandmarkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GooglePlacesService {

    public LandmarkResponse parseGooglePlacesResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, LandmarkResponse.class);
    }

    // Add other methods to manipulate data as needed
}
