Feature: Add Customer

  Scenario Outline: Successfully add a new customer with different data
    Given I am on the Add Customer page
    When I enter customer details with "<fname>", "<lname>", "<email>", "<address>", "<phone>"
    And I click the submit button
    Then Customer ID should be displayed

  Examples:
    | fname   | lname   | email               | address        | phone      |
    | Neha    | Doe     | Neha@example.com    | 123 Street     | 1234567890 |
    | Sneha   | Smith   | sneha@example.com   | 456 Avenue     | 9876543210 |
