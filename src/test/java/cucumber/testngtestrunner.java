package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/cucumber",
        glue = {"jitendrajoshi.stepDefinitions"},
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
)
public class testngtestrunner extends AbstractTestNGCucumberTests {
}
