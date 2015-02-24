package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/23/2015.
 */
public class OrderPage extends BasePage{

    public static final By goOrderPageFromCart = By.xpath("//div[@id='modal-cart']//a[@href='/checkout']/button");
    public static final By nameField = By.xpath("//div[@class='panel-body']//input[@name='name']");
    public static final By lastNameField = By.xpath("//div[@class='panel-body']//input[@name='lastName']");
    public static final By emailField = By.xpath("//div[@class='panel-body']//input[@name='email']");
    public static final By phoneField = By.xpath("//div[@class='panel-body']//input[@name='phone']");
    public static final By regionDropDown = By.xpath("//div[@class='panel-body']//select[@id='region']");
    public static final By addressField = By.xpath("//div[@class='panel-body']//textarea[@name='address']");
    public static final By payTypeDropDown = By.xpath("//div[@class='panel-body']//select[@name='typePay']");
    public static final By deliverTypeDropDown = By.xpath("//div[@class='panel-body']//select[@name='typeDeliver']");
    public static final By cityDropDown = By.xpath("//div[@class='panel-body']//select[@name='cityId']");

    public static final By endOrderButton = By.xpath("//div[@class='store__checkout']//button[contains(text(), 'Закончить оформление заказа')]");
    public static final By orderProductPrice = By.className("store__product__description__parameters__product__item__price-value");
    public static final By orderProductName = By.xpath("//h4/a[contains(@href, '/product/')]/span");


    public OrderPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }
    public void goToOrderPageFromCart(){
        driver.findElement(goOrderPageFromCart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
    public void fillField(By locator, String text){
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public void selectElementFromList(By locator, String text){
        Select dropDown = new Select(driver.findElement(locator));
        dropDown.selectByVisibleText(text);
    }
    public void fillNameField(String text){
       fillField(nameField, text);
    }
    public void fillLastNameField(String text){
        fillField(lastNameField, text);
    }
    public void fillEmailField(String text){
        fillField(emailField, text);
    }
    public void fillPhoneField(String text){
        fillField(phoneField, text);
    }
    public void fillAddressField(String text){
        fillField(addressField, text);
    }
    public void selectRegion(String text){
        selectElementFromList(regionDropDown, text);
    }
    public void selectPayType(String text){
        selectElementFromList(payTypeDropDown, text);
    }
    public void selectDeliverType(String text){
        selectElementFromList(deliverTypeDropDown, text);
    }
    public void selectCity(String text){
        selectElementFromList(cityDropDown, text);
    }

    public void endOrderButtonClick(){
        driver.findElement(endOrderButton).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(orderProductPrice));
    }
    public String getOrderProductPrice(){
        return(driver.findElement(orderProductPrice).getText().replace(" ", ""));
    }
    public String getOrderProductName(){
        return(driver.findElement(orderProductName).getText());
    }
   /* public String getCityDropDownDisableAttribute(){
        return(driver.findElement(cityDropDown).getAttribute("disabled"));
    }*/
    //зробити асерти на продукт нейм і ціну

}
