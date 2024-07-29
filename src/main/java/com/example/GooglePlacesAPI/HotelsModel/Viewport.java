package com.example.GooglePlacesAPI.HotelsModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Viewport{

	@JsonProperty("southwest")
	private Southwest southwest;

	@JsonProperty("northeast")
	private Northeast northeast;

	public Southwest getSouthwest(){
		return southwest;
	}

	public Northeast getNortheast(){
		return northeast;
	}
}