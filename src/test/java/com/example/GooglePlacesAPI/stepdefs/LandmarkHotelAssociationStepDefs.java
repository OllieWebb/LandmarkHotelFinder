package com.example.GooglePlacesAPI.stepdefs;

import com.example.GooglePlacesAPI.SharedState;
import com.example.GooglePlacesAPI.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LandmarkHotelAssociationStepDefs {
    private final SharedState sharedState;

    public LandmarkHotelAssociationStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @When("I request hotels near {string} within a {int} meter radius")
    public void iRequestHotelsNearWithinAMeterRadius(String landmark, int radius) {
        sharedState.response = RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .queryParams(Map.of(
                        "text",landmark,
                        "radius",radius
                ))
                .when()
                .get()
                .thenReturn();
    }

    @Then("I should receive a list of hotels")
    public void iShouldReceiveAListOfHotels() throws IOException {
        assertThat(Utils.parseLandmarkResponse(sharedState.response.getBody().asString()).getResults(),not(empty()));
    }

    @And("each hotel should include the name, rating, website URL, reviews, phone number, and address")
    public void eachHotelShouldIncludeTheNameRatingWebsiteURLReviewsPhoneNumberAndAddress() throws IOException {
        assertThat(Utils.parseLandmarkResponse(sharedState.response.getBody().asString()).getResults().getFirst().getName(),notNullValue());
    }
}
