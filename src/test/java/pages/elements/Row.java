package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Row {
    private List<WebElement> cells;

    public Row(WebElement row) {
        this.cells = row.findElements(By.xpath("td"));
    }

    public List<WebElement> getCells() {
        return cells;
    }

    public WebElement getCell(int index) {
        return this.cells.get(index);
    }
}
