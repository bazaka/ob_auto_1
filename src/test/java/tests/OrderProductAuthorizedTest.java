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
public class OrderProductAuthorizedTest extends BaseTestClass {
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String emailForOrder;
    private String phone;
    private String region;
    private String city;
    private String address;
    private String payType;
    private String deliveryType;

    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(
                new Object[][]{
                        {"yb@t4web.com.ua", "111111", "Петр", "Петров", "petrov.petr@mailforspam.com", "0993334422", "Сумская обл.", "Шостка", "ул. Грушевского", "Наличными", "Самовывоз"},
                        // {"piurrmbjwpdm@mailforspam.com", "9405"}
                }
        );
    }
    public OrderProductAuthorizedTest(String login, String password, String name, String lastName, String emailForOrder, String phone, String region, String city, String address, String payType, String deliveryType){
        this.login=login;
        this.password=password;
        this.name=name;
        this.lastName=lastName;
        this.emailForOrder=emailForOrder;
        this.phone=phone;
        this.region=region;
        this.city=city;
        this.address=address;
        this.payType=payType;
        this.deliveryType=deliveryType;
    }
    @Test
    public void orderProductAuthorizedTest(){
        System.out.println("Start Order Product Authorized Test");
        ShopPage shopPage = new ShopPage(driver, wait);
        OrderPage orderPage = new OrderPage(driver, wait);
        shopPage.open();
        assertTrue("Page not opened", shopPage.isOpened());

        //авторизація
        shopPage.openLoginForm();
        shopPage.fillLoginField(login);
        shopPage.fillPasswordField(password);
        shopPage.clickLoginButton();

        //Додавання товару в корзину і перевірка корзини
        shopPage.clickGoodsPage(); // click on Товары
        shopPage.clickOnFirstCategory();
        assertEquals("Text in button Buy is not correct", shopPage.getBuyProductButtonText(), "Купить");
        assertEquals("Cart not empty", shopPage.getCartCount(), "пусто"); // перевірка, що корзина пуста
        String productName = shopPage.getProductName();
        String productPrice = shopPage.getProductPrice().replaceAll(" ", ""); //прибрати пробіли зі строки
        shopPage.clickOnBuyButton();
        assertEquals("Cart is empty", shopPage.getCartCount(), "1"); // перевірка, що в корзині 1 товар
        shopPage.refresh();
        shopPage.clickOnCart();
        assertEquals("Different product name", shopPage.getCartProductName(), productName); // перевірка правильності імені в корзині і в каталозі
        assertEquals("Different total product price", shopPage.getCartProductTotalPrice(), productPrice); //перевірки цін в Каталозі, Корзині та Загальної
        assertEquals("Product value is not 1",shopPage.getCartProductValue(), "1"); // перевірка графи "Количество"

        //Перехід на форму оформлення замовлення:
        orderPage.goToOrderPageFromCart();
        //Заповняємо форму
        orderPage.fillNameField(name);
        orderPage.fillLastNameField(lastName);
        orderPage.fillEmailField(emailForOrder);
        orderPage.fillPhoneField(phone);
        orderPage.fillAddressField(address);
        orderPage.selectRegion(region);
        orderPage.selectPayType(payType);
        orderPage.selectDeliverType(deliveryType);
        orderPage.selectCity(city);

        orderPage.endOrderButtonClick();

        assertTrue(productName.contains(orderPage.getOrderProductName())); // перевірка, чи модель замовленого продукту така ж сама, як і в його загальній назві
        assertEquals(orderPage.getOrderProductPrice(), productPrice); // перевірка ціни
        //логаут
        shopPage.clickLogoutButton();

        System.out.println("Order Product Authorized Test successfully completed");
    }
}
