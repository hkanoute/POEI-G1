package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverHelper;
import java.time.Duration;

/**
 * SearchPage class represents the search page of the application.
 * It contains methods to interact with elements on the search page.
 */
public class SearchPage {

    @FindBy(id = "search_query_top")
    WebElement searchField;

    @FindBy(name = "submit_search")
    WebElement searchButton;

    @FindBy(css = ".ac_results li")
    WebElement suggestion;

    @FindBy(id = "search")
    WebElement searchPage;

    @FindBy(css = "h1")
    WebElement productTitle;

    @FindBy(css = ".product_list .product-container")
    java.util.List<WebElement> searchResults;

    /**
     * Method that checks if the search field is displayed on the page.
     * @return boolean indicating if the search field is displayed.
     */
    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    /**
     * Method that checks if the search button is displayed on the page.
     * @return boolean indicating if the search button is displayed.
     */
    public boolean isSearchButtonDisplayed() {
        return searchButton.isDisplayed();
    }


    /**
     * Method fills the search field with the given article name.
     */
    public void fillSearchField(String article) {
        searchField.clear();
        searchField.sendKeys(article);

        if (!article.isEmpty()) {
            try {
                new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ac_results")));
            } catch (TimeoutException e) {
                System.out.println("Aucune suggestion affichée pour l'article : " + article);
            }
        }
    }

    /**
     * Method that clicks on the suggested item in the search results.
     */
    public void selectSuggestion() {
        suggestion.click();
    }

    /**
     * Method that clicks on the search button.
     */
    public void clickSearchButton() {
        searchButton.click();
    }

    /**
     * Method that checks if the search results are displayed.
     * @return boolean indicating if the search results are displayed.
     */
    public boolean isOnProductPage() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(5));
            WebElement titre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            return titre.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method that gets the product title from the search results.
     * @return String representing the product title.
     */
    public String getPageTitle() {
        return DriverHelper.driver.getTitle();
    }


    /**
     * Methode that verify if the search results are displayed.
     * @return boolean indicating if the search results are displayed.
     */
    public boolean hasSearchResults() {
        return !searchResults.isEmpty();
    }

    /**
     * Method that verify if the search page is displayed.
     * @return boolean indicating if the search page is displayed.
     */
    public boolean isSearchPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(searchPage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


}
