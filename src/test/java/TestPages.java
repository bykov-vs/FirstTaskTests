import org.openqa.selenium.WebDriver;
import pages.AddCustomerPage;
import pages.CustomersListPage;
import pages.MainPage;

public class TestPages {
    MainPage mainPage;
    AddCustomerPage addCustomerPage;
    CustomersListPage customersListPage;

    public TestPages(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.addCustomerPage = new AddCustomerPage(driver);
        this.customersListPage = new CustomersListPage(driver);
    }
}
