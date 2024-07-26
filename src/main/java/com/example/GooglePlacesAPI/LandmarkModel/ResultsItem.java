package com.example.GooglePlacesAPI.LandmarkModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultsItem{

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("business_status")
	private String businessStatus;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("icon_background_color")
	private String iconBackgroundColor;

	@JsonProperty("photos")
	private List<PhotosItem> photos;

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("user_ratings_total")
	private int userRatingsTotal;

	@JsonProperty("name")
	private String name;

	@JsonProperty("opening_hours")
	private OpeningHours openingHours;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("icon_mask_base_uri")
	private String iconMaskBaseUri;

	@JsonProperty("plus_code")
	private PlusCode plusCode;

	@JsonProperty("place_id")
	private String placeId;

	public String getFormattedAddress(){
		return formattedAddress;
	}

	public List<String> getTypes(){
		return types;
	}

	public String getBusinessStatus(){
		return businessStatus;
	}

	public String getIcon(){
		return icon;
	}

	public double getRating(){
		return rating;
	}

	public String getIconBackgroundColor(){
		return iconBackgroundColor;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public String getReference(){
		return reference;
	}

	public int getUserRatingsTotal(){
		return userRatingsTotal;
	}

	public String getName(){
		return name;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public String getIconMaskBaseUri(){
		return iconMaskBaseUri;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public String getPlaceId(){
		return placeId;
	}
}