package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.DriverHelper;

import static org.junit.Assert.assertTrue;
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
    @FindBy (xpath="//ul[@class=\"last_item item box\"]/li[1]/h3")
    private WebElement myAdresses;
    @FindBy(xpath="//ul[@class = \"last_item item box\"]/li[2]/span[1]")
    private WebElement first_name;
    @FindBy(xpath= "//ul[@class = \"last_item item box\"]/li[2]/span[2]")
    private WebElement last_name;
    @FindBy(id="firstname")
    private WebElement inputFirstName;
    @FindBy(id="lastname")
    private   WebElement inputLastName;
    @FindBy (xpath = "//ul[@class = \"last_item item box\"]/li[4]/span[@class='address_address1']")
    private WebElement address;
    @FindBy(xpath="//ul[@class = \"last_item item box\"]/li[7]/span[@class='address_phone']")
    private WebElement phone;
    @FindBy(xpath = "//a[@title='Add an address']")
    private WebElement addNewAddressButton;
    @FindBy(xpath = "//input[@id='address1']")
    private WebElement address1;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id="id_state")
    private WebElement state;
    @FindBy(id="postcode")
    private WebElement postCode;
    @FindBy(id="id_country")
    private WebElement country;
    @FindBy(id="phone_mobile")
    private WebElement phoneMobile;
    @FindBy(id="submitAddress")
    private WebElement saveButton;
    @FindBy(xpath = "//p[@class=\"alert alert-warning\"]/a")
    private WebElement alertNoAdresses;
    @FindBy(xpath = "//a[@title='Update']")
    private WebElement updateButton;
    @FindBy(id="alias")
    private WebElement adressTitle;
    String pageText = driver.getPageSource();
    @FindBy(xpath="//div[@class='addresses']/p/strong")
    private WebElement adressesList;


    public String getAdressesListText() {
        return adressesList.getText();
    }
    public String getAlertNoAdressesText() {
        return alertNoAdresses.getText();
    }
    public void addNewAdress(){
        inputFirstName.sendKeys("auralion");
        inputLastName.sendKeys("second user last name");
        address1.sendKeys("12 rue de la paix");
        city.sendKeys("Phoenix");
        Select selectState = new Select(state);
        selectState.selectByValue("3");
        postCode.sendKeys("12345");
        Select selectcountry = new Select(country);
        selectcountry.selectByValue("21");
        phoneMobile.sendKeys("1234567890");
        saveButton.click();

    }
    public void addSecondAdress(){
        inputFirstName.clear();
        inputFirstName.sendKeys("second user");
        inputLastName.clear();
        inputLastName.sendKeys("second user last name");
        address1.sendKeys("125 rue de l'amour");
        city.sendKeys("Phoenix");
        Select selectState = new Select(state);
        selectState.selectByValue("3");
        postCode.sendKeys("12345");
        Select selectcountry = new Select(country);
        selectcountry.selectByValue("21");
        phoneMobile.sendKeys("1234567890");
        adressTitle.sendKeys("second address");
        saveButton.click();
    }
    public void updateAddress(){
        inputFirstName.clear();
        inputFirstName.sendKeys("john");
        inputLastName.clear();
        inputLastName.sendKeys("doe");
        address1.clear();
        address1.sendKeys("123 rue de la paix");
        city.clear();
        city.sendKeys("Phoenix");

        Select selectState = new Select(state);
        selectState.selectByValue("3");
        postCode.clear();
        postCode.sendKeys("12345");
        Select selectcountry = new Select(country);
        selectcountry.selectByValue("21");
        phoneMobile.clear();
        phoneMobile.sendKeys("1234567890");
        saveButton.click();
    }
    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
    }
    public void clickUpdateButton() {
        updateButton.click();
    }
    public String getNewAddressButtonText(){
        return addNewAddressButton.getText();
    }
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
