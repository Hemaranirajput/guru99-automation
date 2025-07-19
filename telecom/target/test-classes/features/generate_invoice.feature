Feature: Generate Invoice

  Scenario: Successfully generate invoice for a valid customer ID
    Given I navigate to the Generate Invoice page
    When I enter customer ID "12345" and submit
    Then I should see the invoice details
