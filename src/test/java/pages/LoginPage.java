package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.DriverHelper.driver;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement userNameInput;

    @FindBy(name = "password")
    private WebElement passwordButton;

    @FindBy(xpath = "//input[@type = \"submit\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[contains(text(),'Accounts Overview')]")
    private WebElement title;

    @FindBy(xpath = "//p[@class = \"error\"]")
    private WebElement h3;

    @FindBy(xpath = "//a[@href=\"register.htm\"]")
    private WebElement registerLink;

    public void fillUsernameInput(String username) {
        userNameInput.sendKeys(username);
    }


    public void fillPasswordInput(String password) {
        passwordButton.sendKeys(password);
    }


    public void clickOnLoginButton() {
        loginButton.click();
    }


    public String verifyUserIsLogged() {
        return title.getText();

    }


    public String verifyErrorMessage() {
        return h3.getText();
    }


    public void jeSuisConnecteEtJeSuisSurLaPageDesProduits() {
        fillPasswordInput("secret_sauce");
        fillUsernameInput("standard_user");
        clickOnLoginButton();
    }


    public String jeSuisSurLaPageLogin() {
        return driver.getCurrentUrl();
    }

    public void iClickOnRegister(){
        registerLink.click();
    }

}
