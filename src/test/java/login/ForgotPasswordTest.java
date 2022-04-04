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
    public static void restoreForgottenPasswordTest() {
        /**
         * Select "I can't sign in"
         * Type a [Login credential] as email and submit.
         * Type the new password. This test suite will need changes to the new password.
         **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        PageFactory.initElements(driver, ForgotPasswordPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        ForgotPasswordPage.forgotPasswordLink.click();
        ForgotPasswordPage.forgotPasswordEmailMandatory.sendKeys(ForgotPasswordPage.LOGIN_CREDENTIAL);
        ForgotPasswordPage.submitButton.submit();
        ForgotPasswordPage.atlassianSettingsLink.click();
        ForgotPasswordPage.atlassianSubmitButton.click();
        /*** BLOCKER: Haven't accessed to a mail service. **/
        Assert.assertEquals(ForgotPasswordPage.atlassianForgotPasswordConfirmation.
                getText(), ForgotPasswordPage.LOGIN_CREDENTIAL);
    }
}
