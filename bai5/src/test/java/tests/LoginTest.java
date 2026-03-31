package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Feature("Đăng nhập hệ thống")
public class LoginTest extends BaseTest {

    // ------------------------------------------------------------------ //
    // UC-001: Đăng nhập hợp lệ                                           //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-001: Đăng nhập bằng tài khoản hợp lệ")
    @Description("Kiểm thử đăng nhập với username/password hợp lệ → chuyển sang trang inventory")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Mở trang đăng nhập", () ->
            getDriver().get(BASE_URL));

        Allure.step("Nhập thông tin đăng nhập", () -> {
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
        });

        Allure.step("Click nút Đăng nhập", loginPage::clickLoginButton);

        Allure.step("Kiểm tra chuyển trang thành công", () ->
            Assert.assertTrue(
                getDriver().getCurrentUrl().contains("inventory"),
                "URL phải chứa 'inventory' sau khi đăng nhập thành công"
            ));
    }

    // ------------------------------------------------------------------ //
    // UC-002: Đăng nhập sai mật khẩu                                     //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-002: Đăng nhập với mật khẩu sai")
    @Description("Kiểm thử đăng nhập với mật khẩu sai → hiển thị thông báo lỗi")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Mở trang đăng nhập", () ->
            getDriver().get(BASE_URL));

        Allure.step("Nhập username hợp lệ và password sai", () -> {
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("wrong_password");
        });

        Allure.step("Click nút Đăng nhập", loginPage::clickLoginButton);

        Allure.step("Kiểm tra thông báo lỗi hiển thị", () ->
            Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Phải hiển thị thông báo lỗi khi mật khẩu sai"
            ));
    }

    // ------------------------------------------------------------------ //
    // UC-003: Đăng nhập tài khoản bị khóa                                //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-003: Đăng nhập tài khoản bị khóa")
    @Description("Kiểm thử đăng nhập với tài khoản locked_out_user → hiển thị lỗi bị khóa")
    @Severity(SeverityLevel.MINOR)
    public void testLoginLockedUser() {
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Mở trang đăng nhập", () ->
            getDriver().get(BASE_URL));

        Allure.step("Nhập tài khoản bị khóa", () -> {
            loginPage.enterUsername("locked_out_user");
            loginPage.enterPassword("secret_sauce");
        });

        Allure.step("Click nút Đăng nhập", loginPage::clickLoginButton);

        Allure.step("Kiểm tra thông báo lỗi tài khoản bị khóa", () -> {
            String error = loginPage.getErrorMessage();
            Assert.assertTrue(
                error.contains("locked out"),
                "Thông báo phải chứa 'locked out', thực tế: " + error
            );
        });
    }

    // ------------------------------------------------------------------ //
    // UC-004: Test cố tình FAIL để xem ảnh đính kèm trong Allure         //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-004: Kiểm tra chụp ảnh khi FAIL")
    @Description("Test case cố ý fail để kiểm tra tính năng đính kèm ảnh khi thất bại")
    @Severity(SeverityLevel.TRIVIAL)
    public void testLoginFail_ForScreenshot() {
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Mở trang đăng nhập", () ->
            getDriver().get(BASE_URL));

        Allure.step("Nhập thông tin đăng nhập sai cố ý", () -> {
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
        });

        Allure.step("Click nút Đăng nhập", loginPage::clickLoginButton);

        Allure.step("Assert sai cố ý – để trigger chụp ảnh trong BaseTest", () ->
            Assert.assertEquals(
                getDriver().getTitle(),
                "TITLE_SAI_CO_Y",
                "Test này cố tình FAIL để demo ảnh đính kèm trong Allure"
            ));
    }
}
