package com.example.GooglePlacesAPI.Hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisplayName{

	@JsonProperty("text")
	private String text;

	@JsonProperty("languageCode")
	private String languageCode;

	public String getText(){
		return text;
	}

	public String getLanguageCode(){
		return languageCode;
	}
}