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
    public static void registerNewUserThroughTrelloTest() throws InterruptedException {
        /**
         * Open https://trello.com/signup
         * Type the email from https://temp-mail.io/  then submit it.
         * On https://id.atlassian.com/signup type the email from https://temp-mail.io/  then submit it.
         **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, RegistrationPage.class);
        PageFactory.initElements(driver, TempMail.class);
        driver.get(TempMail.TEMP_MAIL_PAGE_URL);
        CommonActions.explicitWaitOfOneElementVisible(TempMail.randomTempEmail);
        String randomTempEmailValue = TempMail.randomTempEmail.getAttribute("title");
        CommonActions.openUrlInNewBrowserTab(RegistrationPage.TRELLO_REGISTER_PAGE_URL);
        RegistrationPage.emailFromRegisterPage.sendKeys(randomTempEmailValue);
        RegistrationPage.emailFromRegisterPage.submit();
        Thread.sleep(2000);
        RegistrationPage.emailFromRegisterPage.submit();
        /*** BLOCKER: Atlassian has a Google Captcha. **/
        Assert.assertEquals(RegistrationPage.reCaptchaIframe.getTagName(), "iframe");
    }

    //TC ID TRE002 Registration with an existing data
    @Test
    public static void registerWithExistingDataTest() {
        /**
         * Type [default email name] in email text field then click the "Sign up it's free" button.
         * In the "Display name" type random name then submit.
         **/
        driver.manage().deleteAllCookies();
        PageFactory.initElements(driver, RegistrationPage.class);
        SoftAssert softAssert = new SoftAssert();
        driver.get(RegistrationPage.TRELLO_WELCOME_PAGE_URL);
        RegistrationPage.emailFromWelcomePage.sendKeys(RegistrationPage.EMAIL_REGISTERED_EARLIER);
        RegistrationPage.signUpFromWelcomePageButton.click();
        String emailOnRegisterPage = RegistrationPage.emailFromRegisterPage.getText();
        /*** Expected result: Id-atlassian Email mandatory is filled by email from trello home **/
        softAssert.assertEquals(RegistrationPage.EMAIL_REGISTERED_EARLIER, emailOnRegisterPage);
        RegistrationPage.emailFromRegisterPage.submit();
        /*** BLOCKER: Atlassian has a Google Captcha. **/
        Assert.assertEquals(RegistrationPage.reCaptchaIframe.getTagName(), "iframe");
    }
}
