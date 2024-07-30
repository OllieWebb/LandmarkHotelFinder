package com.example.GooglePlacesAPI.Hotel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponse{

	@JsonProperty("places")
	private List<PlacesItem> places;

	public List<PlacesItem> getPlaces(){
		return places;
	}
}