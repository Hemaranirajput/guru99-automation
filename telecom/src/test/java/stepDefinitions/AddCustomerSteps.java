package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pages.AddCustomerPage;
import utils.TestContext;

public class AddCustomerSteps {
    public static String customerID;

    WebDriver driver = TestContext.getDriver();
    AddCustomerPage page;

    @Given("I am on the Add Customer page")
    public void i_am_on_add_customer_page() {
        page = new AddCustomerPage(driver);
        page.open();
    }

    @When("I enter customer details with {string}, {string}, {string}, {string}, {string}")
    public void i_enter_customer_details(String fname, String lname, String email, String address, String phone) {
        page.fillCustomerForm(fname, lname, email, address, phone);
    }

    @When("I click the submit button")
    public void i_click_submit_button() {
        page.submit();
    }

    @Then("Customer ID should be displayed")
    public void customer_id_should_be_displayed() {
        Assert.assertTrue(page.getCustomerID().matches("\\d+"), "Customer ID not displayed");
        customerID = page.getCustomerID();
        System.out.println("Captured Customer ID: " + customerID);
    }
}
