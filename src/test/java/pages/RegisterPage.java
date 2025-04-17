package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverHelper;

import java.time.Duration;
public class RegisterPage extends BasePage {
    @FindBy(id = "email_create")
    private WebElement emailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//div[@id='create_account_error']//li")
    private WebElement emailErrorMessage;

    // Formulaire "Create an account"
    @FindBy(id = "id_gender1")
    private WebElement genderMr;

    @FindBy(id = "id_gender2")
    private WebElement genderMrs;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daysSelect;

    @FindBy(id = "months")
    private WebElement monthsSelect;

    @FindBy(id = "years")
    private WebElement yearsSelect;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "submitAccount")
    private WebElement registerBtn;

    @FindBy(css = ".account")
    private WebElement accountHeader;

    @FindBy(css = ".info-account")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement registrationError;

    public void submitEmail(String email) {
        emailInput.sendKeys(email);
        createAccountBtn.click();
    }

    public void waitForCreateAccountForm() {
        WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public void selectGender(String genre) {
        WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(10));

        if (genre.equalsIgnoreCase("Mr")) {
            wait.until(ExpectedConditions.elementToBeClickable(genderMr)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(genderMrs)).click();
        }
    }

    public void fillName(String prenom, String nom) {
        firstNameInput.sendKeys(prenom);
        lastNameInput.sendKeys(nom);
    }

    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void selectDate(String date_naissance) {
        if (date_naissance != null && !date_naissance.isEmpty()) {
            String[] parts = date_naissance.split("/");
            new Select(daysSelect).selectByValue(String.valueOf(Integer.parseInt(parts[0])));
            new Select(monthsSelect).selectByValue(String.valueOf(Integer.parseInt(parts[1])));
            new Select(yearsSelect).selectByValue(String.valueOf(Integer.parseInt(parts[2])));
        }
    }

    public void checkNewsletter(String choix) {
        if (choix.equalsIgnoreCase("oui")) {
            if (!newsletterCheckbox.isSelected()) {
                newsletterCheckbox.click();
            }
        }
    }

    public void clickRegister() {
        registerBtn.click();
    }

    public String getFinalMessage() {
        try {
            return successMessage.getText(); // "Your account has been created."
        } catch (NoSuchElementException e) {
            return registrationError.getText(); // Erreur de mot de passe ou autre
        }
    }

}
