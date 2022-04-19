package login;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.login.LoginViaApplePage;
import pages.login.LoginViaGooglePage;
import pages.login.LoginViaMicrosoftPage;

import static commons.CommonActions.driver;

public class SocialMediaLoginTest extends BaseTest {

    //TC ID TRE005 Social media login. Google.
    @Test (groups={"critical_path"})
    public void socialMediaLoginGoogleTest() {
        /*** Select "Sign in via Google". Type a [Login credential] and a [Password credential]. **/
        LoginViaGooglePage loginViaGooglePage = PageFactory.initElements(driver, LoginViaGooglePage.class);
        loginViaGooglePage.tryToLoginViaGoogle();
    }

    //TC ID TRE007 Social media login. Microsoft.
    @Test (groups={"critical_path"})
    public void socialMediaLoginMicrosoftTest() throws InterruptedException {
        /**
         * Select "Sign in via Microsoft". Type a [Login credential] and a [Password credential].
         * Click "No" button for saving the session.
         **/
        LoginViaMicrosoftPage loginViaMicrosoftPage = PageFactory.initElements(driver, LoginViaMicrosoftPage.class);
        loginViaMicrosoftPage.tryToLoginViaMicrosoft();
//If it fails, write a try/catch test for checking the MS Protection message. It happens after 50 oaths and proceed 3 days.
// After 3 days MS oauth is available again.
    }

    //TC ID TRE007 Social media login. Apple.
    @Test (retryAnalyzer = FlakingTestOneChanceToPass.class, groups={"critical_path"})
    public void socialMediaLoginAppleTest() throws InterruptedException {
        /*** Select "Sign in via Apple". Type a [Login credential] and a [Password credential]. **/
        LoginViaApplePage loginViaApplePage = PageFactory.initElements(driver, LoginViaApplePage.class);
        loginViaApplePage.tryToLoginViaApple();
    }
}
