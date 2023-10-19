package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='form-group'][1]/input")
    private WebElement firstNameInput;

    @FindBy(xpath = "//div[@class='form-group'][2]/input")
    private WebElement lastNameInput;

    @FindBy(xpath = "//div[@class='form-group'][3]/input")
    private WebElement postCode;

    @FindBy(css = "button[type='submit']")
    private WebElement addCustomerSubmitButton;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutForm(String firstName, String lastName, String postCode) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.postCode.sendKeys(postCode);
    }

    public void submitForm() {
        this.addCustomerSubmitButton.click();
    }

    public String getAlertMessage() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }
}
