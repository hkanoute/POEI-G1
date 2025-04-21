package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MyAccountPage;
import pages.RegisterPage;


import static org.junit.Assert.*;

/**
 * MyAccountSteps class contains step definitions for the "My Account" page of the application.
 * It includes methods to interact with elements on the "My Account" page and verify their behavior.
 * These are used in Cucumber tests.
 */
public class MyAccountSteps {
    private final MyAccountPage myAccountPage;
    RegisterPage registerPage;
    String generatedEmail;

    public MyAccountSteps() {
        myAccountPage = new MyAccountPage();
        registerPage = new RegisterPage();
    }



    @Then("Je voit l'accès à la {string}")
    public void jeVoisAccesA(String arg0) {
        myAccountPage.voitAccesA(arg0);
    }

    /**
     * Vérifie que l'utilisateur est sur la page "Mon Compte"
     */
    @When("Je clique sur le bouton Add my first Address")
    public void ilCliqueSurLeBoutonAddMyFirstAddress() {
        myAccountPage.clickAddMyFirstAddressButton();
    }


    @Then("Je suis redirigé vers la page de saisie d'adresse")
    public void jeSuisRedirigeVersLaPageDeSaisieDAdresse() {
        myAccountPage.verifyAddressPage();
    }

    @When("Je clique sur le bouton Home")
    public void ilCliqueSurLeBoutonHome() {
        myAccountPage.clickHomeButton();
    }

    @Then("Je est redirigé vers la page d’accueil")
    public void ilEstRedirigeVersLaPageDAccueil() {
        myAccountPage.verifyHomePage();
    }

    @And("Je peux modifier les champs obligatoire du formulaire")
    public void jePeuxModifierLesChampsObligatoireDuFormulaire() {
        myAccountPage.fillMandatoryFieldsForAddress();
    }


    @When("Je clique sur le lien {string}")
    public void jeCliqueSurLeLien(String myaccount) {
        myAccountPage.clickMyAccountLink();

    }


    @Then("Le site m'affiche un espace {string} avec mes coordonnees \\(nom prenom, addresse, telephone)")
    public void leSiteMAfficheUnEspaceAvecMesCoordonneesNomPrenomAddresseTelephone(String arg0) {
        assertEquals(myAccountPage.getMyAccountTitle().toLowerCase(), "My account".toLowerCase());

        myAccountPage.clickMyAddressesLink();
        if (myAccountPage.getMyAdresses() != null) {
            assertNotNull(myAccountPage.getName());
            assertNotNull(myAccountPage.getLastName());
            assertNotNull(myAccountPage.getAddress());
            assertNotNull(myAccountPage.getPhone());


        }
    }

    @Then("Le site me permet de mettre à jour mes coordonnes")
    public void leSiteMePermetDeMettreÀJourMesCoordonnes() {
        myAccountPage.clickMyAddressesLink();
        assertEquals(myAccountPage.getMyAccountTitle(), "MY ADDRESSES");
        myAccountPage.clickUpdateButton();
        myAccountPage.updateAddress();
    }

    @Then("Le site me permet d'ajouter une autre adresse")
    public void leSiteMePermetDAjouterUneAutreAdresse() {
        myAccountPage.clickMyAddressesLink();
        myAccountPage.clickAddNewAddressButton();
        myAccountPage.addSecondAdress();
    }


    @And("Je peux mettre a jour mes coordonnes")
    public void jePeuxMettreAJourMesCoordonnes() {
        myAccountPage.clickUpdateButton();
        myAccountPage.updateAddress();
    }

    @Given("Je viens de m'inscrire sur le site")
    public void jeViensDeMInscrireSurLeSite() {
        registerPage.registerRandomUser("Mr", "John", "Doe", "password123", "01/01/1990");
    }
}
