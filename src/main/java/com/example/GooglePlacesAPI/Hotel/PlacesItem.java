package com.example.GooglePlacesAPI.Hotel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlacesItem{

	@JsonProperty("formattedAddress")
	private String formattedAddress;

	@JsonProperty("reviews")
	private List<ReviewsItem> reviews;

	@JsonProperty("displayName")
	private DisplayName displayName;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("websiteUri")
	private String websiteUri;

	@JsonProperty("internationalPhoneNumber")
	private String internationalPhoneNumber;

	public String getFormattedAddress(){
		return formattedAddress;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
	}

	public DisplayName getDisplayName(){
		return displayName;
	}

	public double getRating(){
		return rating;
	}

	public String getWebsiteUri(){
		return websiteUri;
	}

	public String getInternationalPhoneNumber(){
		return internationalPhoneNumber;
	}
}