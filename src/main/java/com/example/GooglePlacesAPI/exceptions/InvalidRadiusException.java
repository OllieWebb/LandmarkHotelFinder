package com.example.GooglePlacesAPI.exceptions;

public class InvalidRadiusException extends Exception {
    public InvalidRadiusException() {
        super("The radius is invalid. It has to be a positive number, try again.");
    }
}
