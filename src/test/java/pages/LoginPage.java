package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;

import static utils.DriverHelper.driver;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;
    @FindBy(xpath = "//a[@title='Log me out']")
    private WebElement logoutButton;
    @FindBy(id="SubmitLogin")
    private WebElement loginButton;

    public void login() {
        emailInput.sendKeys("auralion4@gmail.com");
        passwordInput.sendKeys("test123");
        loginButton.click();
        
    }
    public void redirectToLoginPage() {
        driver.get(ConfigReader.getProperty("LOGIN_URL"));
    }
    public void clickSignout() {
        logoutButton.click();
    }
}
