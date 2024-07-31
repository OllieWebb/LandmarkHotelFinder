package com.example.GooglePlacesAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HotelExceptionAdvice {
    @ExceptionHandler(InvalidRadiusException.class)
    public ResponseEntity<String> invalidRadius(InvalidRadiusException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLandmarkException.class)
    public ResponseEntity<String> invalidLandmark(InvalidLandmarkException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
