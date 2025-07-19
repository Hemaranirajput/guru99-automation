import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VisibleBrowserTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        System.out.println("Browser launched");

        driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
        System.out.println("Navigated to gateway");

        Thread.sleep(10000); // Keep it open for 10 seconds
        driver.quit();
        System.out.println("Browser closed");
    }
}