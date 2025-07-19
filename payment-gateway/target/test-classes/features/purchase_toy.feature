Feature: Toy Purchase via Payment Gateway

  Background:
    Given I am on the Purchase Toy page

  Scenario Outline: Purchase succeeds with valid card
    When I set quantity to "<qty>"
      And I generate a valid card
      And I pay using the generated card
    Then I should see a success message with an order ID

    Examples:
      | qty |
      | 1   |
      | 5   |

  Scenario: Purchase fails with invalid card
    When I set quantity to "1"
     And I pay using an invalid card "12345678" "123" "12" "2028"
  Then I should see an alert with message "Check card number is 16 digits!"
