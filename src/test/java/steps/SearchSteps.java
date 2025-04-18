package steps;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pages.SearchPage;
import utils.DriverHelper;

import static org.junit.Assert.assertTrue;
public class SearchSteps {
    SearchPage searchPage = PageFactory.initElements(DriverHelper.driver, SearchPage.class);

    @Given("Le champ de rechercher et la loupe s'affichent sur la page")
    public void champEtLoupeSontVisibles() {
        DriverHelper.driver.get("http://www.automationpractice.pl/index.php");
        assertTrue(searchPage.isSearchFieldDisplayed());
        assertTrue(searchPage.isSearchButtonDisplayed());
    }

    @When("Saisir le nom de l'article {string}")
    public void saisirNomArticle(String article) {
        searchPage.fillSearchField(article);
    }

    @And("sélectionner l'article")
    public void selectionnerArticle() {
        searchPage.selectSuggestion();
    }

    @And("cliquer sur la loupe")
    public void cliquerSurLaLoupe() {
        searchPage.clickSearchButton();
    }

    @Then("l'utilisateur est redirigé vers la page de l'article choisi")
    public void redirigeVersArticle() {
        assertTrue(searchPage.isOnProductPage());
    }

    @Then("l'utilisateur est redirigé vers la page {string}")
    public void redirigeVersPageSearch(String pageTitle) {
        assertTrue(searchPage.getPageTitle().contains(pageTitle));
    }


    @And("la page {string} contient des articles en relation avec le résultat de recherche")
    public void searchContientArticles(String page) {
        assertTrue(searchPage.hasSearchResults());
    }
}
