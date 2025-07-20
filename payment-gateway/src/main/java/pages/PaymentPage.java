package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        String cleanMonth = mm.replaceAll("[^0-9]", "");
        if (cleanMonth.length() == 1) {
            cleanMonth = "0" + cleanMonth;
        }
        wait.until(ExpectedConditions.visibilityOf(month));
        new Select(month).selectByVisibleText(cleanMonth);

        String cleanYear = yy.replaceAll("[^0-9]", "");
        wait.until(ExpectedConditions.visibilityOf(year));
        new Select(year).selectByVisibleText(cleanYear);

        String cleanCvv = cvvNum.replaceAll("[^0-9]", "");
        if (!cleanCvv.matches("\\d{3}")) {
            throw new IllegalArgumentException("Invalid CVV entered: " + cvvNum);
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
            if (!actualText.equals(expectedText)) {
                throw new AssertionError(
                    "Alert message mismatch! Expected: " + expectedText + ", but got: " + actualText
                );
            }
            alert.accept();
        } catch (Exception e) {
            throw new RuntimeException("Error while verifying alert.", e);
        }
    }

    public void handleAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {
            // No alert — ignore
        }
    }

    public void payWith(String card, String cvvNum, String mm, String yy) {
        enterCardDetails(card, mm, yy, cvvNum);
        clickPay();
        handleAlertIfPresent();
    }
}
