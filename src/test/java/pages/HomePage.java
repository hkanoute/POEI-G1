package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


    @FindBy(xpath = "//a[@href = \"openaccount.htm\"]")
    private WebElement openAccountLink;

    @FindBy(xpath = "//h1[contains(text(),'Open New Account')]")
    private WebElement openAccountTitle;

    @FindBy(xpath = "//input[@type=\"button\"]")
    private WebElement openAccountSubmitButton;

    @FindBy(xpath = "(//tr)[2]//a")
    private WebElement accountLink;

    public void iClickOnOpenAccount() {
        openAccountLink.click();
    }

    public String imRedirectedToTheOpenAccountPage() {
        return openAccountLink.getText();
    }

    public void iFinishCreatingMyAccount() {
        openAccountSubmitButton.click();
    }

    public void iClickOnMyAccount() {
        accountLink.click();
    }


}
