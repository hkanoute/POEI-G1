package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import utils.ConfigReader;

import static org.junit.Assert.assertEquals;
import static utils.DriverHelper.driver;

public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        this.homePage = new HomePage();
    }



    @Then("La barre de menu noire est affichée")
    public void laBarreDeMenuNoireEstAffichée() {
        assertEquals(homePage.menuAffiche(),"Call us now");
    }

    @And("Le lien {string} redirige vers la page de contact")
    public void leLienRedirigeVersLaPageDeContact(String contact) {
        homePage.clickOnContactUs();
        assertEquals();

    @Given("je suis sur la page d'accueil")
    public void jeSuisSurLaPageDAccueil() {
        homePage.navigateToHomePage();
    }

    @And("Le lien {string} redirige vers la page d’authentification")
    public void leLienRedirigeVersLaPageDAuthentification(String arg0) {

    }

    @And("Les onglets {string}, {string} et {string} redirigent vers les articles de leur catégorie")
    public void lesOngletsEtRedirigentVersLesArticlesDeLeurCatégorie(String arg0, String arg1, String arg2) {
    }

    @And("L'onglet {string} redirige vers le site Prestashop")
    public void lOngletRedirigeVersLeSitePrestashop(String arg0) {

    }

    @And("Les publicités redirigent vers le site partenaire Prestashop")
    public void lesPublicitésRedirigentVersLeSitePartenairePrestashop() {
    }
}
