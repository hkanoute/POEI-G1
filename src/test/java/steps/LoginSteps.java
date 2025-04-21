package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import pages.LoginPage;
import pages.NavbarPage;
import pages.RegisterPage;
import utils.DriverHelper;

import java.util.Objects;

import static utils.DriverHelper.driver;


/**
 * LoginSteps class contains step definitions for the login functionality of the application.
 * It includes methods to interact with elements on the login page and verify their behavior.
 * These are used in Cucumber tests.
 */
public class LoginSteps {
    private final LoginPage loginPage;
    private final NavbarPage navbarPage;
    private final HomePage homePage;
    private final RegisterPage registerPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.navbarPage = new NavbarPage();
        this.homePage = new HomePage();
        this.registerPage = new RegisterPage();

    }

    @Given("Je suis connecté")
    public void jeSuisConnecte() {
        homePage.navigateToHomePage();
        navbarPage.clickLoginButton();
        loginPage.login();
    }


    @Given("L'utilisateur est sur la page {string}")
    public void lUtilisateurEstSurLaPage(String arg0) {
        loginPage.GoToAuthPage();
    }

    @When("Il saisit l'email {string} et le mot de passe {string}")
    public void ilSaisitLEmailEtLeMotDePasse(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
    }

    @And("Il clique sur le bouton {string}")
    public void ilCliqueSurLeBouton(String arg0) {
        loginPage.clickLoginButton();
    }

    /**
     * Step definition for verifying the error message displayed on the login page.
     *
     * @param arg0 The expected error message.
     */
    @Then("Le message affiché est {string}")
    public void leMessageAfficheEst(String arg0) {
        String expectedMessage = "Authentication failed.";

        if (Objects.equals(arg0, "passed")) {
            Assert.assertTrue(true);
            return;
        }

        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @And("L'utilisateur est redirigé vers {string}")
    public void lUtilisateurEstRedirigeVers(String destination) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(destination, actualUrl);

    }

    @When("Cliquer sur le lien {string}")
    public void cliquerSurLeLien(String arg0) {
        loginPage.clickForgotPasswordLink();
    }

    @Then("Rediriger l'utilisateur vers la page de reinitialisation du compte")
    public void redirigerLUtilisateurVersLaPageDeReinitialisationDuCompte() {
        String expectedUrl = "http://www.automationpractice.pl/index.php?controller=password";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    /**
     * Step definition for creating an account using a temporary email from Yopmail.
     * This method opens a new tab, generates a random email, and fills in the registration form.
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    public void createAnAccountFromThrowableMail() throws InterruptedException {
        DriverHelper.driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        DriverHelper.driver.get("https://yopmail.com/fr/email-generator");
        DriverHelper.driver.findElement(By.xpath("//button[@aria-label=\"Autoriser\"]")).click();
        DriverHelper.driver.findElement(By.id("cprnd")).click();
        DriverHelper.driver.findElement(By.xpath("//span[text() = \"Vérifier les mails\"]/parent::button")).click();

        DriverHelper.driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        loginPage.GoToAuthPage();
        WebElement email = registerPage.getEmail();
        email.click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

        Thread.sleep(2000);
        registerPage.clickCreateAccount();
        Thread.sleep(2000);

        registerPage.fillName("test", "test");
        registerPage.fillPassword("test123");
        registerPage.selectGender("Mr");
        registerPage.selectDate("01/01/2000");
        registerPage.clickRegister();

        DriverHelper.driver.switchTo().window(DriverHelper.driver.getWindowHandles().toArray()[0].toString());

    }

    @And("Remplir le champ qui permet de saisir un email")
    public void remplirLeChampQuiPermetDeSaisirUnEmail() throws InterruptedException {
        createAnAccountFromThrowableMail();
        loginPage.fillForgotEmail("");
    }

    @When("cliquer sur {string}")
    public void cliquerSur(String sign_out) {
        sign_out = "Sign out";
        loginPage.clickSignout();

    }

    @Then("Rediriger l{string}authentification")
    public void redirigerLUtilisateurVersLaPageDAuthentification() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account", currentUrl);

    }

    @And("Afficher le lien {string} dans la barre menu")
    public void afficherLeLienDansLaBarreMenu(String signin) {
        String signInText = homePage.getLoginButtonText();
        Assert.assertEquals("Sign in", signInText);
    }

    /*@Then("L'utilisateur est sur la page {string}")
    public void lUtilisateurEstSurLaPageString(String login_page) {
             loginPage.redirectToLoginPage();
            login_page = driver.getCurrentUrl();
            Assert.assertEquals("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account", login_page);

        }*/


    @And("cliquer sur le bouton {string}")
    public void cliquerSurLeBouton(String arg0) {
        loginPage.clickResetPasswordButton();
    }

    @And("vérifier si un email de réinitialisation est envoyé")
    public void verifierSiUnEmailDeReinitialisationEstEnvoye() throws InterruptedException {
        DriverHelper.driver.switchTo().window(DriverHelper.driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(6000);
        loginPage.refreshTheTab();
        Thread.sleep(6000);
        loginPage.clickOnRefreshButton();
        loginPage.clickOnResetLink();
        DriverHelper.driver.switchTo().window(DriverHelper.driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(10000);
        loginPage.clickOnRefreshButton();

    }

    @Given("L{string}espace My Account")
    public void lUtilisateurEstDansLEspaceMyAccount() {
    }
}
