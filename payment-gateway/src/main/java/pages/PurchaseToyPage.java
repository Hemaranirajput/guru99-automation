package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PurchaseToyPage extends BasePage {
    private By qtyInput = By.name("quantity");
    private By buyBtn = By.cssSelector("input.button.special[value='Buy Now']");
    
    private By generateCardLink = By.linkText("Generate Card Number"); // Adjust if link text differs

    public void clickGenerateCard() {
        WebElement link = waitVisible(generateCardLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }
  

    public PurchaseToyPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://demo.guru99.com/payment-gateway/");
        waitVisible(qtyInput);
    }

    public void enterQuantity(String qty) {
    	System.out.println("Entering quantity: " + qty);
    	WebElement dropdown = waitVisible(qtyInput);
        Select select = new Select(dropdown);
        select.selectByVisibleText(qty);
        System.out.println("Quantity selected: " + qty);
    
    }

    public void clickBuy() {
        waitClickable(buyBtn).click();
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("payment-gateway");
    }
}