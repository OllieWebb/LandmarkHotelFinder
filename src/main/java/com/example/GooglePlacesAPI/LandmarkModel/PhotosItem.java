package com.example.GooglePlacesAPI.LandmarkModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotosItem{

	@JsonProperty("photo_reference")
	private String photoReference;

	@JsonProperty("width")
	private int width;

	@JsonProperty("html_attributions")
	private List<String> htmlAttributions;

	@JsonProperty("height")
	private int height;

	public String getPhotoReference(){
		return photoReference;
	}

	public int getWidth(){
		return width;
	}

	public List<String> getHtmlAttributions(){
		return htmlAttributions;
	}

	public int getHeight(){
		return height;
	}
}