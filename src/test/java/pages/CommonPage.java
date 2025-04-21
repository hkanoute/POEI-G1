package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * CommonPage class represents the common elements on the application pages.
 * It contains methods to interact with elements that are common across different pages.
 */
public class CommonPage {

    @FindBy(css = "a[title='View my shopping cart']")
    private WebElement cartButton;
    /**
     * Method that checks if the cart button is visible on the page.
     */
    public boolean isCartButtonVisible() {
        return cartButton.isDisplayed();
    }

    /**
     * Method that clicks the cart button on the page.
     */
    public void clickCartButton() {
        cartButton.click();
    }

}
