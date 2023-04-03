package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[1]/input")
    private WebElement firstNameInput;

    @FindBy(xpath = "//div[2]/input")
    private WebElement lastNameInput;

    @FindBy(xpath = "//div[3]/input")
    private WebElement postCode;

    @FindBy(css = "button[type='submit']")
    private WebElement addCustomerSubmitButton;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение формы значениями")
    public void fillOutForm(String firstName, String lastName, String postCode) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.postCode.sendKeys(postCode);
    }

    @Step("Отправка формы")
    public void submitForm() {
        this.addCustomerSubmitButton.click();
    }


}
