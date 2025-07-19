package pages;

import java.time.Duration;
import utils.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "card_nmuber") WebElement cardNumber;
    @FindBy(name = "month") WebElement month;
    @FindBy(name = "year") WebElement year;
    @FindBy(name = "cvv_code") WebElement cvv;
    @FindBy(name = "submit") WebElement pay;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterCardDetails(String card, String mm, String yy, String cvvNum) {
        wait.until(ExpectedConditions.visibilityOf(cardNumber));
        cardNumber.sendKeys(card);

        // ✅ Sanitize and select month
        String cleanMonth = mm.replaceAll("[^0-9]", "");
        if (cleanMonth.length() == 1) {
            cleanMonth = "0" + cleanMonth;
        }
        System.out.println("Selecting cleaned month: " + cleanMonth);
        wait.until(ExpectedConditions.visibilityOf(month));
        new Select(month).selectByVisibleText(cleanMonth);

        // ✅ Sanitize and select year
        String cleanYear = yy.replaceAll("[^0-9]", "");
        System.out.println("Selecting cleaned year: " + cleanYear);
        wait.until(ExpectedConditions.visibilityOf(year));
        new Select(year).selectByVisibleText(cleanYear);

        // ✅ Sanitize and validate CVV
        String cleanCvv = cvvNum.replaceAll("[^0-9]", "");
        if (!cleanCvv.matches("\\d{3}")) {
            throw new IllegalArgumentException("❌ Invalid CVV entered: " + cvvNum);
        }

        wait.until(ExpectedConditions.visibilityOf(cvv));
        cvv.sendKeys(cleanCvv);
    }

    public void clickPay() {
        pay.click();
    }

    public void verifyAlertMessage(String expectedText) {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String actualText = alert.getText();
            System.out.println("Alert message: " + actualText);
            Assert.assertEquals(actualText, expectedText, "Alert message mismatch!");
            alert.accept(); // ✅ Don't forget to accept it
        } catch (Exception e) {
            Assert.fail("An error occurred while verifying alert: " + e.getMessage());
        }
    }
    public void handleAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Optional Alert: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert appeared.");
        }
    }

    public void payWith(String card, String cvvNum, String mm, String yy) {
        enterCardDetails(card, mm, yy, cvvNum);
        clickPay();
        handleAlertIfPresent();
    }
}
