package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MyAccountPage;

public class MyAccountSteps {
    private MyAccountPage myAccountPage;

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
}
