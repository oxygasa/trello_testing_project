package login;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class LoginViaTrelloTest extends BaseTest {

    //TC ID TRE003 Incorrect credentials

    @Test
    public void loginWithNotRegisteredCredentials() throws InterruptedException {
        /*** Open https://trello.com/login. Type [Incorrect login credential example one] as a login. stay the password clear. **/
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        loginViaTrelloPage.loginWithNotRegisteredCredentials();

    }

    //TC ID TRE003 Incorrect credentials

    @Test
    public void loginWithinNotConfirmedEmail() throws InterruptedException {
        /*** Type [Incorrect login credential example two] as a login. stay the password clear. **/
        driver.manage().deleteAllCookies();
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        loginViaTrelloPage.loginWithNotConfirmedEmail();
    }

    //TC ID TRE003 Incorrect credentials
    @Test
    public void loginWithinEmptyPasswordField() {
        /*** Type a [Login credential], stay the password clear. **/
        driver.manage().deleteAllCookies();
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        loginViaTrelloPage.loginWithEmptyPassword();
    }

    //TC ID TRE004 Correct credentials

    @Test (retryAnalyzer = FlakingTestOneChanceToPass.class)
    public void loginWithinCorrectCredentialsTest() throws InterruptedException {
        /*** Type a [Login credential] and a [Password credential]. **/
        /*** Access granted. The Boards page is opened. The Username is according to [Login credential] **/
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        //login into system is a @BeforeTest method
        loginViaTrelloPage.checkTheCorrectlyLoggedUserExist();
    }
}
