package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;


/**
 * Page object class for the registration page.
 * This class contains methods to interact with the registration page elements.
 */
public class RegisterPage extends BasePage {
    private final LoginPage loginPage;

    public RegisterPage() {
        super();
        loginPage = new LoginPage();
    }

    @FindBy(id = "email_create")
    private WebElement emailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//div[@id='create_account_error']//li")
    private WebElement emailErrorMessage;

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

    @FindBy(xpath = "//a[@class='login']")
    private WebElement loginButton;


    /**
     * Method to submit the email for account creation.
     * @param email The email address to be submitted.
     */
    public void submitEmail(String email) {
        emailInput.sendKeys(email);
        createAccountBtn.click();
    }

    /**
     * Method to click the create account button.
     */
    public void clickCreateAccount() {
        createAccountBtn.click();
    }


    /**
     * Method to get the error message for invalid email.
     * @return The error message as a String.
     */
    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    /**
     * Method to get the header text after successful account creation.
     * @return The header text as a String.
     */
    public void selectGender(String genre) {
        if (genre.equalsIgnoreCase("Mr")) {
           genderMr.click();
        } else {
            genderMrs.click();
        }
    }

    /**
     * Method to fill in the user's first and last name.
     * @param prenom
     * @param nom
     */
    public void fillName(String prenom, String nom) {
        firstNameInput.sendKeys(prenom);
        lastNameInput.sendKeys(nom);
    }

    /**
     * Method to fill in the user's password.
     * @param password The password to be filled in.
     */
    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Method to select the date of birth.
     * @param date_naissance The date of birth in the format "dd/mm/yyyy".
     */
    public void selectDate(String date_naissance) {
        if (date_naissance != null && !date_naissance.isEmpty()) {
            String[] parts = date_naissance.split("/");
            new Select(daysSelect).selectByValue(String.valueOf(Integer.parseInt(parts[0])));
            new Select(monthsSelect).selectByValue(String.valueOf(Integer.parseInt(parts[1])));
            new Select(yearsSelect).selectByValue(String.valueOf(Integer.parseInt(parts[2])));
        }
    }


    /**
     * Method to check the newsletter subscription.
     * @param choix The choice for newsletter subscription ("oui" or "non").
     */
    public void checkNewsletter(String choix) {
        if (choix.equalsIgnoreCase("oui")) {
            if (!newsletterCheckbox.isSelected()) {
                newsletterCheckbox.click();
            }
        }
    }

    /**
     * Method to click the register button.
     */
    public void clickRegister() {
        registerBtn.click();
    }

    /**
     * Method to get the header text after successful account creation.
     * @return The header text as a String.
     */
    public String getFinalMessage() {
        try {
            return successMessage.getText(); // "Your account has been created."
        } catch (NoSuchElementException e) {
            return registrationError.getText(); // Erreur de mot de passe ou autre
        }
    }

    /**
     * Getter for the email input field.
     * @return the email element
     */
    public WebElement getEmail() {
        return this.emailInput;
    }


    /**
     * Register a random user with the given parameters.
     * @param genre
     * @param prenom
     * @param nom
     * @param password
     * @param date_naissance
     */
    public void registerRandomUser(String genre, String prenom, String nom, String password, String date_naissance) {

        loginPage.GoToAuthPage();

        String generatedEmail = "user" + System.currentTimeMillis() + "@mail.com";
        this.submitEmail(generatedEmail);
        this.selectGender(genre);
        this.fillName(prenom, nom);
        this.fillPassword(password);
        this.selectDate(date_naissance);
        this.clickRegister();
    }

}
