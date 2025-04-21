package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;

import static utils.DriverHelper.driver;

public class ContactUsPage extends BasePage{

    public void navigateToContactPage() {
        driver.get(ConfigReader.getProperty("CONTACT_US_URL"));
    }

    public String getContactUsPageTitle() {
        return driver.getTitle();
    }
}
