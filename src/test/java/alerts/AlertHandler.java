package alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandler {
    private Alert alert;

    public AlertHandler(WebDriver driver) {
        this.alert = driver.switchTo().alert();;
    }

    public String getAlertMessage() {
        return alert.getText();
    }

    public void accept(){
        alert.accept();
    }

    public void dismiss(){
        alert.dismiss();
    }
}
