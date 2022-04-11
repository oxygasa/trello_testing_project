package login;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginViaApplePage;
import pages.login.LoginViaGooglePage;
import pages.login.LoginViaMicrosoftPage;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class SocialMediaLoginTest extends BaseTest {

    //TC ID TRE005 Social media login. Google.
    @Test
    public void socialMediaLoginGoogleTest() {
        /*** Select "Sign in via Google". Type a [Login credential] and a [Password credential]. **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaGooglePage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaGooglePage.googleOauthButton.click();
        LoginViaGooglePage.googleLoginTextField.sendKeys(LoginViaGooglePage.LOGIN_CREDENTIAL);
        LoginViaGooglePage.googleLoginNextButton.click();
        /*** BLOCKER: Google secure message about the prohibition og using Web Driver for login into the Google account. **/
        Assert.assertTrue(LoginViaGooglePage.googleSeleniumBlockerMessage.isDisplayed());
    }

    //TC ID TRE007 Social media login. Microsoft.
    @Test
    public void socialMediaLoginMicrosoftTest() throws InterruptedException {
        /**
         * Select "Sign in via Microsoft". Type a [Login credential] and a [Password credential].
         * Click "No" button for saving the session.
         **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaMicrosoftPage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaMicrosoftPage.loginViaMicrosoftLoginPage();
        CommonActions.explicitWaitOfOneElementVisible(LoginViaTrelloPage.avatarName);
        LoginViaTrelloPage.avatarName.click();
        /*** Expected result: Access granted. The Boards page is opened. The Username is according to [Login credential]
         **/
        Assert.assertEquals(LoginViaTrelloPage.avatarEmail.getText(), LoginViaMicrosoftPage.LOGIN_CREDENTIAL);

    }

    //TC ID TRE007 Social media login. Apple.
    @Test
    public void socialMediaLoginAppleTest() {
        /*** Select "Sign in via Apple". Type a [Login credential] and a [Password credential]. **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaApplePage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaApplePage.appleOauthButton.click();
        LoginViaApplePage.appleIDLoginTextField.sendKeys(LoginViaApplePage.LOGIN_CREDENTIAL);
        LoginViaApplePage.appleIDLoginNextButton.click();
        LoginViaApplePage.appleIDPasswordTextField.sendKeys(LoginViaApplePage.PASSWORD_CREDENTIAL);
        LoginViaApplePage.appleIDLoginNextButton.click();
        /*** BLOCKER: Apple ID uses 2FA sending SMS. **/
        Assert.assertTrue(LoginViaApplePage.apple2FADidntGetCodeLink.isDisplayed());
    }
}
