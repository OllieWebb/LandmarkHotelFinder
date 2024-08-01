package com.example.GooglePlacesAPI.Hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewsItem{

	@JsonProperty("originalText")
	private OriginalText originalText;

	@JsonProperty("publishTime")
	private String publishTime;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("relativePublishTimeDescription")
	private String relativePublishTimeDescription;

	@JsonProperty("text")
	private Text text;

	@JsonProperty("authorAttribution")
	private AuthorAttribution authorAttribution;

	public OriginalText getOriginalText(){
		return originalText;
	}

	public String getPublishTime(){
		return publishTime;
	}

	public String getName(){
		return name;
	}

	public int getRating(){
		return rating;
	}

	public String getRelativePublishTimeDescription(){
		return relativePublishTimeDescription;
	}

	public Text getText(){
		return text;
	}

	public AuthorAttribution getAuthorAttribution(){
		return authorAttribution;
	}
}