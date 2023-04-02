package pages;

import io.qameta.allure.Step;
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

    @Step("Сортировка таблицы по именам")
    public void sortByFirstName() {
        firstNameCol.click();
    }

    @Step("Получение имен клиентов из таблицы")
    public String[] getFirstNames() {
        return firstNames.stream()
                .map(WebElement::getText)
                .toArray(String[]::new);
    }

    @Step("Очистка поля для поиска и заполнение новым словом")
    public void enterSearchTerm(String term) {
        searchInput.clear();
        this.searchInput.sendKeys(term);
    }
}
