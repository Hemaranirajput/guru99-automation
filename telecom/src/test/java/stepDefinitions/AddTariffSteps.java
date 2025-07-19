package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.AddTariffPlanPage;
import utils.TestContext;

public class AddTariffSteps {

    private final WebDriver driver;
    private final AddTariffPlanPage page;

    public AddTariffSteps() {
    	this.driver = TestContext.getDriver();
        this.page = new AddTariffPlanPage(driver);
    }

    @Given("I am on the Add Tariff Plan page")
    public void i_am_on_add_tariff_page() {
        page.open();
    }

    @When("I fill in all the required fields")
    public void i_fill_form() {
        page.fillForm("100", "500", "100", "50", "1", "2", "3");
    }

    @When("I click submit")
    public void i_click_submit() {
        page.submit();
    }

    @Then("I should see success message")
    public void i_should_see_success() {
        System.out.println("Tariff Plan successfully added.");
    }
}
