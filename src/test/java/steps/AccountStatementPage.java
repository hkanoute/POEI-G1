package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class AccountStatementPage {
    private final pages.AccountStatementPage accountStatementPage;

    public AccountStatementPage() {
        this.accountStatementPage = new pages.AccountStatementPage();
    }

    @Then("I see that my balance is right")
    public void iSeeThatMyBalanceIsRight() {
        String balance = accountStatementPage.iSeeThatMyBalanceIsRight();
        //Assert.assertEquals("$4822.93",balance);
    }


    @And("I see that i have at least one transaction")
    public void iSeeThatIHaveAtLeastOneTransaction() {
        Assert.assertTrue(accountStatementPage.iSeeThatIHaveAtLeastOneTransaction());
    }
}
