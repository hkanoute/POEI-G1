package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pages.MyAccountPage;
import pages.RegisterPage;
import utils.DriverHelper;

import static org.junit.Assert.*;

public class MyAccountSteps {
    private MyAccountPage myAccountPage;
    private boolean skipFormSteps = false;
    RegisterPage registerPage = PageFactory.initElements(DriverHelper.driver, RegisterPage.class);
    String generatedEmail;
    public MyAccountSteps() {
        myAccountPage = new MyAccountPage();
    }


    /**
     * Vérifie que l'utilisateur est sur la page "Mon Compte"
     */
    @Then("Je voit l'accès à la {string}")
    public void jeVoitLAccèsÀLa(String arg0) {
        myAccountPage.lUtilisateurVoitLAccèsÀ(arg0);
    }

    /**
     * Vérifie que l'utilisateur est sur la page "Mon Compte"
     */
    @When("Il clique sur le bouton Add my first Address")
    public void ilCliqueSurLeBoutonAddMyFirstAddress() {
        myAccountPage.clickAddMyFirstAddressButton();
    }


    @Then("Je suis redirigé vers la page de saisie d'adresse")
    public void jeSuisRedirigéVersLaPageDeSaisieDAdresse() {
        myAccountPage.verifyAddressPage();
    }

    @When("Il clique sur le bouton Home")
    public void ilCliqueSurLeBoutonHome() {
        myAccountPage.clickHomeButton();
    }

    @Then("Il est redirigé vers la page d’accueil")
    public void ilEstRedirigéVersLaPageDAccueil() {
        myAccountPage.verifyHomePage();
    }



    @When("Je clique sur le lien {string}")
    public void jeCliqueSurLeLien(String myaccount) {
        myAccountPage.clickMyAccountLink();

    }
/**
     * Vérifie que l'utilisateur est sur la page "Mon Compte"
 * CE test doit etre effectué avec un compte utilisateur ayant une adresse existante
     */
    @Then("Le site m'affiche un espace {string} avec mes coordonnees \\(nom prenom, addresse, telephone)")
    public void leSiteMAfficheUnEspaceAvecMesCoordonneesNomPrenomAddresseTelephone(String arg0) {
        assertEquals(myAccountPage.getMyAccountTitle(),"MY ADDRESSES");


            assertNotNull(myAccountPage.getName());
            assertNotNull(myAccountPage.getLastName());
            assertNotNull(myAccountPage.getAddress());
            assertNotNull(myAccountPage.getPhone());



    }

    @Then("Le site me permet de mettre à jour mes coordonnes")
    public void leSiteMePermetDeMettreÀJourMesCoordonnes() {
        myAccountPage.clickMyAddressesLink();
        assertEquals(myAccountPage.getMyAccountTitle(),"MY ADDRESSES");
        myAccountPage.clickUpdateButton();
        myAccountPage.updateAddress();
    }

    @Then("Le site me permet d'ajouter une autre adresse")
    public void leSiteMePermetDAjouterUneAutreAdresse() {
        myAccountPage.clickMyAddressesLink();
        myAccountPage.clickAddNewAddressButton();
        myAccountPage.addSecondAdress();
    }

    @When("Je clique sur le bouton Add my first Address")
    public void jeCliqueSurLeBoutonAddMyFirstAddress() {
        myAccountPage.clickMyAddressesLink();
        myAccountPage.clickAddNewAddressButton();
        myAccountPage.addNewAdress();

    }

    @And("Je peux modifier les champs obligatoire du formulaire")
    public void jePeuxModifierLesChampsObligatoireDuFormulaire() {

        myAccountPage.clickUpdateButton();
        myAccountPage.updateAddress();
    }

    @Given("Je viens de m'inscrire sur le site")
    public void jeViensDeMInscrireSurLeSite() {
        registerPage.register("random",
                "Mr",
                "Jean",
                "Dupont",
                "Password123!",
                "12/04/1990",
                "oui",
                "Bienvenue sur votre compte");


    }
}
