package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentResultPage extends BasePage {

	private By orderId = By.xpath("//h3[strong[text()='Order ID']]/parent::td/following-sibling::td/h3/strong");

    private By errorMsg = By.xpath("//*[contains(text(),'Invalid')]");

    public PaymentResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasOrderId() {
        try {
            return waitVisible(orderId).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasErrorMessage() {
        try {
            return waitVisible(errorMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

   
    public String getOrderId() {
        WebElement orderIdElement = waitVisible(orderId);
        String id = orderIdElement.getText();
        if (!id.matches("\\d+")) {
            throw new IllegalStateException("Order ID is not numeric: " + id);
        }
        return id;
    }
}
