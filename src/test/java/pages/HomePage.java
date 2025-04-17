package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;

import static utils.DriverHelper.driver;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[@class=\"login\"]")
    private WebElement loginButton;

    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
