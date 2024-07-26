package com.example.GooglePlacesAPI.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlusCode{

	@JsonProperty("compound_code")
	private String compoundCode;

	@JsonProperty("global_code")
	private String globalCode;

	public String getCompoundCode(){
		return compoundCode;
	}

	public String getGlobalCode(){
		return globalCode;
	}
}