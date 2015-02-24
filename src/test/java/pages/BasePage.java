package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

/**
 * Created by User on 2/9/2015.
 */
public class BasePage { //abstract class

    protected String url= Config.getConfig().getLink();
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver, WebDriverWait wait) {this.driver=driver; this.wait=wait; }
    public void open() {driver.get(url);}
    public void open(String link){ driver.get(link);}
    public boolean isOpened() {return driver.getCurrentUrl().equals(url);} //return true/false
    public boolean isOpened(String currentURL) {return driver.getCurrentUrl().equals(currentURL);} // перевірка поточного урл і заданого на вході
    public String getTitle() {return driver.getTitle();}
    public boolean verifyTitle(String currentTitle) { return driver.getTitle().equals(currentTitle);}
    public void refresh(){driver.navigate().refresh();}
}
