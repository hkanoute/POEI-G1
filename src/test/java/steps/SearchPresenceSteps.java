package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pages.SearchPage;
import utils.DriverHelper;

import static org.junit.Assert.assertTrue;

public class SearchPresenceSteps {
    SearchPage searchPage = PageFactory.initElements(DriverHelper.driver, SearchPage.class);

    @Given("ouvrir l'application dans le navigateur")
    public void ouvrirApplication() {
        DriverHelper.driver.get("http://www.automationpractice.pl/index.php");
    }

    @When("Se rediriger vers une page via  son {string}")
    public void seRedirigerVersUnePage(String url) {
        DriverHelper.driver.get(url);
    }

    @Then("Verifier si la page contient le champ de recherche et la loupe")
    public void verifierPresenceRechercheEtLoupe() {
        assertTrue("Le champ de recherche n'est pas visible", searchPage.isSearchFieldDisplayed());
        assertTrue("La loupe n'est pas visible", searchPage.isSearchButtonDisplayed());
    }


    @Then("l'utilisateur est redirigé vers la page de de résultat de recherche")
    public void lUtilisateurEstRedirigéVersLaPageDeDeRésultatDeRecherche() {
        assertTrue(searchPage.isSearchPageDisplayed());
    }
}
