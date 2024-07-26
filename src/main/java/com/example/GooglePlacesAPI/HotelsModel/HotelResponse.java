package com.example.GooglePlacesAPI.HotelsModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponse{

	@JsonProperty("next_page_token")
	private String nextPageToken;

	@JsonProperty("html_attributions")
	private List<Object> htmlAttributions;

	@JsonProperty("results")
	private List<ResultsItem> results;

	@JsonProperty("status")
	private String status;

	public String getNextPageToken(){
		return nextPageToken;
	}

	public List<Object> getHtmlAttributions(){
		return htmlAttributions;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public String getStatus(){
		return status;
	}
}