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


}
