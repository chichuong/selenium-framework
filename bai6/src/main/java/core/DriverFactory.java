package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createDriver(String browser) {
        String gridUrl = System.getProperty("grid.url");
        if (gridUrl != null && !gridUrl.isBlank()) {
            return createRemoteDriver(browser, gridUrl); // Chạy trên Grid
        }
        
        // GitHub Actions tự đặt biến CI=true
        boolean isCI = System.getenv("CI") != null;
        switch (browser.toLowerCase()) {
            case "firefox":
                return createFirefoxDriver(isCI);
            default:
                return createChromeDriver(isCI);
        }
    }
    private static WebDriver createRemoteDriver(String browser, String gridUrl) {
        org.openqa.selenium.remote.DesiredCapabilities caps = new org.openqa.selenium.remote.DesiredCapabilities();
        caps.setBrowserName(browser.toLowerCase());
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            caps.merge(options);
        }
        try {
            java.net.URL gridEndpoint = new java.net.URL(gridUrl + "/wd/hub");
            org.openqa.selenium.remote.RemoteWebDriver driver = new org.openqa.selenium.remote.RemoteWebDriver(gridEndpoint, caps);
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
            return driver;
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Grid URL không hợp lệ: " + gridUrl);
        }
    }

    private static WebDriver createChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new"); // Chrome 112+
            options.addArguments("--no-sandbox"); // Bắt buộc trên Linux CI
            options.addArguments("--disable-dev-shm-usage"); // Tránh lỗi OOM
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("-headless");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }
}
