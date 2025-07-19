package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GenerateInvoicePage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By customerIdField = By.id("customer_id");
    By submitButton = By.name("submit");

    public GenerateInvoicePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demo.guru99.com/telecom/billing.php");
    }

    public void enterCustomerIdAndSubmit(String customerId) {
        driver.findElement(customerIdField).sendKeys(customerId);
        driver.findElement(submitButton).click();
    }

    public boolean isInvoiceGenerated() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("h1"), "Pay Billing"
            ));
        } catch (Exception e) {
            return false;
        }
    }
}
