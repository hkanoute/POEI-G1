package steps;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pages.CommonPage;
import pages.CartPage;
import utils.DriverHelper;
import static org.junit.Assert.assertTrue;

/**
 * CartAccessSteps class contains step definitions for accessing the cart.
 * It includes methods to navigate to different pages and verify the cart button.
 * These are used in Cucumber tests.
 */
public class CartAccessSteps {

    CommonPage commonPage = PageFactory.initElements(DriverHelper.driver, CommonPage.class);
    CartPage cartPage = PageFactory.initElements(DriverHelper.driver, CartPage.class);

    @When("Je suis sur la page {string}")
    public void jeSuisSurLaPage(String page) {
        switch (page.toLowerCase()) {
            case "my account":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php?controller=my-account");
                break;
            case "accueil":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php");
                break;
            case "woman":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php?id_category=3&controller=category");
                break;
            case "dresses":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php?id_category=8&controller=category");
                break;
            case "t-shirts":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php?id_category=5&controller=category");
                break;
            case "contact us":
                DriverHelper.driver.get("http://www.automationpractice.pl/index.php?controller=contact");
                break;
            default:
                throw new IllegalArgumentException("Page inconnue : " + page);
        }
    }

    @And("Je vois le lien vers le panier")
    public void jeVoisLeBoutonVersLePanier() {
        assertTrue("Le bouton du panier n'est pas visible !", commonPage.isCartButtonVisible());
    }

    @And("Je clique sur le lien vers le panier")
    public void jeCliqueSurLeLienVersLePanier() {
        commonPage.clickCartButton();
    }

    @Then("Je suis redirige sur la page {string}")
    public void jeSuisRedirigeSurLaPage(String titrePageAttendu) {
        String titre = cartPage.getCartTitle();
        assertTrue(titrePageAttendu.toLowerCase(), titre.toLowerCase().contains(titrePageAttendu.toLowerCase()));
    }
}
