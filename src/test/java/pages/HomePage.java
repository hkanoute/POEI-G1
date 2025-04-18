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

//find la barre de menu
    @FindBy(xpath = "//span[@class='shop-phone']")
    private WebElement menu_noir;

    @FindBy(id = "contact-link")
    private WebElement contact_us;

    @FindBy(id = "center_column")
    private WebElement contact_us_page;



    public String menuAffiche() {
        return menu_noir.getText();
    }


    public void clickOnContactUs() {
        contact_us.click();
    }

    /**
     * Method to navigate to the home page.
     */
    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }


}
