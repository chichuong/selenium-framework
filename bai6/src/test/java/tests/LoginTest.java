package tests;

import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver.set(DriverFactory.createDriver(browser));
    }

    @Test
    public void testLogin1() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Login1 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testLogin2() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Login2 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testLogin3() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Login3 on " + driver.get().getClass().getSimpleName());
    }
    @Test
    public void testLogin4() { 
        driver.get().get("https://example.com"); 
        System.out.println("Running Login4 on " + driver.get().getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) { 
            driver.get().quit(); 
            driver.remove(); 
        }
    }
}
