package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartIcon       = By.className("shopping_cart_link");
    private final By cartItems      = By.className("cart_item");
    private final By addToCartBtns  = By.cssSelector("[data-test^='add-to-cart']");
    private final By checkoutBtn    = By.id("checkout");
    private final By removeButtons  = By.cssSelector("[data-test^='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCartIcon() {
        click(cartIcon);
    }

    public void addFirstItemToCart() {
        List<WebElement> buttons = driver.findElements(addToCartBtns);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }

    public void clickCheckout() {
        click(checkoutBtn);
    }

    public void removeFirstItem() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
}
