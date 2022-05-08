package login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.login.ForgotPasswordPage;

import static commons.CommonActions.driver;

public class ForgotPasswordTest extends BaseTest {
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

    @Test(description = "TC ID TRE009 Forget password", groups = {"smoke", "critical_path"})
    public void restoreForgottenPasswordTest() {
        /**
         * Select "I can't sign in"
         * Type a [Login credential] as email and submit.
         * Type the new password. This test suite will need changes to the new password.
         **/
        forgotPasswordPage.askPasswordRestore();
    }
}
