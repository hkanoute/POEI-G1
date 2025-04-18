package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class CartPage {

    @FindBy(css = "h1")
    private WebElement cartTitle;

    public String getCartTitle() {
        return cartTitle.getText().trim();
    }
}
