package pages;

import org.openqa.selenium.support.PageFactory;
import utils.DriverHelper;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverHelper.driver, this);
    }
}
