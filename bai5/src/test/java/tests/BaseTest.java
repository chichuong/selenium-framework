package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    protected static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        String env = System.getProperty("env", "dev");
        System.setProperty("env", env);

        ConfigReader config = ConfigReader.getInstance();

        int explicitWait = Integer.parseInt(config.getProperty("explicit.wait"));
        System.out.println("explicit wait = " + explicitWait);

        String browser = config.getProperty("browser");
        if (browser == null) browser = "chrome";

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--start-maximized");
                driver = new ChromeDriver(opts);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driverThreadLocal.set(driver);
    }

    /**
     * 5.3 – Đính kèm ảnh chụp khi test FAIL vào Allure report
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = getDriver();
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                attachScreenshot(driver);
            }
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    @Attachment(value = "Ảnh chụp khi thất bại", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
