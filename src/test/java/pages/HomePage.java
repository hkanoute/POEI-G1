package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

import static utils.DriverHelper.driver;

public class HomePage extends BasePage {
    /**
     * tester la page d'accueil:
     * 1. vérifier que la barre de menu est affichée
     */

    @FindBy(xpath = "//span[@class='shop-phone']")
    private WebElement menu_noir;
    /**
     * 2. vérifier que le lien contact us est affiché
     */
    @FindBy(id = "contact-link")
    private WebElement contact_us;
    /**
     * 3. vérifier que le lien login est affiché
     */

    @FindBy(xpath = "//a[@class='login']")
    private WebElement loginButton;
    /**
     * 3. vérifier que les onglets "women", dresses et t-shirts sont affichés et nous redirignet vers les articles de leur catégorie
     */
    @FindBy(xpath = "//a[@title='Women']")
    private WebElement women;
    @FindBy(xpath = "//div[@id = 'block_top_menu']/ul/li[2]/a")
    private WebElement dresses;
    @FindBy(xpath = "//div[@id = 'block_top_menu']/ul/li[3]/a")
    private WebElement t_shirts;
    @FindBy(xpath = "//a[@title='Blog']")
    private WebElement blog;
    /**
     * 4. vérifier que le slider de publicite nous redirige vers le site prestashop
     */
    @FindBy(id="homepage-slider")
    private WebElement pub;
    /**
     * 5. vérifier que le logo nous redirige vers la page d'accueil
     */
    @FindBy(xpath = "//div[@id = 'header_logo']/a")
    private WebElement logo;

    public String menuAffiche() {
        return menu_noir.getText();
    }
    public void navigateToHomePage() {
        driver.get(ConfigReader.getProperty("BASE_URL"));
    }



    public void clickOnContactUs() {
        contact_us.click();
    }



    public void clickLoginButton() {
        loginButton.click();
    }
    public void clickOnWomen(){
        women.click();
    }
    public void clickOnDresses(){
        dresses.click();
    }
    public void clickOnTshirts(){
        t_shirts.click();
    }
    public void clicOnBlog(){
        blog.click();
    }
    public void clicOnPub(){
        pub.click();
    }
    public String getLoginButtonText() {
        return loginButton.getText();
    }
    public void clickOnLogo() {
        logo.click();
    }
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
}
