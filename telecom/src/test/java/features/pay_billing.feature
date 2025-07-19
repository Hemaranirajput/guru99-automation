Feature: Pay Billing

  Scenario: Successfully pay bill for an existing customer
    Given I am on the Pay Billing page
    When I enter a valid customer ID
    And I click on submit
    Then Billing details should be displayed