package utils;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;

public class DriverHelper {
    public static WebDriver driver;


    @BeforeAll
    public static void getFeatures() throws IOException, InterruptedException {
        XrayApiClient xray = new XrayApiClient();
        Path myDirectory = Path.of("src/test/resources/features");

        xray.export("/export/cucumber?keys=" + ConfigReader.getProperty("KEYS"), myDirectory);
    }

    @Before
    public void getDriverHelper()  {
        switch (ConfigReader.getProperty("BROWSER")) {
            case "firefox":
                FirefoxOptions ffoptions = new FirefoxOptions();
                if (Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"))) {
                    ffoptions.addArguments("--headless");
                }
                ;
                driver = new FirefoxDriver(ffoptions);
                break;
            case "Edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"))) {
                    edgeOptions.addArguments("--headless");
                }
                ;
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"))) {
                    chromeOptions.addArguments("--headless");
                }
                ;

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("TIME_OUT"))));
        driver.get(ConfigReader.getProperty("BASE_URL"));

    }

    @After
    public void takeScreenShotsOnStepFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();

    }

}
