package login;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.ForgotPasswordPage;
import pages.login.LoginViaTrelloPage;

import static commons.CommonActions.driver;

public class ForgotPasswordTest {

    //TC ID TRE009 Forget password
    @Test
    public static void restoreForgottenPasswordTest(){
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/
         * 2. Click the Login button.
         * 3. Select "I can't sign in"
         * 4. Type a [Login credential] as email and submit.
         * 5. Type the new password. This test suite will need changes to the new password.
         **/
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        PageFactory.initElements(driver, ForgotPasswordPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        ForgotPasswordPage.forgotPasswordLink.click();
        ForgotPasswordPage.forgotPasswordEmailMandatory.sendKeys(ForgotPasswordPage.LOGIN_CREDENTIAL);
        ForgotPasswordPage.submitButton.submit();
        ForgotPasswordPage.atlassianSettingsLink.click();
        ForgotPasswordPage.atlassianSubmitButton.click();
        /**
         * BLOCKER
         * No access to a mailservice.
         **/
        Assert.assertEquals(ForgotPasswordPage.atlassianForgotPasswordConfirmation.
                getText(),ForgotPasswordPage.LOGIN_CREDENTIAL);
    }
}
