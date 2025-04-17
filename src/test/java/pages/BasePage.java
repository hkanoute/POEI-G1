package pages;

import org.openqa.selenium.support.PageFactory;
import utils.DriverHelper;

/**
 * Base page class for all page object classes.
 * This class initializes the web elements using PageFactory.
 */
public abstract class BasePage {

    /**
     * Constructor to initialize the web elements using PageFactory.
     */
    public BasePage() {
        PageFactory.initElements(DriverHelper.driver, this);
    }
}
