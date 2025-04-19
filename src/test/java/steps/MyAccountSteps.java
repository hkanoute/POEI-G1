package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MyAccountPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyAccountSteps {
    private final MyAccountPage myAccountPage;

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
    @When("Je clique sur le bouton Add my first Address")
    public void ilCliqueSurLeBoutonAddMyFirstAddress() {
        myAccountPage.clickAddMyFirstAddressButton();
    }


    @Then("Je suis redirigé vers la page de saisie d'adresse")
    public void jeSuisRedirigéVersLaPageDeSaisieDAdresse() {
        myAccountPage.verifyAddressPage();
    }

    @When("Je clique sur le bouton Home")
    public void ilCliqueSurLeBoutonHome() {
        myAccountPage.clickHomeButton();
    }

    @Then("Je est redirigé vers la page d’accueil")
    public void ilEstRedirigéVersLaPageDAccueil() {
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
        assertEquals(myAccountPage.getMyAccountTitle().toLowerCase(),"My account".toLowerCase());
        myAccountPage.clickMyAddressesLink();
        if(myAccountPage.getMyAdresses()!= null){
            assertNotNull(myAccountPage.getName());
            assertNotNull(myAccountPage.getLastName());
            assertNotNull(myAccountPage.getAddress());
            assertNotNull(myAccountPage.getPhone());
        }

    }

    @And("Le site me permet de mettre à jour mes coordonnes")
    public void leSiteMePermetDeMettreÀJourMesCoordonnes() {

    }

    @And("Le site me permet d'ajouter une autre adresse")
    public void leSiteMePermetDAjouterUneAutreAdresse() {
    }
}
