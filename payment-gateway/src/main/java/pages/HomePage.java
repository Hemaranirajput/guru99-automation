package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    WebDriver driver;

    // Locators
    @FindBy(name = "quantity") 
    WebElement quantityDropdown;

    @FindBy(xpath = "//input[@value='Buy Now']")
    WebElement buyNowBtn;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action to select quantity
    public void selectQuantity(String qty) {
        new Select(quantityDropdown).selectByVisibleText(qty);
    }

    // Action to click Buy Now
    public void clickBuyNow() {
        buyNowBtn.click();
    }
}
