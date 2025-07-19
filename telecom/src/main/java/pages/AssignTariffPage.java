package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AssignTariffPage {
    WebDriver driver;
    WebDriverWait wait;

    By customerIdInput = By.id("customer_id");
    By submitBtn = By.name("submit");
    By planRadioButtons = By.xpath("//input[@type='radio' and @name='tariff_plan']");
    By assignBtn = By.xpath("//input[@value='Add Tariff Plan to Customer']");
    By successMessage = By.xpath("//h2[contains(normalize-space(),'Congratulation Tariff Plan assigned')]");

    public AssignTariffPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get("https://demo.guru99.com/telecom/assigntariffplantocustomer.php");
    }

    public void enterCustomerID(String customerId) {
        driver.findElement(customerIdInput).sendKeys(customerId);
        driver.findElement(submitBtn).click();
    }

    public void selectTariffPlanAndSubmit() {
        try {
            List<WebElement> radios = driver.findElements(planRadioButtons);
            System.out.println("👉 Found " + radios.size() + " radio button(s).");

            if (radios.size() == 0) {
                throw new IllegalStateException("No tariff plans available to assign!");
            }

            WebElement radio = radios.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radio);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
            System.out.println("Tariff plan selected");

            WebElement assignButton = wait.until(ExpectedConditions.elementToBeClickable(assignBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", assignButton);
            assignButton.click();
            System.out.println("Assign button clicked");

        } catch (Exception e) {
            System.out.println("Error during tariff selection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isAssignmentSuccessful() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            System.out.println(" Success message displayed: " + message.getText());
            return message.getText().trim().contains("Congratulation Tariff Plan assigned");
        } catch (TimeoutException e) {
            System.out.println("Success message not found in time.");
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error while checking success message: " + e.getMessage());
            return false;
        }
    }
}
