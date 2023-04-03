package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.Row;
import pages.elements.Table;

import java.util.List;

public class CustomersListPage {
    private WebDriver driver;
    @FindBy(xpath = "//table/thead/tr/td/a")
    private WebElement firstNameCol;

    @FindBy(xpath = "//div[@class='input-group']/input")
    private WebElement searchInput;

    List<Row> rows;

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private void initRows() {
        rows = new Table(driver.findElement(By.xpath("//tbody")))
                .getRows();
    }

    @Step("Сортировка таблицы по именам")
    public void sortByFirstName() {
        firstNameCol.click();
        initRows();
    }

    @Step("Получение имен клиентов из таблицы")
    public String[] getFirstNames() {
        initRows();
        return rows.stream().map((row) -> row.getCell(0).getText())
                .toArray(String[]::new);
    }

    @Step("Очистка поля для поиска и заполнение новым словом")
    public void enterSearchTerm(String term) {
        searchInput.clear();
        this.searchInput.sendKeys(term);
        initRows();
    }
}
