package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class PayBillingPage {

    WebDriver driver;
    WebDriverWait wait;

    private By customerIdInput = By.name("customer_id");
    private By submitButton = By.name("submit");
    private By billingTable = By.xpath("//table[@class='alt']");

    public PayBillingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demo.guru99.com/telecom/billing.php");
    }

    public void enterCustomerId(String customerId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerIdInput)).sendKeys(customerId);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean isBillingTableDisplayed() {
        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(billingTable));
            return table.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
