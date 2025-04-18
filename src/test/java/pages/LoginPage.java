package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
     * Web element representing the forgot password link.
     */
    @FindBy(xpath = "//a[@title='Recover your forgotten password']")
    private WebElement forgotPasswordLink;


    @FindBy(id = "email")
    private WebElement forgotEmailInputField;

    @FindBy(xpath = "//p[@class=\"submit\"]/button")
    private WebElement resetPasswordButton;


    @FindBy(id = "refresh")
    private WebElement refreshButton;


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
     *
     * @param email The email address to be entered.
     */
    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    /**
     * Method to enter the password into the password input field.
     *
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
     *
     * @return The error message text.
     */
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    /**
     * Method to click the forgot password link.
     */
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    /**
     * Method to enter the email address in the forgot password input field.
     *
     * @param email The email address to be entered.
     */
    public void fillForgotEmail(String email) {
        forgotEmailInputField.click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

    }

    /**
     * Method to click the reset password button.
     */
    public void clickResetPasswordButton() {
        resetPasswordButton.click();
    }

    /**
     * Methode to click on the refresh button.
     */
    public void clickOnRefreshButton() {
        refreshButton.click();
    }

    /**
     * Method to refresh the tab.
     */
    public void refreshTheTab() {
        // refresh the active tab
        driver.navigate().refresh();
    }

    /**
     * Method clicking on the reset link.
     */
    public void clickOnResetLink() {
        WebDriver iframe = driver.switchTo().frame("ifmail");
        System.out.println("iframe = " + iframe);
        iframe.findElement(By.xpath("//*[contains(text(),'id_customer')]")).click();
        driver.switchTo().defaultContent();
    }
}
