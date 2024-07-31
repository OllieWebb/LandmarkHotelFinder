package com.example.GooglePlacesAPI.exceptions;

public class InvalidLandmarkException extends Exception{
    public InvalidLandmarkException(){
        super("Invalid landmark. Please input valid characters and without leading spaces");
    }
}
