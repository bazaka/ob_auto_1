package tests;

import org.junit.Test;
import pages.HomePage;
import pages.MailforspamPage;
import utils.Config;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by User on 2/10/2015.
 */
public class RegistrationTest extends BaseTestClass {

   /* public RegistrationTest(WebDriver driver){
        super(driver);
    }*/
    @Test
    public void registrationTest(){
        System.out.println("Starting Registration test");
        HomePage homePage = new HomePage(driver, wait);
        MailforspamPage mailforspamPage = new MailforspamPage(driver, wait);

        homePage.open(); // открыть сайт
        assertTrue("Page not opened", homePage.isOpened());

        homePage.openRegistrationForm();
        String username = homePage.fillFullNameRegistrationRandomField();
        String email= homePage.fillEmailRegistrationRandomField();//email name
        homePage.agreeRegistrationTerms();
        homePage.clickRegistrationButton(); //заповнили форму реєстрації


        homePage.open(Config.getConfig().getEmailDomain()); // заходимо на пошту
        mailforspamPage.fillEmailField(email);
        assertEquals("Incorrect title", homePage.getTitle(), "MailForSpam - Mailbox: "+email);

        mailforspamPage.checkLetter();
        mailforspamPage.clickOnLetter();
        String login = mailforspamPage.getLogin(mailforspamPage.parseLetter()); //парсинг листа і виділення звідти логіна
        String password = mailforspamPage.getPassword(mailforspamPage.parseLetter()); // аналогічно з паролем
        System.out.println("Mail: "+login+", "+ "Username: "+username+", "+ "Password: "+password);

        homePage.open(); //на сайт
        homePage.openLoginForm();
        homePage.fillLoginField(login); //логін
        homePage.fillPasswordField(password);//пароль
        homePage.clickLoginButton();

        assertEquals("Incorrect login", homePage.getAuthorizedUserName(), username  );

        homePage.clickLogoutButton(); //logout

        System.out.println("Registration test successfully completed");

    }



}
