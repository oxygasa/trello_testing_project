package login;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class LoginThroughTrelloTest extends BaseTest {

    //TC ID TRE003 Incorrect credentials

    @Test
    public static void loginWithinIncorrectCredentialsPartOneTest() {
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/login
         * 2. Type [Incorrect login credential example one] as a login. stay the password clear.
         **/
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_ONE);
        LoginViaTrelloPage.submitButtonTrello.click();
        /**
         * Expected result
         * 2. Error message module "not registered" is displayed.
         **/
        Assert.assertTrue(LoginViaTrelloPage.accountNotExistError.isEnabled());
    }

    //TC ID TRE003 Incorrect credentials

    @Test
    public static void loginWithinIncorrectCredentialsPartTwoTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 3. Type [Incorrect login credential example two] as a login. stay the password clear.
         **/
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
        LoginViaTrelloPage.submitButtonTrello.click();
        Thread.sleep(5000);
        /**
         * Expected result
         * 3. Module "Approve email" is displayed within the typed email value.
         **/
        Assert.assertEquals(LoginViaTrelloPage.emailApprovalRequest.
                getText(), LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
    }

    //TC ID TRE003 Incorrect credentials

    @Test
    public static void loginWithinIncorrectCredentialsPartThreeTest() {
        /**
         * Steps to Reproduce
         * 4. Type a [Login credential], stay the password clear.
         **/
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.clear();
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.LOGIN_CREDENTIAL);
        LoginViaTrelloPage.submitButtonTrello.click();
        /**
         * Expected result
         * 4. Error message is displayed "Password is required"
         **/
        Assert.assertTrue(LoginViaTrelloPage.passwordIsNotTypedError.isEnabled());
    }

    //TC ID TRE004 Correct credentials

    @Test
    public static void loginWithinCorrectCredentialsTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/login
         * 2. Type a [Login credential] and a [Password credential].
         **/
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        /**
         * Expected result
         * 2. Access granted. The Boards page is opened. The Username is according to [Login credential]
         **/
        LoginViaTrelloPage.avatarName.click();
        Assert.assertEquals(LoginViaTrelloPage.avatarEmail.getText(),LoginViaTrelloPage.LOGIN_CREDENTIAL);
    }
}
