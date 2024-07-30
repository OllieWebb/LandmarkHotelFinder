package com.example.GooglePlacesAPI.stepdefs;

import com.example.GooglePlacesAPI.SharedState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommonStepDefs {
    private final SharedState sharedState;

    public CommonStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Given("the endpoint {string}")
    public void theEndpoint(String endpoint) {
        sharedState.endpoint = endpoint;
    }

    @And("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int expected) {
        assertThat(sharedState.response.statusCode(),is(expected));
    }
}
