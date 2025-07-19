Feature: Assign Tariff Plan to Customer
@AssignTariff
  Scenario: Successfully assign a tariff plan to a customer
  Given I am on the Assign Tariff Plan to Customer page
  When I enter the customer ID
  And I select a tariff plan
 
  Then I should see a success message confirming tariff assignment