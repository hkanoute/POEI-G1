package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * CartPage class represents the cart page of the application.
 * It contains methods to interact with elements on the cart page.
 */
public class CartPage {

    @FindBy(css = "h1")
    private WebElement cartTitle;

    /**
     * Gets the title of the cart page.
     *
     * @return the title of the cart page as a String.
     */
    public String getCartTitle() {
        return cartTitle.getText().trim();
    }
}
