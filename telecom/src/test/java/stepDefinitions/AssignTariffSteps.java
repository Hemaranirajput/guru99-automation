package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AssignTariffPage;
import utils.TestContext;

public class AssignTariffSteps {
    WebDriver driver = TestContext.getDriver(); 
    AssignTariffPage assignPage;

    @Given("I am on the Assign Tariff Plan to Customer page")
    public void openAssignTariffPage() {
        assignPage = new AssignTariffPage(driver);
        assignPage.open();
    }

    @When("I enter the customer ID")
    public void enterCustomerId() {
        assignPage.enterCustomerID(AddCustomerSteps.customerID);
    }

    @When("I select a tariff plan")
    public void selectTariffPlan() {
        assignPage.selectTariffPlanAndSubmit();
    }

    @Then("I should see a success message confirming tariff assignment")
    public void verifySuccessMessage() {
        boolean success = assignPage.isAssignmentSuccessful();
        Assert.assertTrue(success, "Tariff Plan not assigned successfully — check for radio presence or success message mismatch.");
    }
}
