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



}
