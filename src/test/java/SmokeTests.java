import io.qameta.allure.Description;
import io.qameta.allure.Epic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Epic("Smoke Tests")
public class SmokeTests {
    WebDriver driver;
    MainPage mainPage;
    AddCustomerPage addCustomerPage;
    CustomersListPage customersListPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);
        addCustomerPage = new AddCustomerPage(driver);
        customersListPage = new CustomersListPage(driver);
    }

    @BeforeMethod
    public void openStartPage() {
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    @Description("Создание клиента")
    public void addCustomerTest() {
        String firstName = "Test";
        String lastName = "Test";
        String postCode = "Test";

        mainPage.addCustomer();
        addCustomerPage.fillOutForm(firstName, lastName, postCode);
        addCustomerPage.submitForm();
        String alertMessage = addCustomerPage.getAlertMessage();

        Assert.assertEquals("Customer added successfully with customer id :",
                alertMessage.split("\\d")[0]);
    }

    @Test
    @Description("Сортировка клиентов по имени")
    public void sortCustomersByFirstNameTest() {
        mainPage.customers();

        List<String> firstNames = customersListPage.getFirstNames();
        customersListPage.sortByFirstName();
        customersListPage.sortByFirstName();
        List<String> sortedFirstNames = customersListPage.getFirstNames();

        firstNames = firstNames.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

        Assert.assertEquals(firstNames.toArray(), sortedFirstNames.toArray());
    }

    @Test
    @Description("Поиск клиента по имени")
    public void searchCustomerByFirstNameTest() {
        String firstName = "Harry";
        mainPage.customers();
        customersListPage.enterSearchTerm(firstName);
        int size = customersListPage.getFirstNames().size();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по фамилии")
    public void searchCustomerByLastNameTest(){
        String lastName = "Potter";
        mainPage.customers();
        customersListPage.enterSearchTerm(lastName);
        int size = customersListPage.getFirstNames().size();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по почтовому индексу")
    public void searchCustomerByPostCodeTest(){
        String postCode = "E725JB";
        mainPage.customers();
        customersListPage.enterSearchTerm(postCode);
        int size = customersListPage.getFirstNames().size();

        Assert.assertTrue(size > 0);
    }

    @Test
    @Description("Поиск клиента по номеру аккаунта")
    public void searchCustomerByAccountNumberTest(){
        String accountNumber = "1004";
        mainPage.customers();
        customersListPage.enterSearchTerm(accountNumber);
        int size = customersListPage.getFirstNames().size();

        Assert.assertTrue(size > 0);
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
