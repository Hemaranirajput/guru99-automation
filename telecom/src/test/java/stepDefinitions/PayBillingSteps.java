package stepDefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.PayBillingPage;
import stepDefinitions.AddCustomerSteps;
import utils.TestContext;
import org.testng.Assert;

public class PayBillingSteps {

    WebDriver driver = TestContext.getDriver();
    PayBillingPage billingPage;

    @Given("I am on the Pay Billing page")
    public void i_am_on_the_pay_billing_page() {
        billingPage = new PayBillingPage(driver);
        billingPage.open();
    }

    @When("I enter a valid customer ID")
    public void i_enter_valid_customer_id() {
        billingPage.enterCustomerId(AddCustomerSteps.customerID); 
    }

    @When("I click on submit")
    public void i_click_on_submit() {
        billingPage.clickSubmit();
    }

    @Then("Billing details should be displayed")
    public void billing_details_should_be_displayed() {
        boolean isDisplayed = billingPage.isBillingTableDisplayed(); 
        Assert.assertTrue(isDisplayed, "Billing details not displayed");
    }
}
