package utils;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class DataProviders {
    @DataProvider(name = "createCustomer")
    public static Object[][] createCustomerData(){
        return new Object[][]{{"test", "test", "test"}};
    }

    @DataProvider(name = "createCustomerDuplicate")
    public static Object[][] createCustomerDuplicateData(){
        return new Object[][]{{"Harry", "Potter", "E725JB"}};
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

    @DataProvider(name = "wrongSearchTerms")
    public static Object[][] createWrongSearchTerms(){
        return new Object[][]{
                {"Harry Potter"},
                {"1004 1005 1006"}
        };
    }
}
