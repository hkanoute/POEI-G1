package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;

import static utils.DriverHelper.driver;

public class LoginSteps {
    private final LoginPage loginPage;
    private final HomePage homePage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();

    }

    @Given("Je suis connecté")
    public void jeSuisConnecte() {
        homePage.navigateToHomePage();
        homePage.clickLoginButton();
        loginPage.login();
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

}
