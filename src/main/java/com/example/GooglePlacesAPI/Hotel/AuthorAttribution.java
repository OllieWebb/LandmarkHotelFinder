package com.example.GooglePlacesAPI.Hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorAttribution{

	@JsonProperty("displayName")
	private String displayName;

	@JsonProperty("photoUri")
	private String photoUri;

	@JsonProperty("uri")
	private String uri;

	public String getDisplayName(){
		return displayName;
	}

	public String getPhotoUri(){
		return photoUri;
	}

	public String getUri(){
		return uri;
	}
}