import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;
import utils.ChromeDriverCreator;
import utils.DataProviders;
import utils.SortHelper;

@Epic("Smoke Tests")
public class SmokeTests {
    static ThreadLocal<WebDriver> localDriver;

    @BeforeClass
    public void setup() {
        localDriver = ThreadLocal.withInitial(ChromeDriverCreator::create);
    }

    @Test(dataProvider = "createCustomer", dataProviderClass = DataProviders.class)
    @Description("Создание клиента")
    public void addCustomerTest(String firstName, String lastName, String postCode) {
        MainPage mainPage = new MainPage(localDriver.get());
        AddCustomerPage addCustomerPage = new AddCustomerPage(localDriver.get());

        mainPage.addCustomer();
        addCustomerPage.fillOutForm(firstName, lastName, postCode);
        addCustomerPage.submitForm();
        String alertMessage = addCustomerPage.getAlertMessage();

        Assert.assertEquals("Customer added successfully with customer id :",
                alertMessage.split("\\d")[0]);
    }

    @Test
    @Description("Сортировка клиентов по имени по возрастанию")
    public void sortCustomersByFirstNameByAscTest() {
        MainPage mainPage = new MainPage(localDriver.get());
        CustomersListPage customersListPage = new CustomersListPage(localDriver.get());

        mainPage.customers();
        String[] actual = SortHelper.sortByAsc(customersListPage.getFirstNames());
        customersListPage.sortByFirstName();
        customersListPage.sortByFirstName();
        String[] expected = customersListPage.getFirstNames();

        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Сортировка клиентов по имени по убыванию")
    public void sortCustomersByFirstNameByDescTest() {
        MainPage mainPage = new MainPage(localDriver.get());
        CustomersListPage customersListPage = new CustomersListPage(localDriver.get());

        mainPage.customers();
        String[] actual = SortHelper.sortByDesc(customersListPage.getFirstNames());
        customersListPage.sortByFirstName();
        String[] expected = customersListPage.getFirstNames();

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

        Assert.assertTrue(size > 0);
    }

    @AfterClass
    public void quit() {
        localDriver.get().quit();
        localDriver.remove();
    }
}
