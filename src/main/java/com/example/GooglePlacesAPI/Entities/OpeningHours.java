package com.example.GooglePlacesAPI.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningHours{

	@JsonProperty("open_now")
	private boolean openNow;

	public boolean isOpenNow(){
		return openNow;
	}
}