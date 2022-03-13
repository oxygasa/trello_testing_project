package register;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.register.RegistrationPage;
import pages.register.TempMail;
import static commons.CommonActions.driver;

public class RegisterUserTest extends BaseTest {
    //TC ID TRE001 Register a new user through Trello
    @Test
    public static void registerNewUserThroughTrelloContinueTest() throws InterruptedException {
        PageFactory.initElements(driver, RegistrationPage.class);
        PageFactory.initElements(driver, TempMail.class);
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com/signup type the email from https://temp-mail.io/  then submit it.
         * 2. On https://id.atlassian.com/signup type the email from https://temp-mail.io/  then submit it.
         **/
        driver.get(TempMail.TEMP_MAIL_PAGE_URL);
        Thread.sleep(2000);
        String randomTempEmailValue = TempMail.randomTempEmail.getAttribute("title");
        CommonActions.openUrlInNewBrowserTab(RegistrationPage.TRELLO_REGISTER_PAGE_URL);
        RegistrationPage.emailFromRegisterPage.sendKeys(randomTempEmailValue);
        RegistrationPage.emailFromRegisterPage.submit();
        Thread.sleep(2000);
        RegistrationPage.emailFromRegisterPage.submit();
        /**
         * BLOCKER
         * Atlassian has a Google Captcha.
         **/
        Assert.assertEquals(RegistrationPage.reCaptchaIframe.getTagName(), "iframe");

    }

    //TC ID TRE002 Registration with an existing data
    @Test
    public static void registerWithExistingDataTest() {
        PageFactory.initElements(driver, RegistrationPage.class);
        SoftAssert softAssert = new SoftAssert();
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com
         * 2. Type [default email name] in email text field then click the "Sign up it's free" button.
         * 3. In the "Display name" type random name then submit.
         **/
        driver.get(RegistrationPage.TRELLO_WELCOME_PAGE_URL);
        RegistrationPage.emailFromWelcomePage.sendKeys(RegistrationPage.EMAIL_REGISTERED_EARLIER);
        RegistrationPage.signUpFromWelcomePageButton.click();
        String emailOnRegisterPage = RegistrationPage.emailFromRegisterPage.getText();

        /**
         * Expected result
         * 2. Id-atlassian Email mandatory is filled by email from trello home
         **/
        softAssert.assertEquals(RegistrationPage.EMAIL_REGISTERED_EARLIER, emailOnRegisterPage);
        RegistrationPage.emailFromRegisterPage.submit();
        /**
         * BLOCKER
         * Atlassian has a Google Captcha.
         **/
        Assert.assertEquals(RegistrationPage.reCaptchaIframe.getTagName(), "iframe");
    }
}
