package login;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class LoginThroughTrelloTest extends BaseTest {

    //TC ID TRE003 Incorrect credentials

    @Test
    public static void loginWithinIncorrectCredentialsTest(){}

    //TC ID TRE004 Correct credentials

    @Test
    public static void loginWithinCorrectCredentialsTest() throws InterruptedException {
        CommonActions.loginIntoTrelloWithinDefaultCredentials();
        // Need to add Assert equals Main page.....
    }

    //TC ID TRE008 Login via SSO

    @Test
    public static void autologinViaSSOTest(){}


}
