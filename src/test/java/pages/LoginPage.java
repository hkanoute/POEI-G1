package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.DriverHelper.driver;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    public void login() {
        emailInput.sendKeys("auralion4@gmail.com");
        passwordInput.sendKeys("test123");
    }
}
