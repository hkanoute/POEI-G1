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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * DriverHelper class to manage WebDriver instances.
 */
public class DriverHelper {
    public static WebDriver driver;




    @Before
    public void getDriverHelper() throws MalformedURLException {
        String browser = ConfigReader.getProperty("BROWSER");
        boolean isRemote = Boolean.parseBoolean(ConfigReader.getProperty("REMOTE"));
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"));
        String remoteUrl = ConfigReader.getProperty("REMOTE_URL");

        if (isRemote) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser.equalsIgnoreCase("Edge") ? "MicrosoftEdge" : browser.toLowerCase());
            driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } else {
            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) edgeOptions.addArguments("--headless");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("TIME_OUT"))));
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }

    /**
     * Method to take a screenshot if a step fails.
     * @param scenario
     */
    @After
    public void takeScreenShotsOnStepFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        //driver.quit();
    }

}
