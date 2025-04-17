package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;

import static utils.DriverHelper.driver;

public class HomePage extends BasePage {

//find la barre de menu
    @FindBy(xpath = "//span[@class='shop-phone']")
    private WebElement menu_noir;

    @FindBy(id = "contact-link")
    private WebElement contact_us;

    @FindBy(id = "center_column")
    private WebElement contact_us_page;

    @FindBy(xpath = "//a[@class=\"login\"]")
    private WebElement loginButton;


    public String menuAffiche() {
        return menu_noir.getText();
    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }


    public void clickOnContactUs() {
        contact_us.click();
    }



    public void clickLoginButton() {
        loginButton.click();
    }
}
