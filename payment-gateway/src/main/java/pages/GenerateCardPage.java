package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class GenerateCardPage {
    WebDriver driver;
    WebDriverWait wait;

    private By cardNumber = By.xpath("//h4[contains(text(),'Card Number')]"); 

    private By cvv = By.xpath("//h4[contains(text(),'CVV')]");
    private By exp = By.xpath("//h4[contains(text(),'Exp')]");

    public GenerateCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // ✅ Required for @FindBy to work
    }
    private WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void open() {
    	System.out.println("▶ Opening card generation page");
    	String mainWindow = driver.getWindowHandle();
       // driver.get("https://demo.guru99.com/payment-gateway/index.php");
        driver.get("https://demo.guru99.com/payment-gateway/cardnumber.php");
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumber));
    }

    public String getCardNumber() {
        String card = waitVisible(cardNumber).getText(); // e.g., "- 43571185282162"
        return card.replaceAll("[^0-9]", ""); // returns "43571185282162"
    }


    public String getCvv() {
        String rawText = wait.until(ExpectedConditions.visibilityOfElementLocated(cvv)).getText(); // e.g., "CVV: 123"
        if (rawText.contains(":")) {
            return rawText.split(":")[1].trim();
        } else {
            throw new RuntimeException("CVV text not in expected format: " + rawText);
        }
    }

    public String getExpMonth() {
        String rawText = waitVisible(exp).getText(); // e.g., "Exp: 09 / 2025"
        if (rawText.contains(":")) {
            String[] parts = rawText.split(":")[1].trim().split("/");
            return parts[0].trim(); // "09"
        } else {
            throw new RuntimeException("Expiry format not as expected: " + rawText);
        }
    }

    public String getExpYear() {
        String rawText = waitVisible(exp).getText(); // e.g., "Exp: 09 / 2025"
        if (rawText.contains(":")) {
            String[] parts = rawText.split(":")[1].trim().split("/");
            return parts[1].trim(); // "2025"
        } else {
            throw new RuntimeException("Expiry format not as expected: " + rawText);
        }
    }
}
