package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pages.HomePage;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 2/11/2015.
 */
@RunWith(value = Parameterized.class)
public class AuthorizationTest extends BaseTestClass {

    private String login;
    private String password;
    private String name;

    @Parameters
    public static Collection testData(){
        return Arrays.asList(
                new Object[][]{
                        {"yb@t4web.com.ua", "111111", "Юра"},
                       // {"piurrmbjwpdm@mailforspam.com", "9405", "shkukoow"}
                }
        );
    }
    public AuthorizationTest(String login, String password, String name){
        this.login=login;
        this.password=password;
        this.name=name;
    }

    @Test
    public void authorizationTest(){
        System.out.println("Starting Authorization test");
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        assertTrue("Page not opened", homePage.isOpened());
        homePage.openLoginForm(); //click login
        homePage.fillLoginField(login); // enter login
        homePage.fillPasswordField(password); // enter password
        homePage.clickLoginButton(); /// go login
        assertEquals("Not same username", homePage.getAuthorizedUserName(), name);
        homePage.clickLogoutButton();
        assertTrue("Logout failed", homePage.checkNonAuthorizedUser());
        System.out.println("Authorization test successfully completed");





    }
}
