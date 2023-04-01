package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomersListPage {
    @FindBy(xpath = "//table/thead/tr/td/a")
    private WebElement firstNameCol;

    @FindBy(xpath = "//div[@class='input-group']/input")
    private WebElement searchInput;

    @FindAll({
            @FindBy(xpath = "//tbody/tr/td[1]")
    })
    List<WebElement> firstNames;

    public CustomersListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sortByFirstName() {
        firstNameCol.click();
    }

    public List<String> getFirstNames() {
        List<String> sortedList = new ArrayList<>();
        for (WebElement firstName : firstNames) {
            sortedList.add(firstName.getText());
        }
        return sortedList;
    }

    public void enterSearchTerm(String term) {
        searchInput.clear();
        this.searchInput.sendKeys(term);
    }
}
