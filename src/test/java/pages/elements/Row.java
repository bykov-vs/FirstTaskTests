package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Row {
    @FindBy(xpath = "td")
    private List<WebElement> cells;

    public Row(WebElement row) {
        PageFactory.initElements(row, this);
    }

    public List<WebElement> getCells() {
        return cells;
    }

    public WebElement getCell(int index) {
        return this.cells.get(index);
    }
}
