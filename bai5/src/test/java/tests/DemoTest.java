package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Cấu hình môi trường")
public class DemoTest extends BaseTest {

    @Test
    @Description("Kiểm tra URL môi trường dev trỏ đúng vào saucedemo")
    @Severity(SeverityLevel.MINOR)
    public void testEnvironmentConfig() {
        getDriver().get(BASE_URL);
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Running test on URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("saucedemo"), "URL should contain saucedemo");
    }
}

