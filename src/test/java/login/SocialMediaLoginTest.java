package login;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.testng.annotations.Test;
import pages.login.LoginViaApplePage;
import pages.login.LoginViaGooglePage;
import pages.login.LoginViaMicrosoftPage;

import static commons.CommonActions.driver;

public class SocialMediaLoginTest extends BaseTest {
    LoginViaGooglePage loginViaGooglePage = new LoginViaGooglePage(driver);
    LoginViaMicrosoftPage loginViaMicrosoftPage = new LoginViaMicrosoftPage(driver);
    LoginViaApplePage loginViaApplePage = new LoginViaApplePage(driver);

    @Test(description = "TC ID TRE005 Social media login. Google.", groups = {"critical_path"})
    public void socialMediaLoginGoogleTest() {
        /*** Select "Sign in via Google". Type a [Login credential] and a [Password credential]. **/
        loginViaGooglePage.tryToLoginViaGoogle();
    }

    @Test(description = "TC ID TRE007 Social media login. Microsoft.", groups = {"critical_path"})
    public void socialMediaLoginMicrosoftTest() throws InterruptedException {
        /**
         * Select "Sign in via Microsoft". Type a [Login credential] and a [Password credential].
         * Click "No" button for saving the session.
         **/
        loginViaMicrosoftPage.tryToLoginViaMicrosoft();
//If it fails, write a try/catch test for checking the MS Protection message. It happens after 50 oaths and proceed 3 days.
// After 3 days MS oauth is available again.
    }

    @Test(description = "TC ID TRE007 Social media login. Apple.",
            retryAnalyzer = FlakingTestOneChanceToPass.class, groups = {"critical_path"})
    public void socialMediaLoginAppleTest() throws InterruptedException {
        /*** Select "Sign in via Apple". Type a [Login credential] and a [Password credential]. **/
        loginViaApplePage.tryToLoginViaApple();
    }
}
