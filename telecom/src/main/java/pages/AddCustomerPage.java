package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AddCustomerPage {
    WebDriver driver;
    WebDriverWait wait;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By doneRadio = By.id("done");
    By fname = By.id("fname");
    By lname = By.id("lname");
    By email = By.id("email");
    By address = By.name("addr");
    By phone = By.id("telephoneno");
    By submitBtn = By.name("submit");

    public void open() {
        driver.get("https://demo.guru99.com/telecom/addcustomer.php");
        System.out.println("Navigated to page: " + driver.getTitle());
    }

    public void fillCustomerForm(String firstName, String lastName, String emailAddr, String addr, String mobile) {
    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='done']"))).click();
     
        driver.findElement(fname).sendKeys(firstName);
        driver.findElement(lname).sendKeys(lastName);
        driver.findElement(email).sendKeys(emailAddr);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(phone).sendKeys(mobile);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }
    public String getCustomerID() {
        WebElement idElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3")));
        return idElement.getText();
    }
}
