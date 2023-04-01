import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SmokeTests {
    static WebDriver driver;
    static MainPage mainPage;
    static AddCustomerPage addCustomerPage;
    static CustomersListPage customersListPage;


    @BeforeClass
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);
        addCustomerPage = new AddCustomerPage(driver);
        customersListPage = new CustomersListPage(driver);
    }

    @Before
    public void openStartPage() {
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
        addCustomerPage.fillOutForm(firstName, lastName, postCode);
        addCustomerPage.submitForm();
        String alertMessage = addCustomerPage.getAlertMessage();

        Assert.assertEquals("Customer added successfully with customer id :",
                alertMessage.split("\\d")[0]);
    }

    @Test
    public void sortCustomersByFirstNameTest() {
        mainPage.customers();

        List<String> firstNames = customersListPage.getFirstNames();
        customersListPage.sortByFirstName();
        customersListPage.sortByFirstName();
        List<String> sortedFirstNames = customersListPage.getFirstNames();

        firstNames = firstNames.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

        Assert.assertArrayEquals(firstNames.toArray(), sortedFirstNames.toArray());
    }

    @Test
    public void searchCustomerTest() {
        String firstName = "Harry";
        String lastName = "Potter";
        String postCode = "E725JB";
        String accountNumber = "1004";
        boolean result = false;

        mainPage.customers();

        customersListPage.enterSearchTerm(firstName);
        int size1 = customersListPage.getFirstNames().size();
        customersListPage.enterSearchTerm(lastName);
        int size2 = customersListPage.getFirstNames().size();
        customersListPage.enterSearchTerm(postCode);
        int size3 = customersListPage.getFirstNames().size();
        customersListPage.enterSearchTerm(accountNumber);
        int size4 = customersListPage.getFirstNames().size();

        if ((size1 > 0) && (size2 > 0) && (size3 > 0) && (size4 > 0)) {
            result = true;
        }

        Assert.assertTrue(result);
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
