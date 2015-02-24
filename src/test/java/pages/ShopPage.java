package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.rmi.server.ExportException;

/**
 * Created by User on 2/18/2015.
 */
public class ShopPage extends BasePage {
    public static final By goods = By.xpath("//div[@class='store__category__naviagation__outer']//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span");
    public static final By goodsDropdown = By.xpath("//div[@class='store__category__naviagation__outer']//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li/a/span");
    public static final By firstCategoryOfGoods = By.xpath("//div[@class='store__category__category-list__group']//li[1]/a");
    public static final By buyProduct = By.xpath("//div[@class='store__product__description__parameters__product__item__buy']/a");
    public static final By productName = By.xpath("//a[@class='product-url']/span");
    public static final By productPrice = By.xpath("//div[@class='store__product__description__parameters__product__item__price']/span");
    public static final By cart = By.id("cart");
    public static final By cartClick = By.className("ajax-link");
    public static final By cartCount = By.className("count");
    public static final By cartProductName = By.xpath("//h2[@class='store__checkout__product-title']/a");
    public static final By cartProductTotalPrice = By.xpath("//tr[starts-with(@id, 'cart-product')]/td[4]/span");
    public static final By cartProductValue = By.xpath("//tr[starts-with(@id, 'cart-product')]/td[3]/input");
    public static final By deleteProductFromCart = By.xpath("//tr[starts-with(@id, 'cart-product')]/td[5]/a/i");
    public static final By emptyCart = By.xpath("//div[@id='modal-cart']//div[@class='modal-body']/p");

    public static final By loginName = By.id("login_name");
    public static final By password = By.id("password");
    public static final By loginButton = By.id("login_button");
    public static final By logoutButton = By.xpath("//a[@href='/logout']");
    public static final By loginForm = By.xpath("//li[@id='store-login']/a");
    public static final By closeCart = By.xpath("//div[@id='modal-cart']//button[@class='close']");




    public ShopPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        url = "http://electronic.ob.t4web.com.ua/";
    }
    public void openLoginForm(){ // відкрити форму логіна
        driver.findElement(loginForm).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginName));
    }
    public void clickLogoutButton(){ //клік Логаут
        driver.findElement(logoutButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginForm));

    }

    public void fillLoginField(String name){ //заповнити поле логін
        driver.findElement(loginName).clear();
        driver.findElement(loginName).click();
        driver.findElement(loginName).sendKeys(name);
    }
    public void fillPasswordField(String pass){ //заповнити пароль
        driver.findElement(password).clear();
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(pass);
    }
    public void clickLoginButton(){ //клік Войти
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton)); // assert username
    }
    public void clickGoodsPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(goods));
        Actions actions = new Actions(driver);
        WebElement goodsMenu = driver.findElement(goods);
        WebElement goodsDropdownMenu = driver.findElement(goodsDropdown);
       //WebElement goodsDropdownElement = driver.findElement(goods).findElement(goodsDropdown);

        actions.moveToElement(goodsMenu).click().perform(); //підвести мишку і зробити клік

        //driver.findElement(goods).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(goodsDropdown)); //ждьомс
        actions.moveToElement(goodsDropdownMenu).click().perform();

    }
    public void clickOnFirstCategory(){ //запускаємо двічі, клацає на першу категорію в магазині
        wait.until(ExpectedConditions.presenceOfElementLocated(firstCategoryOfGoods));
        driver.findElement(firstCategoryOfGoods).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(buyProduct)); // ???
    }
    public String getBuyProductButtonText(){ // text of "Buy" button
        return(driver.findElement(buyProduct).getText());
    }
    public String getProductName(){
        return(driver.findElement(productName)).getText();
    }
    public String getProductPrice(){
        return(driver.findElement(productPrice).getText());
    }
    public String getCartCount(){
        return(driver.findElement(cart).findElement(cartCount).getText());
    }
    public void clickOnBuyButton(){
        driver.findElement(buyProduct).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(buyProduct, "В корзине"));
    }
    public void clickOnCart(){
        driver.findElement(cart).findElement(cartClick).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductName));
    }
    public String getCartProductName(){
        return(driver.findElement(cartProductName).getText());
    }
   /* public String getCartProductPrice(){
        return(driver.findElement(cartProductPrice).getText());
    }*//**/
    public String getCartProductTotalPrice(){
        String price = driver.findElement(cartProductTotalPrice).getText();
        return(price.replaceAll(" ", "")); // прибрати пробєли
    }
    public String getCartProductValue(){
        return(driver.findElement(cartProductValue).getAttribute("value"));
    }

    public void deleteProductFromCart(){

        driver.findElement(deleteProductFromCart).click();

    }
    public String getEmptyCartText(){
        return(driver.findElement(emptyCart).getText());
    }

    public void closeCart(){
        driver.findElement(closeCart).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeCart));
    }

}
