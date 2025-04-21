package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

import static utils.DriverHelper.driver;

/**
 * Page object class for the home page.
 * This class contains methods to interact with the home page elements.
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//span[@class='shop-phone']")
    private WebElement menu_noir;

    @FindBy(id = "contact-link")
    private WebElement contact_us;

    @FindBy(xpath = "//a[@class='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement women;

    @FindBy(xpath = "//div[@id = 'block_top_menu']/ul/li[2]/a")
    private WebElement dresses;

    @FindBy(xpath = "//div[@id = 'block_top_menu']/ul/li[3]/a")
    private WebElement t_shirts;

    @FindBy(xpath = "//a[@title='Blog']")
    private WebElement blog;

    @FindBy(id = "homepage-slider")
    private WebElement pub;

    @FindBy(xpath = "//div[@id = 'header_logo']")
    private WebElement logo;

    @FindBy(id = "center_column")
    private WebElement contact_us_page;

    /**
     * Method that returns the text of the menu noir (black menu).
     *
     * @return the text of the menu noir as a String.
     */
    public String menuAffiche() {
        return menu_noir.getText();
    }


    /**
     * Method to navigate to the home page.
     */
    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }


    /**
     * Method to get the title of the home page.
     */
    public void clickOnContactUs() {
        contact_us.click();
    }

    /**
     * Method that allows the user to click on the login button.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Method that allows the user to click on the women link.
     */
    public void clickOnWomen() {
        women.click();
    }

    /**
     * Method that allows the user to click on the dresses link.
     */
    public void clickOnDresses() {
        dresses.click();
    }

    /**
     * Method that allows the user to click on the t-shirts link.
     */
    public void clickOnTshirts() {
        t_shirts.click();
    }

    /**
     * Method that allows the user to click on the blog link.
     */
    public void clicOnBlog() {
        blog.click();
    }

    /**
     * Method that allows the user to click on the pub.
     */
    public void clicOnPub() {
        pub.click();
    }

    /**
     * Method return the login button text.
     * @return String
     */
    public String getLoginButtonText() {
        return loginButton.getText();
    }

    /**
     * Method that allows to click on the logo.
     */
    public void clickOnLogo() {
        logo.click();
    }

    /**
     * Method that allows to verify if the logo is displayed.
     * @return boolean
     */
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
}
