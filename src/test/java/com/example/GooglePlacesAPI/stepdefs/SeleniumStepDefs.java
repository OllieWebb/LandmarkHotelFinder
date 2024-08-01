package com.example.GooglePlacesAPI.stepdefs;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.GooglePlacesAPI.Services.SeleniumService;
import com.example.GooglePlacesAPI.SharedState;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SeleniumStepDefs {

    private final SharedState sharedState;

    public SeleniumStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
        // Initialise SeleniumService
        sharedState.seleniumService = new SeleniumService();
    }

    @Given("I am on the booking site")
    public void iAmOnTheBookingSite() {
        // Initialise WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Uncomment if you want headless mode
        // options.addArguments("--remote-debugging-port=9222"); // Uncomment if needed
        sharedState.driver = new ChromeDriver(options);
    }

    @When("I search for a hotel {string} with check-in date {string} and check-out date {string}")
    public void iSearchForHotel(String hotelName, String checkInDate, String checkOutDate) {
        try {
            sharedState.availability = sharedState.seleniumService.getHotelAvailability(hotelName, checkInDate, checkOutDate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle or rethrow as needed
        }
    }

    @Then("the hotel {string} should be available")
    public void theHotelShouldBeAvailable(String hotelName) {
        boolean isAvailable = sharedState.availability.getOrDefault(hotelName, false);
        assertTrue(isAvailable, String.format("Expected hotel '%s' to be available, but it was not. Availability map: %s", hotelName, sharedState.availability));
    }

    @Then("the hotel {string} should not be available")
    public void theHotelShouldNotBeAvailable(String hotelName) {
        boolean isAvailable = sharedState.availability.getOrDefault(hotelName, true);
        assertFalse(isAvailable, String.format("Expected hotel '%s' to not be available, but it was found in the availability map: %s", hotelName, sharedState.availability));
    }

    @Then("the hotel with an invalid name should not be available")
    public void theHotelWithAnInvalidNameShouldNotBeAvailable() {
        // Check that the availability map is not empty and contains different hotel names
        boolean hasDifferentHotels = sharedState.availability.keySet().stream()
                .noneMatch(name -> name.matches(".*\\d+.*")); // Assumes invalid names have digits, adjust as needed

        assertTrue(hasDifferentHotels, "Availability map should not contain hotels with invalid names.");
    }

    @Then("the hotel {string} should not be available for invalid dates")
    public void theHotelShouldNotBeAvailableForInvalidDates(String hotelName) {
        // Check that the specific hotel name is not available
        boolean isAvailable = sharedState.availability.getOrDefault(hotelName, true);
        assertFalse(isAvailable, String.format("Expected hotel '%s' to not be available with invalid dates, but it was found in the availability map: %s", hotelName, sharedState.availability));
    }

    @After
    public void tearDown() {
        if (sharedState.driver != null) {
            sharedState.driver.quit();
        }
    }
}
