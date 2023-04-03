package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.Row;

import java.util.ArrayList;
import java.util.List;

public class CustomersListPage {
    private WebDriver driver;
    @FindBy(xpath = "//table/thead/tr/td/a")
    private WebElement firstNameCol;

    @FindBy(xpath = "//div[@class='input-group']/input")
    private WebElement searchInput;

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private List<Row> getRows() {
        List<Row> rows = new ArrayList<>();
        List<WebElement> rowsElements = findRowsElements();
        for (WebElement row : rowsElements) {
            rows.add(new Row(row));
        }
        return rows;
    }

    private List<WebElement> findRowsElements(){
        WebElement table = findTable();
        return table.findElements(By.xpath("tr"));
    }

    private WebElement findTable(){
        return driver.findElement(By.xpath("//tbody"));
    }

    @Step("Сортировка таблицы по именам")
    public void sortByFirstName() {
        firstNameCol.click();
    }

    @Step("Получение имен клиентов из таблицы")
    public String[] getFirstNames() {
        List<Row> rows = getRows();
        return rows.stream().map((row) -> row.getCell(0).getText())
                .toArray(String[]::new);
    }

    @Step("Очистка поля для поиска и заполнение новым словом")
    public void enterSearchTerm(String term) {
        searchInput.clear();
        this.searchInput.sendKeys(term);
    }
}
