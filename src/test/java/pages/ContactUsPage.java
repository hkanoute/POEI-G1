package pages;


import utils.ConfigReader;
import static utils.DriverHelper.driver;

/**
 * ContactUsPage class represents the contact us page of the application.
 * It contains methods to interact with elements on the contact us page.
 */
public class ContactUsPage extends BasePage{

    /**
     * Method to navigate to the contact us page.
     */
    public void navigateToContactPage() {
        driver.get(ConfigReader.getProperty("CONTACT_US_URL"));
    }

    /**
     * Method to get the title of the contact us page.
     *
     * @return the title of the contact us page as a String.
     */
    public String getContactUsPageTitle() {
        return driver.getTitle();
    }
}
