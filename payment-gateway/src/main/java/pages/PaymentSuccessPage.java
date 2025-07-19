package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentSuccessPage {
    WebDriver driver;

    @FindBy(xpath = "//h2[contains(text(),'Payment successfull!')]")
    WebElement successMessage;

    public PaymentSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}
