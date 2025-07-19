package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.GenerateInvoicePage;
import utils.TestContext;

public class GenerateInvoiceSteps {
    TestContext context;
    GenerateInvoicePage invoicePage;

    public GenerateInvoiceSteps(TestContext context) {
        this.context = context;
        invoicePage = new GenerateInvoicePage(TestContext.getDriver());
    }

    @Given("I navigate to the Generate Invoice page")
    public void i_navigate_to_generate_invoice_page() {
        invoicePage.open(); // Reuse the already created invoicePage
    }

    @When("I enter customer ID {string} and submit")
    public void i_enter_customer_id(String customerId) {
        invoicePage.enterCustomerIdAndSubmit(customerId);
    }

    @Then("I should see the invoice details")
    public void i_should_see_invoice_details() {
        Assert.assertTrue(invoicePage.isInvoiceGenerated(), "Invoice generation failed!");
    }
}
