package com.example.GooglePlacesAPI.LandmarkModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry{

	@JsonProperty("viewport")
	private Viewport viewport;

	@JsonProperty("location")
	private Location location;

	public Viewport getViewport(){
		return viewport;
	}

	public Location getLocation(){
		return location;
	}
}