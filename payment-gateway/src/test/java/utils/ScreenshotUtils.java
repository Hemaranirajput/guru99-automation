package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String scenarioName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = "test-output/screenshots/" + scenarioName.replaceAll(" ", "_") + ".png";
            Path destination = Paths.get(filePath);
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
