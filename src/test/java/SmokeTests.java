import io.qameta.allure.Description;
import io.qameta.allure.Epic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Epic("Smoke Tests")
public class SmokeTests {

    ChromeOptions options;

    @BeforeClass
    public void setOptions(){
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver","/");
    }

    public WebDriver setup(){
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
        return driver;
    }

    @Test
    @Description("Создание клиента")
    public void addCustomerTest() {
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);
        String firstName = "Test";
        String lastName = "Test";
        String postCode = "Test";

        pages.mainPage.addCustomer();
        pages.addCustomerPage.fillOutForm(firstName, lastName, postCode);
        pages.addCustomerPage.submitForm();
        String alertMessage = pages.addCustomerPage.getAlertMessage();

        driver.quit();

        Assert.assertEquals("Customer added successfully with customer id :",
                alertMessage.split("\\d")[0]);
    }

    @Test
    @Description("Сортировка клиентов по имени")
    public void sortCustomersByFirstNameTest() {
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);

        pages.mainPage.customers();
        List<String> firstNames = pages.customersListPage.getFirstNames();
        pages.customersListPage.sortByFirstName();
        pages.customersListPage.sortByFirstName();
        List<String> sortedFirstNames = pages.customersListPage.getFirstNames();
        firstNames = firstNames.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

        driver.quit();

        Assert.assertEquals(firstNames.toArray(), sortedFirstNames.toArray());
    }

    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstNameTest() {
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);
        String firstName = "Harry";

        pages.mainPage.customers();
        pages.customersListPage.enterSearchTerm(firstName);
        int size = pages.customersListPage.getFirstNames().size();

        driver.quit();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastNameTest(){
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);
        String lastName = "Potter";

        pages.mainPage.customers();
        pages.customersListPage.enterSearchTerm(lastName);
        int size = pages.customersListPage.getFirstNames().size();

        driver.quit();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCodeTest(){
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);
        String postCode = "E725JB";

        pages.mainPage.customers();
        pages.customersListPage.enterSearchTerm(postCode);
        int size = pages.customersListPage.getFirstNames().size();

        driver.quit();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumberTest(){
        WebDriver driver = setup();
        TestPages pages = new TestPages(driver);
        String accountNumber = "1004";

        pages.mainPage.customers();
        pages.customersListPage.enterSearchTerm(accountNumber);
        int size = pages.customersListPage.getFirstNames().size();

        driver.quit();

        Assert.assertTrue(size > 0);
    }
}
