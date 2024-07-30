# Landmark-Hotel Association API

## Overview

This project provides a comprehensive suite of RESTful API endpoints for the Landmark-Hotel Association, facilitating various functionalities related to hotel management and user interactions. A user will be able to input the name of landmark, a given radius and two dates and the API will provide these deliverables:

### 1. Landmark-Hotel Association
Endpoints to retrieve hotels near a given landmark based on proximity and availability. These endpoints help users find suitable accommodations close to their desired landmarks efficiently.

### 2. Availability Check
An endpoint to check hotel availability near a specified landmark for a given date range. This functionality allows users to plan their stays by ensuring room availability before making a booking.

### 3. Booking Insights
An endpoint providing valuable insights, such as popular booking periods, average prices near landmarks, and other trends. This data aids in understanding booking behaviors and optimizing pricing strategies.

### 4. User Reviews and Ratings
Endpoints to manage user reviews and ratings for hotels and landmarks. This feature allows users to share their experiences and provide feedback, enhancing the decision-making process for future travelers.

## Getting Started

### Installation
1. Clone the repository:
   ```bash
   

### Running
2. Run the program and use the Swagger UI to interact with the API:
   http://localhost:8080/swagger-ui/index.html#/

### Using
3. Example request:

    GET /hotel: finds hotel near given landmark
