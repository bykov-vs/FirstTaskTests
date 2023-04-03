package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Row> rows;

    public Table(WebElement table) {
        rows = new ArrayList<>();
        List<WebElement> rows = table.findElements(By.xpath("tr"));
        for (WebElement row : rows) {
            this.rows.add(new Row(row));
        }
    }

    public List<Row> getRows() {
        return rows;
    }
}
