package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    @FindBy(tagName = "h1")
    private WebElement formTitle;

    @FindBy(id = "customer.firstName")
    private WebElement firstNameInput;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameInput;

    @FindBy(id = "customer.address.street")
    private WebElement addressInput;

    @FindBy(id = "customer.address.city")
    private WebElement cityInput;

    @FindBy(id = "customer.address.state")
    private WebElement stateInput;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneInput;

    @FindBy(id = "customer.ssn")
    private WebElement ssnInput;

    @FindBy(id = "customer.username")
    private WebElement usernameInput;

    @FindBy(id = "customer.password")
    private WebElement passwordInput;

    @FindBy(id = "repeatedPassword")
    private WebElement repeatedPasswordInput;

    @FindBy(xpath = "//p[contains(text(),'Your account was created successfully. You are now logged in.')]")
    private WebElement succesMessage;

    @FindBy(xpath = "(//input[ @type = \"submit\"])[2]")
    private WebElement registerButton;

    public String imRedirectedToTheSignupPage() {
        return formTitle.getText();
    }

    public void iFillTheFirstNameInput() {
        firstNameInput.sendKeys("John");
    }

    public void iFillTheLastNameInput() {
        lastNameInput.sendKeys("Does");
    }

    public void iFillTheAddressInput() {
        addressInput.sendKeys("i dk");
    }

    public void iFillTheCityInput() {
        cityInput.sendKeys("Melun");
    }

    public void iFillTheStateInput() {
        stateInput.sendKeys("france");
    }

    public void iFillThePhoneInput() {
        phoneInput.sendKeys("0615169636");
    }

    public void iFillTheSSNInput() {
        ssnInput.sendKeys("qsdqshdqshdiqsh");
    }

    public void iFillTheUsernameInput() {
        usernameInput.sendKeys("JohnDoe");
    }

    public void iFillThePasswordInput() {
        passwordInput.sendKeys("test123*");
    }

    public void iFillTheConfirmInput() {
        repeatedPasswordInput.sendKeys("test123*");
    }

    public void iFillTheZipCodecInput() {
        zipCodeInput.sendKeys("77000");
    }

    public String getSuccessMessage(){
        return succesMessage.getText();
    }

    public void iClickOnRegister(){
        registerButton.click();
    }
}
