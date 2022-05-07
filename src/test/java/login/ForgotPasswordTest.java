package login;

import base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.ForgotPasswordPage;
import pages.login.LoginViaTrelloPage;
import static commons.CommonActions.driver;

public class ForgotPasswordTest extends BaseTest {
    ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver, ForgotPasswordPage.class);
    //TC ID TRE009 Forget password
    @Test (groups={"smoke", "critical_path"})
    public void restoreForgottenPasswordTest() {
        /**
         * Select "I can't sign in"
         * Type a [Login credential] as email and submit.
         * Type the new password. This test suite will need changes to the new password.
         **/
        forgotPasswordPage.askPasswordRestore();
    }
}
