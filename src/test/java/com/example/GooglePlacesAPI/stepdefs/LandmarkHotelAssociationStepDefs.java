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
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces(),not(empty()));
    }

    @And("each hotel should include the name, rating, website URL, reviews, phone number, and address")
    public void eachHotelShouldIncludeTheNameRatingWebsiteURLReviewsPhoneNumberAndAddress() throws IOException {
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getDisplayName(),notNullValue());
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getRating(),notNullValue());
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getWebsiteUri(),notNullValue());
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getReviews(),notNullValue());
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getInternationalPhoneNumber(),notNullValue());
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().getFirst().getFormattedAddress(),notNullValue());
    }

    @Then("I should receive an empty list")
    public void iShouldReceiveAnEmptyList() throws IOException {
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces(),nullValue());
    }

    @Then("I should receive an error message indicating the landmark is not found")
    public void iShouldReceiveAnErrorMessageIndicatingTheLandmarkIsNotFound() {
        assertThat(sharedState.response.getBody().asString().toLowerCase(),containsString("no results found"));
    }

    @Then("I should receive an error message indicating the radius is invalid")
    public void iShouldReceiveAnErrorMessageIndicatingTheRadiusIsInvalid() {
        assertThat(sharedState.response.getBody().asString().toLowerCase(),containsString("invalid radius"));
    }

    @And("the list should not exceed the maximum number of hotels limit \\(e.g., {int})")
    public void theListShouldNotExceedTheMaximumNumberOfHotelsLimitEG(int max) throws IOException {
        assertThat(Utils.parseHotelResponse(sharedState.response.getBody().asString()).getPlaces().size(),lessThan(max));
    }

    @When("I request hotels near {string} with missing radius parameter")
    public void iRequestHotelsNearWithMissingRadiusParameter(String landmark) {
        sharedState.response = RestAssured
                .given()
                .baseUri(sharedState.ROOT_URI)
                .basePath(sharedState.endpoint)
                .queryParams(Map.of(
                        "text",landmark
                ))
                .when()
                .get()
                .thenReturn();
    }

    @Then("I should receive an error message indicating missing parameters")
    public void iShouldReceiveAnErrorMessageIndicatingMissingParameters() {
        assertThat(sharedState.response.getBody().asString().toLowerCase(),containsString("missing parameter"));
    }
}
