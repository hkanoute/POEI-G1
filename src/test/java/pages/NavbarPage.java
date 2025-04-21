package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object class for the navigation bar.
 */
public class NavbarPage extends BasePage{
    /**
     * Web element representing the account link.
     */
    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement myAccountButton;

    /**
     * Web element representing the logout link.
     */
    @FindBy(xpath = "//a[@title='Log me out']")
    private WebElement logoutButton;

    /**
     * Web element representing the login link.
     */
    @FindBy(xpath = "//a[@class=\"login\"]")
    private WebElement loginButton;

    /**
     * Web element representing the contact link.
     */

    @FindBy(xpath = "//a[@title='Contact Us']")
    private WebElement contactButton;

    /**
     * Method to click the login button.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Method to click the my account button.
     */
    public void clickMyAccountButton() {
        myAccountButton.click();
    }

    /**
     * Method to click the logout button.
     */
    public void clickLogoutButton() {
        logoutButton.click();
    }

    /**
     * Method to click the contact button.
     */
    public void clickContactButton() {
        contactButton.click();
    }


}
