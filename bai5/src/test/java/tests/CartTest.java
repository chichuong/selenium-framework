package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;

@Feature("Giỏ hàng")
public class CartTest extends BaseTest {

    /** Helper: đăng nhập trước khi thao tác giỏ hàng */
    private void doLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        getDriver().get(BASE_URL);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    // ------------------------------------------------------------------ //
    // UC-005: Thêm sản phẩm vào giỏ hàng                                 //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-005: Thêm sản phẩm vào giỏ hàng")
    @Description("Kiểm thử thêm sản phẩm đầu tiên vào giỏ hàng → số lượng tăng lên 1")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddToCart() {
        CartPage cartPage = new CartPage(getDriver());

        Allure.step("Đăng nhập vào hệ thống", this::doLogin);

        Allure.step("Thêm sản phẩm đầu tiên vào giỏ hàng", cartPage::addFirstItemToCart);

        Allure.step("Mở giỏ hàng", cartPage::clickCartIcon);

        Allure.step("Kiểm tra giỏ hàng có 1 sản phẩm", () ->
            Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Giỏ hàng phải có đúng 1 sản phẩm sau khi thêm"));
    }

    // ------------------------------------------------------------------ //
    // UC-006: Xóa sản phẩm khỏi giỏ hàng                                 //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-006: Xóa sản phẩm khỏi giỏ hàng")
    @Description("Kiểm thử xóa sản phẩm khỏi giỏ hàng → giỏ hàng trống")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveFromCart() {
        CartPage cartPage = new CartPage(getDriver());

        Allure.step("Đăng nhập và thêm sản phẩm vào giỏ", () -> {
            doLogin();
            cartPage.addFirstItemToCart();
            cartPage.clickCartIcon();
        });

        Allure.step("Xóa sản phẩm khỏi giỏ hàng", cartPage::removeFirstItem);

        Allure.step("Kiểm tra giỏ hàng trống", () ->
            Assert.assertTrue(cartPage.isCartEmpty(),
                "Giỏ hàng phải trống sau khi xóa sản phẩm"));
    }

    // ------------------------------------------------------------------ //
    // UC-007: Chuyển đến trang thanh toán                                 //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-007: Chuyển đến trang thanh toán")
    @Description("Kiểm thử click Checkout → chuyển sang trang nhập thông tin thanh toán")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckout() {
        CartPage cartPage = new CartPage(getDriver());

        Allure.step("Đăng nhập và thêm sản phẩm vào giỏ", () -> {
            doLogin();
            cartPage.addFirstItemToCart();
            cartPage.clickCartIcon();
        });

        Allure.step("Click nút Checkout", cartPage::clickCheckout);

        Allure.step("Kiểm tra chuyển sang trang checkout", () ->
            Assert.assertTrue(
                getDriver().getCurrentUrl().contains("checkout"),
                "URL phải chứa 'checkout' sau khi click Checkout"
            ));
    }

    // ------------------------------------------------------------------ //
    // UC-008: Giỏ hàng trống khi mới đăng nhập                           //
    // ------------------------------------------------------------------ //
    @Test
    @Story("UC-008: Kiểm tra giỏ hàng trống ban đầu")
    @Description("Kiểm thử giỏ hàng phải trống khi vừa đăng nhập chưa thêm gì")
    @Severity(SeverityLevel.MINOR)
    public void testCartEmptyOnLogin() {
        CartPage cartPage = new CartPage(getDriver());

        Allure.step("Đăng nhập vào hệ thống", this::doLogin);

        Allure.step("Mở giỏ hàng", cartPage::clickCartIcon);

        Allure.step("Kiểm tra giỏ hàng trống", () ->
            Assert.assertTrue(cartPage.isCartEmpty(),
                "Giỏ hàng phải trống khi mới đăng nhập"));
    }
}
