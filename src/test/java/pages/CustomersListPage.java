package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersListPage {
    @FindBy(xpath = "//table/thead/tr/td/a")
    private WebElement firstNameCol;

    @FindBy(xpath = "//div[@class='input-group']/input")
    private WebElement searchInput;

    @FindBy(xpath = "//tbody/tr/td[1]")
    List<WebElement> firstNames;

    public CustomersListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sortByFirstName() {
        firstNameCol.click();
    }

    public String[] getFirstNames() {
        return firstNames.stream()
                .map(WebElement::getText)
                .toArray(String[]::new);
    }

    public void enterSearchTerm(String term) {
        searchInput.clear();
        this.searchInput.sendKeys(term);
    }
}
