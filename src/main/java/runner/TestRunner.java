package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.ConfigReader;
import utils.XrayApiClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "utils"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}
)
public class TestRunner {

    public static void getFeatures() throws IOException, InterruptedException {
        XrayApiClient xray = new XrayApiClient();
        Path myDirectory = Path.of("src/test/resources/features");

        xray.export("/export/cucumber?keys=" + ConfigReader.getProperty("KEYS"), myDirectory);
    }

    @AfterClass
    public static void sendReport() throws IOException, InterruptedException {
        XrayApiClient xray = new XrayApiClient();
        File cucumberJson = new File("target/cucumber.json");

        String response = xray.post("/import/execution/cucumber", new String(Files.readAllBytes(cucumberJson.toPath())));
        System.out.println(response);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 0 && "fetch".equals(args[0])) {
            getFeatures();
        } else {
            org.junit.runner.JUnitCore.main("runner.TestRunner");
        }
    }
}
