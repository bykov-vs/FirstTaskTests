package alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertExecutor {

    public static String getAlertMessage(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }
}
