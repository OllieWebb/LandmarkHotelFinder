# Landmark-Hotel Association API

## Overview

This project provides a comprehensive suite of RESTful API endpoints for the Landmark-Hotel Association, facilitating various functionalities related to hotel management and user interactions. A user will be able to input the name of landmark, a given radius and two dates and the API will provide these deliverables:

### 1. Landmark-Hotel Association
Endpoints to retrieve hotels near a given landmark based on proximity and availability. These endpoints help users find suitable accommodations close to their desired landmarks efficiently.

### 2. Availability Check
An endpoint to check hotel availability near a specified landmark for a given date range. This functionality allows users to plan their stays by ensuring room availability before making a booking.

### 3. User Reviews and Ratings
Endpoints to manage user reviews and ratings for hotels and landmarks. This feature allows users to share their experiences and provide feedback, enhancing the decision-making process for future travelers.

## Getting Started

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/OllieWebb/LandmarkHotelFinder.git
   ```

2. Update Chrome and download the latest ChromeDriver from [here](https://googlechromelabs.github.io/chrome-for-testing/).
   Unzip the download and place the file `chromedriver.exe` in `/src/main/resources`
3. Create a `application.properties` file in `/src/main/resources` and add the following to it:
   ```properties
   logging.level.org.springframework.web=DEBUG
   spring.application.name=GooglePlacesAPI
   google.places.api.key=[YOUR-GOOGLE-PLACES-API-KEY]
   ```

### Running
Run the program `/src/main/java/com/example/GooglePlacesAPI/GooglePlacesAPIApplication.java` and use the [Swagger UI](http://localhost:8080/swagger-ui/index.html#/) to interact with the API:

### Using
Example request:

    GET /hotel: finds hotel near given landmark

### Test Framework
To run the tests, run `src/test/java/com/example/GooglePlacesAPI/TestRunner.java`. A cucumber test report will be generated in the parent folder of the repository.
