package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        this.homePage = new HomePage();
    }


    @When("I click on open account")
    public void iClickOnOpenAccount() {
        homePage.iClickOnOpenAccount();
    }


    @Then("Im redirected to the open account page")
    public void imRedirectedToTheOpenAccountPage() {
        String title = homePage.imRedirectedToTheOpenAccountPage();

        Assert.assertEquals("Open New Account",title);
    }

    @And("I finish creating my account")
    public void iFinishCreatingMyAccount() {
        homePage.iFinishCreatingMyAccount();
    }

    @When("I click on my account")
    public void iClickOnMyAccount() {
        homePage.iClickOnMyAccount();
    }


}
