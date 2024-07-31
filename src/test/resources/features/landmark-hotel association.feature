Feature: Nearby Hotels API

  Scenario: Retrieve hotels near a landmark within a valid radius
    Given the endpoint "/hotel"
    When I request hotels near "Eiffel Tower" within a 1000 meter radius
    Then I should receive a list of hotels
    And each hotel should include the name, rating, website URL, reviews, phone number, and address
    And the response should have a status code of 200

  Scenario: No hotels within the specified radius
    Given the endpoint "/hotel"
    When I request hotels near "Mount Everest" within a 500 meter radius
    Then I should receive an empty list
    And the response should have a status code of 200

#  Scenario: Invalid landmark name
#    Given the endpoint "/hotel"
#    When I request hotels near "Unknown Landmark" within a 1000 meter radius
#    Then I should receive an error message indicating the landmark is not found
#    And the response should have a status code of 404

  Scenario: Invalid radius (negative value)
    Given the endpoint "/hotel"
    When I request hotels near "Statue of Liberty" within a -500 meter radius
    Then I should receive an error message indicating the radius is invalid
    And the response should have a status code of 400

  Scenario: Valid request with zero hotels
    Given the endpoint "/hotel"
    When I request hotels near "Sahara Desert" within a 1000 meter radius
    Then I should receive an empty list
    And the response should have a status code of 200

  Scenario: Large radius returning many hotels
    Given the endpoint "/hotel"
    When I request hotels near "Times Square" within a 5000 meter radius
    Then I should receive a list of hotels
    And the list should not exceed the maximum number of hotels limit (e.g., 100)
    And each hotel should include the name, rating, website URL, reviews, phone number, and address
    And the response should have a status code of 200

  Scenario: API handling special characters in landmark names
    Given the endpoint "/hotel"
    When I request hotels near "Tōkyō Tower" within a 1000 meter radius
    Then I should receive a list of hotels
    And each hotel should include the name, rating, website URL, reviews, phone number, and address
    And the response should have a status code of 200

  Scenario: Malformed request with missing parameters
    Given the endpoint "/hotel"
    When I request hotels near "Colosseum" with missing radius parameter
    Then I should receive an error message indicating missing parameters
    And the response should have a status code of 400

  Scenario: Landmark with no hotels but valid name
    Given the endpoint "/hotel"
    When I request hotels near "Great Sphinx of Giza" within a 100 meter radius
    Then I should receive an empty list
    And the response should have a status code of 200
