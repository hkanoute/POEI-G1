package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static utils.DriverHelper.driver;

/**
 * Page object class for the login page.
 * This class contains methods to interact with the login page elements.
 */
public class LoginPage extends BasePage {

    /**
     * Web element representing the email input field.
     */
    @FindBy(id = "email")
    private WebElement emailInput;

    /**
     * Web element representing the password input field.
     */
    @FindBy(id = "passwd")
    private WebElement passwordInput;

    /**
     * Web element representing the login button.
     */
    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    /**
     * Web element representing the error message.
     */
    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement errorMessage;

    /**
     * method to enter the email and password into the respective input fields and click the login button.
     */
    public void login() {
        emailInput.sendKeys("auralion4@gmail.com");
        passwordInput.sendKeys("test123");
        loginButton.click();
    }


    /**
     * Method to enter the email and password into the respective input fields.
     * @param email The email address to be entered.
     */
    public  void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    /**
     * Method to enter the password into the password input field.
     * @param password
     */
    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Method to click the login button.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Method to navigate to the authentication page.
     */
    public void GoToAuthPage() {
        driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    /**
     * Method to get the error message displayed on the login page.
     * @return The error message text.
     */
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
