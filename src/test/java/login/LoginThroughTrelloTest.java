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
        /*** Open https://trello.com/login. Type [Incorrect login credential example one] as a login. stay the password clear. **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_ONE);
        LoginViaTrelloPage.submitButtonTrello.click();
        /*** Expected result: Error message module "not registered" is displayed. **/
        Assert.assertTrue(LoginViaTrelloPage.accountNotExistError.isEnabled());
    }

    //TC ID TRE003 Incorrect credentials

    @Test
    public static void loginWithinIncorrectCredentialsPartTwoTest() throws InterruptedException {
        /*** Type [Incorrect login credential example two] as a login. stay the password clear. **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
        LoginViaTrelloPage.submitButtonTrello.click();
        CommonActions.explicitWaitOfOneElementVisible(LoginViaTrelloPage.emailApprovalRequest);
        /*** Module "Approve email" is displayed within the typed email value.**/
        Assert.assertEquals(LoginViaTrelloPage.emailApprovalRequest.
                getText(), LoginViaTrelloPage.INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
    }

    //TC ID TRE003 Incorrect credentials
    @Test
    public static void loginWithinIncorrectCredentialsPartThreeTest() {
        /*** Type a [Login credential], stay the password clear. **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.clear();
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.LOGIN_CREDENTIAL);
        LoginViaTrelloPage.submitButtonTrello.click();
        /*** Expected result: Error message is displayed "Password is required" **/
        Assert.assertTrue(LoginViaTrelloPage.passwordIsNotTypedError.isEnabled());
    }

    //TC ID TRE004 Correct credentials

    @Test
    public static void loginWithinCorrectCredentialsTest() throws InterruptedException {
        /*** Type a [Login credential] and a [Password credential]. **/
        /*** Access granted. The Boards page is opened. The Username is according to [Login credential] **/
        LoginViaTrelloPage.avatarName.click();
        Assert.assertEquals(LoginViaTrelloPage.avatarEmail.getText(), LoginViaTrelloPage.LOGIN_CREDENTIAL);
    }
}
