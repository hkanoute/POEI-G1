package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import static utils.DriverHelper.driver;


/**
 * MyAccountPage class represents the "My Account" page of the application.
 */
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

    @FindBy(xpath = "//h1")
    private WebElement myAccountTitle;

    @FindBy(xpath = "//a[@class='account']")
    private WebElement myAccountLink;

    @FindBy(xpath = "//li/a[@title='Addresses']")
    private WebElement myAddressesLink;

    @FindBy(xpath = "//ul[@class=\"last_item item box\"]/li[1]/h3")
    private WebElement myAdresses;

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

    @FindBy(id = "submitAddress")
    private WebElement submitAddress;

    @FindBy(xpath="//ul[@class = \"last_item item box\"]/li[2]/span[1]")
    private WebElement first_name_address;

    @FindBy(xpath= "//ul[@class = \"last_item item box\"]/li[2]/span[2]")
    private WebElement last_name_address;

    @FindBy(id="firstname")
    private WebElement inputFirstName;

    @FindBy(id="lastname")
    private   WebElement inputLastName;

    @FindBy (xpath = "//ul[@class = \"last_item item box\"]/li[4]/span[@class='address_address1']")
    private WebElement address;

    @FindBy(xpath="//ul[@class = \"last_item item box\"]/li[7]/span[@class='address_phone']")
    private WebElement phone_address;

    @FindBy(xpath = "//a[@title='Add an address']")
    private WebElement addNewAddressButton;

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

    @FindBy(xpath="//div[@class='addresses']/p/strong")
    private WebElement adressesList;


    /**
     * Method to get the text of the "Adresses" list.
     * @return The text of the "Adresses" list.
     */
    public String getAdressesListText() {
        return adressesList.getText();
    }

    /**
     * Method to get the text of the alert message when there are no addresses.
     * @return The text of the alert message.
     */
    public String getAlertNoAdressesText() {
        return alertNoAdresses.getText();
    }


    String pageText = driver.getPageSource();

    /**
     * Methode to add a new address.
     */
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

    /**
     * Method to add a second address.
     */
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

    /**
     * Method to update the address.
     */
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

    /**
     * Method to click on the "Add New Address" button.
     */
    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
    }

    /**
     * Method to click on the "Update" button.
     */
    public void clickUpdateButton() {
        updateButton.click();
    }

    /**
     * Method to get the text of the "Update" button.
     * @return The text of the "Update" button.
     */
    public String getNewAddressButtonText(){
        return addNewAddressButton.getText();
    }


    /**
     * Method to get the text of phone.
     * @return The text of the phone.
     */
    public String getPhone() {
        return phone_address.getText();
    }

    /**
     * Method to get the text of the address.
     * @return The text of the address.
     */
    public String getAddress() {
        return address.getText();
    }

    /**
     * Method to get the text of the "Adresses"
     * @return The text of the "Adresses"
     */
    public String getMyAdresses() {
        return myAdresses.getText();
    }

    /**
     * Method to click on the "My Addresses" link.
     */
    public void clickMyAddressesLink() {
        myAddressesLink.click();
    }

    /**
     * Method to click on the "My Account" link.
     */
    public void clickMyAccountLink() {
        myAccountLink.click();
    }

    /**
     * Method to get the text of the "My Account" title.
     * @return The text of the "My Account" as string.
     */
    public String getMyAccountTitle() {
        return myAccountTitle.getText();
    }

    /**
     * Method to get the text of the first name address.
     * @return string
     */
    public String getName() {
        return first_name_address.getText();
    }

    /**
     * Method to get the text of the last name address.
     * @return string
     */
    public String getLastName() {
        return last_name_address.getText();
    }


    /**
     * Method to verify if the user can access the specified section.
     * @param arg0
     */
    public void voitAccesA(String arg0) {
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


    /**
     * Method to verify if the body ID contains the specified ID.
     * @param id
     */
    public void bodyIdContains(String id) {
        String bodyId = driver.findElement(By.tagName("body")).getDomAttribute("id");
        assert bodyId != null;
    }

    /**
     * Method to click on the "My First Address" button.
     */
    public void clickAddMyFirstAddressButton() {
        myFirstAddress.click();
    }

    /**
     * Methode that allows to verify if the user is on the address page.
     */
    public void verifyAddressPage() {
        bodyIdContains("address");
    }

    /**
     * Method to click on the "Home" button.
     */
    public void clickHomeButton() {
        homeButton.click();
    }

    /**
     * Method to verify if the user is on the home page.
     */
    public void verifyHomePage() {
        bodyIdContains("index");
    }

    /**
     * Method to fill in the mandatory fields for the address.
     */
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

        submitAddress.click();


    }
}
