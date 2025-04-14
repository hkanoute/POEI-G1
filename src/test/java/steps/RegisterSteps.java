package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.RegisterPage;

public class RegisterSteps {
    private final RegisterPage registerPage;

    public RegisterSteps() {
        this.registerPage = new RegisterPage();
    }

    @Then("Im redirected to the Signup page")
    public void imRedirectedToTheSignupPage() {
        String formTitle = registerPage.imRedirectedToTheSignupPage();

        Assert.assertEquals("Signing up is easy!", formTitle);
    }

    @And("I fill the first name input")
    public void iFillTheFirstNameInput() {
        registerPage.iFillTheFirstNameInput();
    }

    @And("I fill the last name input")
    public void iFillTheLastNameInput() {
        registerPage.iFillTheLastNameInput();
    }

    @And("I fill the Address input")
    public void iFillTheAddressInput() {
        registerPage.iFillTheAddressInput();
    }

    @And("I fill the City input")
    public void iFillTheCityinput() {
        registerPage.iFillTheCityInput();
    }

    @And("I fill the State input")
    public void iFillTheStateInput() {
        registerPage.iFillTheStateInput();
    }

    @And("I fill the Zip Code input")
    public void iFillTheZipCodecInput() {
       registerPage.iFillTheZipCodecInput();
    }

    @And("I fill the Phone input")
    public void iFillThePhoneInput() {
        registerPage.iFillThePhoneInput();

    }

    @And("I fill the SSN input")
    public void iFillTheSSNInput() {
        registerPage.iFillTheSSNInput();
    }

    @And("I fill the Username input")
    public void iFillTheUsernameInput() {
        registerPage.iFillTheUsernameInput();
    }

    @And("I fill the Password input")
    public void iFillThePasswordInput() {
        registerPage.iFillThePasswordInput();
    }

    @And("I fill the Confirm input")
    public void iFillTheConfirmInput() {
        registerPage.iFillTheConfirmInput();
    }

    @And("I clock on register")
    public void iClockOnRegister() {
        registerPage.iClickOnRegister();
    }

    @And("Im successfully registered")
    public void imSuccessfullyRegistered() {
        String succesMessage = registerPage.getSuccessMessage();
        Assert.assertEquals("Your account was created successfully. You are now logged in.",succesMessage);
    }


    @Given("the user isn't already registered")
    public void theUserIsnTAlreadyRegistered() {
        return;
    }
}
