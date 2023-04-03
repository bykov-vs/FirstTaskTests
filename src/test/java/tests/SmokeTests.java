package tests;

import alerts.AlertExecutor;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;
import utils.ChromeDriverCreator;
import utils.DataProviders;
import utils.SortHelper;

@Epic("Smoke Tests")
public class SmokeTests {
    static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() {
        localDriver.set(ChromeDriverCreator.create());
    }

    @Test(dataProvider = "createCustomer", dataProviderClass = DataProviders.class)
    @Description("Создание клиента")
    public void addCustomerTest(String firstName, String lastName, String postCode) {
        MainPage mainPage = new MainPage(localDriver.get());
        AddCustomerPage addCustomerPage = new AddCustomerPage(localDriver.get());

        mainPage.addCustomer();
        addCustomerPage.fillOutForm(firstName, lastName, postCode);
        addCustomerPage.submitForm();
        String alertMessage = AlertExecutor.getAlertMessage(localDriver.get());

        //В диалоговом окне сообщение об успешно созданном клиенте
        Assert.assertEquals(alertMessage.split("\\d")[0],
                "Customer added successfully with customer id :");
    }

    @Test
    @Description("Сортировка клиентов по имени по возрастанию")
    public void sortCustomersByFirstNameByAscTest() {
        MainPage mainPage = new MainPage(localDriver.get());
        CustomersListPage customersListPage = new CustomersListPage(localDriver.get());

        mainPage.customers();
        String[] expected = SortHelper.sortByAsc(customersListPage.getFirstNames());
        customersListPage.sortByFirstName();
        customersListPage.sortByFirstName();
        String[] actual = customersListPage.getFirstNames();

        //Записи отсортированы по возрастанию
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Сортировка клиентов по имени по убыванию")
    public void sortCustomersByFirstNameByDescTest() {
        MainPage mainPage = new MainPage(localDriver.get());
        CustomersListPage customersListPage = new CustomersListPage(localDriver.get());

        mainPage.customers();
        String[] expected = SortHelper.sortByDesc(customersListPage.getFirstNames());
        customersListPage.sortByFirstName();
        String[] actual = customersListPage.getFirstNames();

        //Записи отсортированы по убыванию
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "searchTerms", dataProviderClass = DataProviders.class)
    @Description("Поиск клиента")
    public void searchCustomer(String term) {
        MainPage mainPage = new MainPage(localDriver.get());
        CustomersListPage customersListPage = new CustomersListPage(localDriver.get());

        mainPage.customers();
        customersListPage.enterSearchTerm(term);
        int size = customersListPage.getFirstNames().length;

        //Поиск выдал как минимум одну запись в таблице
        Assert.assertTrue(size > 0);
    }

    @AfterMethod
    public void quit() {
        localDriver.get().quit();
        localDriver.remove();
    }
}
