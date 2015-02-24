package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/10/2015.
 */
public class MailforspamPage extends BasePage {

    public static final By emailLogin = By.id("input_box");
    public static final By goToInbox = By.className("buttonGo");
    public static final By mailbox = By.id("mailbox");
    public static final By registrationLetter = By.xpath("//table[@id='mailbox']//tr[3]//a[contains(text(),'Officebook.com!')]");
    public static final By openedMessage = By.className("letter");



    WebDriverWait wait = new WebDriverWait(driver,15);

    public MailforspamPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void fillEmailField(String currentEmail){ //зайти в пошту
        driver.findElement(emailLogin).clear();
        driver.findElement(emailLogin).click();
        driver.findElement(emailLogin).sendKeys(currentEmail);
        driver.findElement(goToInbox).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(mailbox)); //assert title

    }
    public void checkLetter(){//чекаємо листа

        int count = 1;
        while(driver.findElements(registrationLetter).isEmpty()){
            driver.navigate().refresh();
            count++;
            if (count==20){
                System.out.println("Письмо не пришло");
                break;
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(mailbox)); // чекаємо загрузки сторінки
        }
    }
    public void clickOnLetter(){
        driver.findElement(registrationLetter).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(openedMessage));
    }
    public String[] parseLetter(){//парсинг текса і витягування з нього логіна


        String allText = driver.findElement(openedMessage).getText();

        String arrayText[] = allText.split("\\n"); // ріжем текст по ентерам
        //String partWithLoginPassword
        String[] arrayTextPart = arrayText[8].split(" "); // ріжем строку по пробєлам

        return arrayTextPart;

       /* for(int i=0; i<arrayTextPart.length; i++)
            System.out.println(arrayTextPart[i]);
        String login = arrayTextPart[7];            //дістали логін
        String password = arrayTextPart[10];            //дістали пароль
        System.out.println("login: "+ login);
        System.out.println("pass: " +password);*/

    }
    public String getLogin(String[] arrayTextPart){ //при парсинге, логин = arrayTextPart[7]
        return arrayTextPart[7];
    }
    public String getPassword(String[] arrayTextPart){
        return arrayTextPart[10];
    }
}

