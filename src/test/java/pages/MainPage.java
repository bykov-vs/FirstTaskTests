package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//div[@class='center']/button[1]")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//div[@class='center']/button[3]")
    private WebElement customersButton;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addCustomer() {
        this.addCustomerButton.click();
    }

    public void customers() {
        this.customersButton.click();
    }


}
