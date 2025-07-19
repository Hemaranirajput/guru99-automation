package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

public class TestContext {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<CardDetails> cardDetails = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // ✅ Added

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            WebDriver webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            cardDetails.remove();
            extentTest.remove(); // ✅ clean up ExtentTest
        }
    }

    public static void setCardDetails(CardDetails details) {
        cardDetails.set(details);
    }

    public static CardDetails getCardDetails() {
        return cardDetails.get();
    }

    // ✅ ExtentTest getter/setter
    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
