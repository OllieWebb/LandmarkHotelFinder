Feature: Hotel Availability Check

  Scenario: Check availability for a hotel that is available
    Given I am on the booking site
    When I search for a hotel "The Tower Hotel, London" with check-in date "2024-08-01" and check-out date "2024-08-05"
    Then the hotel "The Tower Hotel, London" should be available

  Scenario: Check availability for a hotel that is not available
   Given I am on the booking site
  When I search for a hotel "Unavailable Hotel" with check-in date "2024-08-01" and check-out date "2024-08-05"
  Then the hotel "Unavailable Hotel" should not be available

  Scenario: Check availability with an invalid hotel name
    Given I am on the booking site
    When I search for a hotel "the most invalid hotel name" with check-in date "2024-08-01" and check-out date "2024-08-05"
    Then the hotel with an invalid name should not be available
