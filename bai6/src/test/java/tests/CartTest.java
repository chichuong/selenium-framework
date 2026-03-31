package tests;

import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver.set(DriverFactory.createDriver(browser));
    }

    @Test
    public void testCart1() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Cart1 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testCart2() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Cart2 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testCart3() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Cart3 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testCart4() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Cart4 on " + driver.get().getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) { 
            driver.get().quit(); 
            driver.remove(); 
        }
    }
}
