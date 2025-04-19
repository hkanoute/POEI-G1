package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static utils.DriverHelper.driver;

public class MyAccountPage extends BasePage {
    private RegisterPage registerPage;

    /**
     * Constructor to initialize the web elements using PageFactory.
     */
    public MyAccountPage() {
        this.registerPage = new RegisterPage();
    }


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


    public String getPhone() {
        return phone.getText();
    }
    public String getAddress() {
        return adresses.getText();
    }
    public String getMyAdresses() {
        return myAdresses.getText();
    }

    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "id_state")
    private WebElement stateSelect;

    @FindBy(id = "postcode")
    private WebElement postcodeSelect;

    @FindBy(id = "id_country")
    private WebElement countrySelect;

    @FindBy(id = "phone")
    private WebElement phone;



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
    return firstName.getText();
}
public String getLastName()
{
    return  lastName.getText();
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

    public void fillMandatoryFieldsForAddress() {
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        address1.sendKeys("123 Main St");
        city.sendKeys("New York");

        Select stateSelectDropdown = new Select(stateSelect);
        stateSelectDropdown.selectByVisibleText("New York");

        postcodeSelect.sendKeys("10001");
        Select countrySelectDropdown = new Select(countrySelect);
        countrySelectDropdown.selectByVisibleText("United States");

        phone.sendKeys("1234567890");
    }
}
