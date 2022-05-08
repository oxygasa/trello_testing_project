package login;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.testng.annotations.Test;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class LoginViaTrelloTest extends BaseTest {
    LoginViaTrelloPage loginViaTrelloPage = new LoginViaTrelloPage(driver);

    @Test(description = "TC ID TRE003 Incorrect credentials")
    public void loginWithNotRegisteredCredentials() throws InterruptedException {
        /*** Open https://trello.com/login. Type [Incorrect login credential example one] as a login. stay the password clear. **/
        loginViaTrelloPage.loginWithNotRegisteredCredentials();
    }

    @Test(description = "TC ID TRE003 Incorrect credentials")
    public void loginWithinNotConfirmedEmail() throws InterruptedException {
        /*** Type [Incorrect login credential example two] as a login. stay the password clear. **/
        driver.manage().deleteAllCookies();
        loginViaTrelloPage.loginWithNotConfirmedEmail();
    }

    @Test(description = "TC ID TRE003 Incorrect credentials", groups = {"smoke", "critical_path"})
    public void loginWithinEmptyPasswordField() {
        /*** Type a [Login credential], stay the password clear. **/
        driver.manage().deleteAllCookies();
        loginViaTrelloPage.loginWithEmptyPassword();
    }

    @Test(description = "TC ID TRE004 Correct credentials",
            retryAnalyzer = FlakingTestOneChanceToPass.class,
            groups = {"smoke", "critical_path"})
    public void loginWithinCorrectCredentialsTest() throws InterruptedException {
        /*** Type a [Login credential] and a [Password credential]. **/
        /*** Access granted. The Boards page is opened. The Username is according to [Login credential] **/
        //login into system is a @BeforeTest method
        loginViaTrelloPage.checkTheCorrectlyLoggedUserExist();
    }
}
