package com.example.GooglePlacesAPI.Services;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SeleniumService {

    static {
        // Determine the operating system
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath;

        if (os.contains("win")) {
            driverPath = "src/main/resources/chromedriver.exe";
        } else if (os.contains("mac")) {
            driverPath = "src/main/resources/chromedriver-mac-x64/chromedriver";
        } else {
            driverPath = "src/main/resources/chromedriver-linux64/chromedriver";
        }

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    public Map<String, Boolean> getHotelAvailability(String hotelName, String checkInDate, String checkOutDate) {
        // Set up ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options); // Pass options to ChromeDriver
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Map<String, Boolean> availabilityMap = new HashMap<>();
        try {
            // Open the website to search for hotels
            driver.get("https://www.booking.com/");

            // Decline cookies when loaded in
            WebElement declineCookiesButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-reject-all-handler")));
            declineCookiesButton.click();

            // Wait for the search input to be present and enter the location
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(":rh:")));
            searchInput.sendKeys(hotelName);

            // Wait for the date button to be present and click it
            WebElement dateContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-testid='searchbox-dates-container']")));
            dateContainer.click();

            // Wait for check in date to be present and click it
            WebElement checkInDateSpan = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-date='" + checkInDate + "']")));
            checkInDateSpan.click();

            // Wait for check out date to be present and click it
            WebElement checkOutDateSpan = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-date='" + checkOutDate + "']")));
            checkOutDateSpan.click();

            // Wait for the search button to be present and click it
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.dba1b3bddf.e99c25fd33.f8a5a77b19.f1c8772a7d.bec09c39da.f953867e0b.c437808707")));
            searchButton.click();

            // Check availability message
            boolean isAvailable;
            try {
                WebElement message = driver.findElement(By.cssSelector("p.e2585683de.ca4ca01afd"));
                String result = message.getText();
                isAvailable = !result.equals("This property has no availability on our site from "+formatDate(checkInDate)+" to "+formatDate(checkOutDate)+".");
            } catch (NoSuchElementException e) {
                // No unavailable message but could not be right query returned. Looking at first hotel result
                String hotelNameSearchResult = driver.findElement(By.cssSelector("div[data-testid='title']")).getText();
                isAvailable = hotelName.equals(hotelNameSearchResult);
            }
            availabilityMap.put(hotelName, isAvailable);
        } finally {
            driver.quit();
        }
        return availabilityMap;
    }

    public static String formatDate(String inputDate) {
        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-d");
        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM");

        try {
            // Parse the input date
            Date date = inputFormat.parse(inputDate);

            // Format the date to the desired output format
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
