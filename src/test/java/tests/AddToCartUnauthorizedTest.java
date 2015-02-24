package tests;


import org.junit.Test;
import pages.ShopPage;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 2/18/2015.
 */
public class AddToCartUnauthorizedTest extends BaseTestClass {
    //String url;
    @Test
    public void addToCartUnauthorizedTest(){
        System.out.println("Start Add To Cart Unauthorized Test");
        ShopPage shopPage = new ShopPage(driver, wait);
        shopPage.open(/*url*/);
        assertTrue("Page not opened", shopPage.isOpened(/*url*/));

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
        shopPage.deleteProductFromCart();
        assertEquals("Cart not empty", shopPage.getEmptyCartText(), "корзина пуста");




        System.out.println("Add To Cart Unauthorized Test successfully completed");




    }
}
