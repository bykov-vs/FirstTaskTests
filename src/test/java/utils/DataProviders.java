package utils;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class DataProviders {
    @DataProvider(name = "createCustomer")
    public static Object[][] createUserData(){
        return new Object[][]{{"test", "test", "test"}};
    }

    @DataProvider(name = "searchTerms")
    public static Object[][] createSearchTerms(){
        return new Object[][]{
                {"Harry"},
                {"Potter"},
                {"E725JB"},
                {"1004"}
        };
    }
}
