package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.util.Random;

/**
 * Created by User on 2/10/2015.
 */
public class HomePage extends BasePage {


    public static final By registration = By.className("registration");
    public static final By fullname = By.id("name");
    public static final By email = By.id("email");
    public static final By agreement = By.id("agreement_check");
    public static final By registrationButton = By.id("registration_button");
    public static final By successRegistrationWindow = By.id("registration_success");
    public static final By login = By.className("enter");
    public static final By loginName = By.id("login_name");
    public static final By password = By.id("password");
    public static final By loginButton = By.id("login_button");

    public static final By logoutButton = By.xpath("//header[@id='header']//a[@href='/logout']");
    public static final By profileUsernameButton = By.xpath("//header[@id='header']//a[@href='/profile/edit']");








    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openRegistrationForm(){ //открыть форму регистрации

        driver.findElement(registration).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullname));
    }
    public String randomStringGenerator(int length){ // генератор строки рандомних символів, і = довжина строки
        String chars = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for(int i=0; i<length; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();


    }

    public String fillFullNameRegistrationRandomField(){ // заповнити поле фул нейм рандомною строкою
        String generatedFullName = randomStringGenerator(8); // довжиною 8 символів
        driver.findElement(fullname).clear();
        driver.findElement(fullname).click();
        driver.findElement(fullname).sendKeys(generatedFullName);
        return generatedFullName;
        //wait.until(ExpectedConditions.textToBePresentInElementLocated(fullname, generatedFullName));
    }
    public String fillEmailRegistrationRandomField(){ // заповнити поле емейл рандомною строкою0
        String generatedEmail = randomStringGenerator(12); // довжиноюю 12 символів
        driver.findElement(email).clear();
        driver.findElement(email).click();
        driver.findElement(email).sendKeys(generatedEmail + "@" + Config.getConfig().getEmailDomain());
        //wait.until(ExpectedConditions.textToBePresentInElementLocated(email, generatedEmail));
        return generatedEmail;
    }
    public void agreeRegistrationTerms(){   // принимаю условия клик
        driver.findElement(agreement).click();
        wait.until(ExpectedConditions.elementToBeSelected(agreement));
    }
    public void clickRegistrationButton(){ // натиснути "зареєструватися"
        driver.findElement(registrationButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successRegistrationWindow));
    }
    public void openLoginForm(){ // відкрити форму логіна
        driver.findElement(login).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginName));
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
    public void clickLogoutButton(){ //клік Логаут
        driver.findElement(logoutButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(login));

    }
    public String getAuthorizedUserName(){ //взяти юзернейм зареєстрованого користувача
        return driver.findElement(profileUsernameButton).getText();
    }
    public boolean checkNonAuthorizedUser(){
        return driver.findElement(login).isDisplayed();
    }

}
