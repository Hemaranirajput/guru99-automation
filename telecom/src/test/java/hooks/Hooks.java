package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.TestContext;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        TestContext.setDriver(driver);
        System.out.println("✅ WebDriver initialized in Hooks: " + driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = TestContext.getDriver();

        if (driver != null) {
            if (scenario.isFailed()) {
                try {
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File source = ts.getScreenshotAs(OutputType.FILE);

                    String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                    String screenshotPath = "target/screenshots/" + scenarioName + "_" + timestamp + ".png";

                    File destination = new File(screenshotPath);
                    FileUtils.copyFile(source, destination);
                    System.out.println("Screenshot saved: " + screenshotPath);

                    byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshotBytes, "image/png", "Screenshot");

                } catch (Exception e) {
                    System.out.println("Failed to capture screenshot: " + e.getMessage());
                }
            }

            driver.quit();
            System.out.println("🛑 WebDriver closed.");
        } else {
            System.out.println("WebDriver was null. Skipping screenshot and quit.");
        }
    }
}
