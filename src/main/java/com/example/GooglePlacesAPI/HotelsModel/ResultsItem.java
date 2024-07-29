package com.example.GooglePlacesAPI.HotelsModel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultsItem{

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("name")
	private String name;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("icon_background_color")
	private String iconBackgroundColor;

	@JsonProperty("icon_mask_base_uri")
	private String iconMaskBaseUri;

	@JsonProperty("vicinity")
	private String vicinity;

	@JsonProperty("photos")
	private List<PhotosItem> photos;

	@JsonProperty("place_id")
	private String placeId;

	@JsonProperty("business_status")
	private String businessStatus;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("user_ratings_total")
	private int userRatingsTotal;

	@JsonProperty("plus_code")
	private PlusCode plusCode;

	@JsonProperty("opening_hours")
	private OpeningHours openingHours;

	@JsonProperty("price_level")
	private int priceLevel;

	public String getReference(){
		return reference;
	}

	public List<String> getTypes(){
		return types;
	}

	public String getScope(){
		return scope;
	}

	public String getIcon(){
		return icon;
	}

	public String getName(){
		return name;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public String getIconBackgroundColor(){
		return iconBackgroundColor;
	}

	public String getIconMaskBaseUri(){
		return iconMaskBaseUri;
	}

	public String getVicinity(){
		return vicinity;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public String getPlaceId(){
		return placeId;
	}

	public String getBusinessStatus(){
		return businessStatus;
	}

	public double getRating(){
		return rating;
	}

	public int getUserRatingsTotal(){
		return userRatingsTotal;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public int getPriceLevel(){
		return priceLevel;
	}
}