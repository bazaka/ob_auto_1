package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderPage;
import pages.ShopPage;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 2/24/2015.
 */
@RunWith(value = Parameterized.class)
public class AddToCartAuthorizedTest extends BaseTestClass {
    private String login;
    private String password;

    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(
                new Object[][]{
                        {"yb@t4web.com.ua", "111111"},
                        // {"piurrmbjwpdm@mailforspam.com", "9405"}
                }
        );
    }
    public AddToCartAuthorizedTest(String login, String password){
        this.login=login;
        this.password=password;
    }
    @Test
    public void addToCartAuthorizedTest(){
        System.out.println("Start Add to Cart Authorized Test");
        ShopPage shopPage = new ShopPage(driver, wait);
        shopPage.open();
        assertTrue("Page not opened", shopPage.isOpened());
        //авторизація
        shopPage.openLoginForm();
        shopPage.fillLoginField(login);
        shopPage.fillPasswordField(password);
        shopPage.clickLoginButton();
        //додавання товару в корзину
        shopPage.clickGoodsPage(); // click on Товары
        shopPage.clickOnFirstCategory();

        assertEquals("Text in button Buy is not correct", shopPage.getBuyProductButtonText(), "Купить");
        assertEquals("Cart not empty", shopPage.getCartCount(), "пусто"); // перевірка, що корзина пуста

        String productName = shopPage.getProductName();
        String productPrice = shopPage.getProductPrice().replaceAll(" ", ""); //прибрати пробіли зі строки

        shopPage.clickOnBuyButton();
        shopPage.refresh();
        assertEquals("Cart is empty", shopPage.getCartCount(), "1"); // перевірка, що в корзині 1 товар
        shopPage.clickOnCart();
        assertEquals("Different product name", shopPage.getCartProductName(), productName); // перевірка правильності імені в корзині і в каталозі

        // assertEquals("Different product price",shopPage.getCartProductPrice(), productPrice);
        assertEquals("Different total product price", shopPage.getCartProductTotalPrice(), productPrice); //перевірки цін в Каталозі, Корзині та Загальної

        assertEquals("Product value is not 1",shopPage.getCartProductValue(), "1"); // перевірка графи "Количество"
        //видалення товару з корзини
        shopPage.deleteProductFromCart();
        assertEquals("Cart not empty", shopPage.getEmptyCartText(), "корзина пуста");
        shopPage.closeCart();
        //логаут
        shopPage.clickLogoutButton();

        System.out.println("Add to Cart Authorized Test successfully completed");
    }
}
