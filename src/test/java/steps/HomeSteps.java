package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        this.homePage = new HomePage();
    }

    @Given("je suis sur la page d'accueil")
    public void jeSuisSurLaPageDAccueil() {
        homePage.navigateToHomePage();
    }

}
