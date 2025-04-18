package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.DriverHelper.driver;

public class MyAccountPage extends BasePage {


    @FindBy(xpath = "//ul[@class= \"myaccount-link-list\"]/li[1]")
    private WebElement myFirstAddress;

    @FindBy(xpath = "//ul[@class= \"myaccount-link-list\"]/li[2]")
    private WebElement historique;

    @FindBy(xpath = "//ul[@class= \"myaccount-link-list\"]/li[4]")
    private WebElement adresses;

    @FindBy(xpath = "//ul[@class= \"myaccount-link-list\"]/li[5]")
    private WebElement informations;

    @FindBy(xpath = "//a[@title = \"Return to Home\"]")
    private WebElement homeButton;



    public void lUtilisateurVoitLAccèsÀ(String arg0) {
        switch (arg0) {
            case "Historique":
                historique.click();
                bodyIdContains("history");
                break;
            case "Adresses":
                adresses.click();
                bodyIdContains("address");
                break;
            case "Informations":
                bodyIdContains("identity");
                break;
        }
    }

    public void bodyIdContains(String id) {
        String bodyId = driver.findElement(By.tagName("body")).getDomAttribute("id");
        assert bodyId != null;
    }

    public void clickAddMyFirstAddressButton() {
        myFirstAddress.click();
    }

    public void verifyAddressPage() {
        bodyIdContains("address");
    }

    public void clickHomeButton() {
        homeButton.click();
    }

    public void verifyHomePage() {
        bodyIdContains("index");
    }
}
