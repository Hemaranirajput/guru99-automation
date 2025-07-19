package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentManager;
import utils.ScreenshotUtils;
import utils.TestContext;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        TestContext.getDriver(); // launch browser
        ExtentTest test = ExtentManager.getExtentReports().createTest(scenario.getName());
        TestContext.setExtentTest(test);
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = TestContext.getExtentTest();

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            String screenshotPath = ScreenshotUtils.takeScreenshot(TestContext.getDriver(), scenario.getName());
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        TestContext.quitDriver();
        ExtentManager.getExtentReports().flush();
    }
}
