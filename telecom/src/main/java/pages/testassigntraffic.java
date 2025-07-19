package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class testassigntraffic {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();

            
            driver.get("https://demo.guru99.com/telecom/assigntariffplantocustomer.php");

            
            String customerId = "551409";  // Replace with a valid ID
            driver.findElement(By.id("customer_id")).sendKeys(customerId);
            driver.findElement(By.name("submit")).click();

           
            List<WebElement> radios = driver.findElements(By.xpath("//input[@name='tariff_plan']"));
            System.out.println("👉 Found " + radios.size() + " radio button(s).");

            if (radios.size() == 0) {
                System.out.println("No tariff plans available for customer ID: " + customerId);
                return;
            }

            
            WebElement radio = radios.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radio);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
            System.out.println("Tariff plan selected");

            
            WebElement assignBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Add Tariff Plan to Customer']")));
            assignBtn.click();

           
            boolean success = driver.getPageSource().contains("Congratulation Tariff Plan assigned");
            if (success) {
                System.out.println("Tariff plan assigned successfully!");
            } else {
                System.out.println("Tariff plan assignment failed.");
            }

        } catch (Exception e) {
            System.out.println(" Exception occurred:");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
