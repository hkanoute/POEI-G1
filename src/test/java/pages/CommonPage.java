package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class CommonPage {

    @FindBy(css = "a[title='View my shopping cart']")
    private WebElement cartButton;

    public boolean isCartButtonVisible() {
        return cartButton.isDisplayed();
    }

    public void clickCartButton() {
        cartButton.click();
    }

}
