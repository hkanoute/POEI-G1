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
    /**
     * element representant la page my account
     */
    @FindBy(xpath = "//h1")
    private WebElement myAccountTitle;
    @FindBy(xpath = "//a[@class='account']")
    private WebElement myAccountLink;
    @FindBy(xpath = "//li/a[@title='Addresses']")
    private WebElement myAddressesLink;
    @FindBy (xpath="//h3")
    private WebElement myAdresses;
    @FindBy(xpath = "//ul[@class = \"last_item item box\"]/li[2]/span[1]")
    private WebElement first_name;
    @FindBy(xpath = "//ul[@class = \"last_item item box\"]/li[2]/span[2]")
    private WebElement last_name;
    @FindBy (xpath = "//ul[@class = \"last_item item box\"]/li[4]/span[@class='address_address1']")
    private WebElement address;
    @FindBy(xpath="//ul[@class = \"last_item item box\"]/li[7]/span[@class='address_phone']")
    private WebElement phone;
    public String getPhone() {
        return phone.getText();
    }
    public String getAddress() {
        return address.getText();
    }
    public String getMyAdresses() {
        return myAdresses.getText();
    }

    public void clickMyAddressesLink() {
        myAddressesLink.click();
    }
    public void clickMyAccountLink() {
        myAccountLink.click();
    }

    public String getMyAccountTitle() {
        return myAccountTitle.getText();
    }
public String getName()
{
    return first_name.getText();
}
public String getLastName()
{
    return  last_name.getText();
}

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
