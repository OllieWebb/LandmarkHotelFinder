package com.example.GooglePlacesAPI.Entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LandmarkResponse{

	@JsonProperty("html_attributions")
	private List<Object> htmlAttributions;

	@JsonProperty("results")
	private List<ResultsItem> results;

	@JsonProperty("status")
	private String status;

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