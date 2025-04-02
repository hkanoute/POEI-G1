package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountStatementPage extends BasePage{

    @FindBy(id = "balance")
    private WebElement balance;

    public String iSeeThatMyBalanceIsRight(){
        return balance.getText();
    }

    @FindBy(xpath = "//td//a")
    private WebElement transaction;

    public Boolean iSeeThatIHaveAtLeastOneTransaction() {
        return  transaction.isDisplayed();
    }
}
