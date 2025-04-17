package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;

import static utils.DriverHelper.driver;
/**
 * Page object class for the home page.
 * This class contains methods to interact with the home page elements.
 */
public class HomePage extends BasePage {
    /**
     * Method to navigate to the home page.
     */
    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }


}
