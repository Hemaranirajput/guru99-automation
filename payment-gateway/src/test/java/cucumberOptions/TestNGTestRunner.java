package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/java/features",
	    glue = "stepDefinations",
	    plugin = {"pretty", "html:target/cucumber-reports"},
	    monochrome = true
	)

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
