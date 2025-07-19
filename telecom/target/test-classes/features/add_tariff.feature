Feature: Add Tariff Plan

  Scenario: Successfully add tariff plan
    Given I am on the Add Tariff Plan page
    When I fill in all the required fields
    And I click submit
    Then I should see success message
