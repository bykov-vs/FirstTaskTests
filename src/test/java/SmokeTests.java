import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

import java.time.Duration;

public class SmokeTests {
    static WebDriver driver;
    MainPage mainPage;

    /*
    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);
    }*/

    @Before
    public void openStartPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void addCustomerTest() {
        String firstName = "Test";
        String lastName = "Test";
        String postCode = "Test";

        mainPage.addCustomer();
        mainPage.fillOutForm(firstName, lastName, postCode);
        mainPage.submitForm();
        String alertMessage = mainPage.getAlertMessage();

        Assert.assertEquals("Customer added successfully with customer id :",
                alertMessage.split("\\d")[0]);
    }
}
