package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    private WebDriver driver;

    @FindBy(xpath = "(//input[contains(@class,'form-control')])[1]")
    private WebElement firstNameInput;

    @FindBy(xpath = "(//input[contains(@class,'form-control')])[2]")
    private WebElement lastNameInput;

    @FindBy(xpath = "(//input[contains(@class,'form-control')])[3]")
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
