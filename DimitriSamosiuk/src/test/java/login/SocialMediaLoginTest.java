package login;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginViaApplePage;
import pages.login.LoginViaGooglePage;
import pages.login.LoginViaMicrosoftPage;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class SocialMediaLoginTest {

    //TC ID TRE005 Social media login. Google.
    @Test
    public static void socialMediaLoginGoogleTest(){
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/login
         * 2. Select "Sign in via Google". Type a [Login credential] and a [Password credential].
         **/
        PageFactory.initElements(driver, LoginViaGooglePage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaGooglePage.googleOauthButton.click();
        LoginViaGooglePage.googleLoginTextField.sendKeys(LoginViaGooglePage.LOGIN_CREDENTIAL);
        LoginViaGooglePage.googleLoginNextButton.click();
        /**
         * BLOCKER
         * Google secure message about the prohibition og using Web Driver for login into the Google account.
         **/
        Assert.assertTrue(LoginViaGooglePage.googleSeleniumBlockerMessage.isDisplayed());
    }

    //TC ID TRE007 Social media login. Microsoft.
    @Test
    public static void socialMediaLoginMicrosoftTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/login
         * 2. Select "Sign in via Microsoft". Type a [Login credential] and a [Password credential].
         * 3. Click "No" button for saving the session.
         **/
        PageFactory.initElements(driver, LoginViaMicrosoftPage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaMicrosoftPage.microsoftOauthButton.click();
        Thread.sleep(2000);
        LoginViaMicrosoftPage.microsoftLoginTextField.sendKeys(LoginViaMicrosoftPage.LOGIN_CREDENTIAL);
        LoginViaMicrosoftPage.microsoftLoginNextButton.click();
        Thread.sleep(2000);
        LoginViaMicrosoftPage.microsoftPasswordTextField.sendKeys(LoginViaMicrosoftPage.PASSWORD_CREDENTIAL);
        LoginViaMicrosoftPage.microsoftSignInButton.click();
        Thread.sleep(2000);
        LoginViaMicrosoftPage.buttonNoAboutSavingSession.click();
        Thread.sleep(5000);
        LoginViaTrelloPage.avatarName.click();
        /**
         * Expected result
         * 3. Access granted. The Boards page is opened. The Username is according to [Login credential]
         **/
        Assert.assertEquals(LoginViaTrelloPage.avatarEmail.getText(),LoginViaMicrosoftPage.LOGIN_CREDENTIAL);

    }

    //TC ID TRE007 Social media login. Apple.
    @Test
    public static void socialMediaLoginAppleTest(){
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/login
         * 2. Select "Sign in via Apple". Type a [Login credential] and a [Password credential].
         **/
        PageFactory.initElements(driver, LoginViaApplePage.class);
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaApplePage.appleOauthButton.click();
        LoginViaApplePage.appleIDLoginTextField.sendKeys(LoginViaApplePage.LOGIN_CREDENTIAL);
        LoginViaApplePage.appleIDLoginNextButton.click();
        LoginViaApplePage.appleIDPasswordTextField.sendKeys(LoginViaApplePage.PASSWORD_CREDENTIAL);
        LoginViaApplePage.appleIDLoginNextButton.click();
        /**
         * BLOCKER
         * Apple ID uses 2FA sending SMS.
         **/
        Assert.assertTrue(LoginViaApplePage.apple2FADidntGetCodeLink.isDisplayed());
    }
}
