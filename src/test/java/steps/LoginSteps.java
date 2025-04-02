package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }


    @Then("Je remplis le champ username avec la valeur {string}")
    public void fillUsernameInput(String username) {
        loginPage.fillUsernameInput(username);
    }

    @And("Je remplie le champ password avec la valeur {string}")
    public void fillPasswordInput(String password) {
        loginPage.fillPasswordInput(password);
    }

    @And("Je clique sur le bouton me connecter")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @And("Je suis connecté")
    public void verifyUserIsLogged() {
        String title = loginPage.verifyUserIsLogged();
        Assert.assertEquals("Accounts Overview", title);
    }

    @And("Je reçois un message d'erreur")
    public void verifyErrorMessage() {
        String error = loginPage.verifyErrorMessage();
        Assert.assertEquals("The username and password could not be verified.", error);
    }


    @Given("Je suis connecté et je suis sur la page des produits")
    public void jeSuisConnecteEtJeSuisSurLaPageDesProduits() {
        loginPage.jeSuisConnecteEtJeSuisSurLaPageDesProduits();
    }

    @Given("Je suis sur la page de login")
    @Given("Im on the login page")
    public void jeSuisSurLaPageLogin() {
        String url = loginPage.jeSuisSurLaPageLogin();
        //Assert.assertEquals("https://parabank.parasoft.com/parabank/index.htm",url);
    }

    @When("I click on Register")
    public void iClickOnRegister() {
        loginPage.iClickOnRegister();
    }


    @Given("Im logged")
    public void imLogged() {
        fillPasswordInput("demo");
        fillUsernameInput("john");
        clickOnLoginButton();
    }

}
