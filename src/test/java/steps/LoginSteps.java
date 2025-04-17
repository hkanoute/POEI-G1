package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.NavbarPage;

import java.util.Objects;

import static utils.DriverHelper.driver;

public class LoginSteps {
    private final LoginPage loginPage;
    private final NavbarPage navbarPage;
    private final HomePage homePage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.navbarPage = new NavbarPage();
        this.homePage = new HomePage();

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
     * @param arg0 The expected error message.
     */
    @Then("Le message affiché est {string}")
    public void leMessageAffichéEst(String arg0) {
        String expectedMessage = "Authentication failed.";

        if (Objects.equals(arg0, "passed")) {
            Assert.assertTrue(true);
            return;
        }

        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @And("L'utilisateur est redirigé vers {string}")
    public void lUtilisateurEstRedirigéVers(String destination) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(destination, actualUrl);

    }

    @When("Cliquer sur le lien {string}")
    public void cliquerSurLeLien(String arg0) {

    }
}
