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
    public static void registerNewUserThroughTrelloTest() {
        PageFactory.initElements(driver, RegistrationPage.class);
        SoftAssert softAssert = new SoftAssert();
        /**
         * Steps to Reproduce
         * 1. Open https://trello.com
         * 2. Type test@test.com in email text field then click the "Sign up it's free" button.
         **/
        driver.get(RegistrationPage.TRELLO_WELCOME_PAGE_URL);
        RegistrationPage.emailFromWelcomePage.sendKeys(RegistrationPage.EMAIL_REGISTERED_EARLIER);
        RegistrationPage.signUpFromWelcomePageButton.click();
        String emailOnRegisterPage = RegistrationPage.emailFromRegisterPage.getText();
        /**
         * Expected result
         * 2. Input "email" is filled by email from trello home (test@test.com)
         **/
        softAssert.assertEquals(RegistrationPage.EMAIL_REGISTERED_EARLIER, emailOnRegisterPage);
        /**
         * Steps to Reproduce
         * 3. Get back to the previous page, clear the text field then click the "Sign up it's free" button.
         * 4. Get back to the previous page, clear the text field then click the "Sign up" button on the header.
         * 5. On https://id.atlassian.com/signup stay the email form clear.
         **/
        driver.navigate().back();
        RegistrationPage.emailFromWelcomePage.clear();
        RegistrationPage.signUpFromWelcomePageButton.click();
        /**
         * Expected result
         * 5. The Continue button is inactive for clicking.
         **/
        Assert.assertFalse(RegistrationPage.continueButtonFromRegisterPage.isEnabled());
    }

        //TC ID TRE001 Register a new user through Trello
        @Test
        public static void registerNewUserThroughTrelloContinueTest() throws InterruptedException {
            PageFactory.initElements(driver, RegistrationPage.class);
            PageFactory.initElements(driver,TempMail.class);
            /**
             * Steps to Reproduce
             * 6. On Register page type the email from temp mail service then submit it.
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
             * Atlassian has a Google Captcha.
             * Trying to go to the next page within the saved cookies.
             **/
            driver.navigate().to(RegistrationPage.CREATE_NEW_TEAM_PAGE_URL);
    }

    //TC ID TRE002 Registration with an existing data
    @Test
    public static void registerWithExistingDataTest(){}
}
