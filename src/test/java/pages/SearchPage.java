package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverHelper;

import java.time.Duration;
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

    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return searchButton.isDisplayed();
    }

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

    public void selectSuggestion() {
        suggestion.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isOnProductPage() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(5));
            WebElement titre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            return titre.isDisplayed(); // ou une autre vérif
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return DriverHelper.driver.getTitle();
    }

    public boolean hasSearchResults() {
        return !searchResults.isEmpty();
    }

    public boolean isSearchPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(searchPage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


}
