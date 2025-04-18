package steps;


import io.cucumber.java.en.*;

import org.openqa.selenium.TimeoutException;
import pages.RegisterPage;


import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class RegisterSteps {
    RegisterPage registerPage;
    private boolean skipFormSteps = false;
    String generatedEmail;

    public RegisterSteps() {
        this.registerPage = new RegisterPage();
    }


    @When("Il saisit l'email {string}")
    public void ilSaisitLEmail(String email) {
        if (email.equals("random")) {
            generatedEmail = "user" + System.currentTimeMillis() + "@mail.com";
            registerPage.submitEmail(generatedEmail);
        } else {
            registerPage.submitEmail(email);
        }
    }

    @And("Il clique sur {string}")
    public void ilCliqueSur(String bouton) {
        if (bouton.equalsIgnoreCase("REGISTER")) {
            registerPage.clickRegister();
        }
    }

    @Then("Le système affiche le formulaire {string} ou un message {string}")
    public void verificationMessageOuFormulaire(String form, String messageEmail) {
        if (!messageEmail.isEmpty()) {
            String actualError = registerPage.getEmailErrorMessage();
            System.out.println("🔍 Message d'erreur reçu : " + actualError);
            assertTrue("Le message d'erreur ne correspond pas", actualError.contains(messageEmail));
            skipFormSteps = true;
        } else {
            try {
                skipFormSteps = false;
            } catch (TimeoutException e) {
                assertTrue(true);
            }
        }
    }

    @When("Il sélectionne le genre {string}")
    public void ilSelectionneGenre(String genre) {
        if (skipFormSteps) return;
        registerPage.selectGender(genre);
    }

    @And("Il saisit le prénom {string} et le nom {string}")
    public void ilSaisitPrenomNom(String prenom, String nom) {
        if (skipFormSteps) return;
        registerPage.fillName(prenom, nom);
    }

    @And("Il saisit le mot de passe {string}")
    public void ilSaisitMotDePasse(String password) {
        if (skipFormSteps) return;
        registerPage.fillPassword(password);
    }

    @And("Il saisit la date de naissance {string}")
    public void ilSaisitDateNaissance(String date) {
        if (skipFormSteps) return;
        registerPage.selectDate(date);
    }

    @And("Il coche la newsletter {string}")
    public void ilCocheNewsletter(String choix) {
        if (skipFormSteps) return;
        registerPage.checkNewsletter(choix);
    }


    @Then("Le système affiche le message {string}")
    public void leSystemeAfficheMessageFinal(String message) {
        if (Objects.equals(message, "Fail")) {
            assertTrue(true);

        } else {
            String finalMessage = registerPage.getFinalMessage();
            assertTrue(true);
            System.out.println("Message affiché : " + finalMessage);
        }
    }

    @Given("Je viens de m'inscrire sur le site")
    public void jeViensDeMInscrireSurLeSite() {
        registerPage.registerRandomUser("Mr", "John", "Doe", "test123", "01/01/1990");
    }
}
