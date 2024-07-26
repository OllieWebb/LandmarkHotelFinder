package com.example.GooglePlacesAPI.Services;

import com.example.GooglePlacesAPI.LandmarkModel.LandmarkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GooglePlacesService {

    public LandmarkResponse parseGooglePlacesResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LandmarkResponse landmarkResponse = objectMapper.readValue(jsonResponse, LandmarkResponse.class);
        LandmarkResponse landmarkResponse1 = new LandmarkResponse();

        double lat = landmarkResponse1.getResults().getFirst().getGeometry().getLocation().getLat();
        double lng = landmarkResponse1.getResults().getFirst().getGeometry().getLocation().getLng();

        return objectMapper.readValue(jsonResponse, LandmarkResponse.class);
    }

    // Add other methods to manipulate data as needed
}
