package com.example.GooglePlacesAPI.exceptions;

public class NoResultsFoundException extends Exception{
    public NoResultsFoundException(){
        super("No results found using current search requirements");
    }
}
