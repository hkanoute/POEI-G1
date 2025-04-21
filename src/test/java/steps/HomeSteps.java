package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactUsPage;
import pages.HomePage;
import utils.ConfigReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.DriverHelper.driver;
public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        this.homePage = new HomePage();
    }

    public ContactUsPage contactUsPage = new ContactUsPage();


    @Then("La barre de menu noire est affichée")
    public void laBarreDeMenuNoireEstAffichée() {
        assertEquals("Call us now: 0123-456-789", homePage.menuAffiche());
    }

    @And("Le lien {string} redirige vers la page de contact")
    public void leLienRedirigeVersLaPageDeContact(String contact) {
        homePage.clickOnContactUs();
        contact = contactUsPage.getContactUsPageTitle();
        assertEquals("Contact us - My Shop", contact);
    }

    @Given("je suis sur la page d'accueil")
    public void jeSuisSurLaPageDAccueil() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }


    @And("Le lien {string} redirige vers la page d’authentification")
    public void leLienRedirigeVersLaPageDAuthentification(String authentication) {

        homePage.clickLoginButton();
        authentication = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account", authentication);

    }

    @And("Les onglets {string}, {string} et {string} redirigent vers les articles de leur catégorie")
    public void lesOngletsEtRedirigentVersLesArticlesDeLeurCatégorie(String women, String dresses, String tshirts) throws InterruptedException {
        homePage.clickOnWomen();
        women = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php?id_category=3&controller=category", women);
        homePage.clickOnDresses();
        dresses = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php?id_category=8&controller=category", dresses);
        homePage.clickOnTshirts();
        tshirts = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php?id_category=5&controller=category", tshirts);


    }

    @And("L'onglet {string} redirige vers le site Prestashop")
    public void lOngletRedirigeVersLeSitePrestashop(String blog) throws InterruptedException {
        String mainWindowHandle = driver.getWindowHandle();
        homePage.clicOnBlog();
        blog = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(blog)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        Thread.sleep(2000);
        blog = driver.getCurrentUrl();
        assertEquals("https://prestashop.com/blog/", blog);
        driver.close();
        driver.switchTo().window(mainWindowHandle);

    }

    @And("Les publicités redirigent vers le site partenaire Prestashop")
    public void lesPublicitésRedirigentVersLeSitePartenairePrestashop() {
        homePage.navigateToHomePage();
        homePage.clicOnPub();
        String pub = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(pub)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        pub = driver.getCurrentUrl();
        assertEquals("https://prestashop.com/", pub);
    }

    @When("Cliquer sur logo {string}")
    public void cliquerSurLogo(String logo) {

        homePage.clickOnLogo();
        logo = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php", logo);

    }

    @Then("Redirection vers {string}")
    public void redirectionVers(String home) {
        home = driver.getCurrentUrl();
        assertEquals("http://www.automationpractice.pl/index.php", home);
    }

    @Then("Verifier si la page contient le logo {string}")
    public void verifierSiLaPageContientLeLogo(String logo) {
        assertTrue(homePage.isLogoDisplayed());
    }

}
