package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "utils"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json", "junit:target/surefire-reports/cucumber.xml"},
        tags = "@POEI25P2G1-36"
)
public class TestRunner {
}
