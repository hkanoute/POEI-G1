package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterPage extends BasePage {
    private final LoginPage loginPage;

    public RegisterPage() {
        super();
        loginPage = new LoginPage();
    }

    private boolean skipFormSteps = false;
    String generatedEmail;

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

    @FindBy(xpath = "//a[@class='login']")
    private WebElement loginButton;

    public void submitEmail(String email) {
        emailInput.sendKeys(email);
        createAccountBtn.click();
    }

    public void clickCreateAccount() {
        createAccountBtn.click();
    }


    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public void selectGender(String genre) {
        if (genre.equalsIgnoreCase("Mr")) {
           genderMr.click();
        } else {
            genderMrs.click();
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
    public void register(String email, String genre, String prenom, String nom, String password, String dateNaissance, String newsletter, String messageFinal) {
        // 1. Email
        loginButton.click();
        if (email.equals("random")) {
            generatedEmail = "user" + System.currentTimeMillis() + "@mail.com";
           submitEmail(generatedEmail);
        } else {
            submitEmail(email);
        }

        // 2. Clic sur REGISTER
        clickRegister();

        // 3. Gestion du formulaire ou message d'erreur
        /*String actualError = getEmailErrorMessage();
        if (!actualError.isEmpty()) {
            System.out.println("🔍 Message d'erreur reçu : " + actualError);
            assertTrue("Le message d'erreur ne correspond pas", actualError.contains(messageFinal));
            skipFormSteps = true;
            return;
        }*/

        skipFormSteps = false; // s'assurer qu'on continue si pas d'erreur

        // 4. Suite du formulaire
        selectGender(genre);
        fillName(prenom, nom);
        fillPassword(password);
        selectDate(dateNaissance);
        checkNewsletter(newsletter);

        // 5. Vérification finale
      /*  if (!messageFinal.equalsIgnoreCase("Fail")) {
            String finalMessage = getFinalMessage();
            System.out.println("✅ Message affiché : " + finalMessage);
            assertTrue(finalMessage.contains(messageFinal));
        }*/
        clickRegister();
    }

}
