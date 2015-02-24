package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/9/2015.
 */
public class BaseTestClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /*public BaseTest(WebDriver driver){
        this.driver=driver;
    }*/


    @Before
    public void preCondition(){

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,15);
        driver.manage().window().maximize();
    }
    @After
    public void postCondition(){
        if(driver!=null)
            driver.quit();
    }
}
