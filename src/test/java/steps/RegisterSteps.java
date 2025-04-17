package steps;


import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pages.RegisterPage;
import utils.DriverHelper;

public class RegisterSteps {


    RegisterPage registerPage = PageFactory.initElements(DriverHelper.driver, RegisterPage.class);
    String generatedEmail;


    @Given("L'utilisateur est sur la page {string}")
    public void utilisateurEstSurLaPage(String page) {
        
    }

}
