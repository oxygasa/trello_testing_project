package register;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.testng.annotations.Test;
import pages.register.RegistrationPage;

import static commons.CommonActions.driver;

public class RegisterUserTest extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage(driver);

    @Test(description = "TC ID TRE001 Register a new user through Trello",
            groups = {"smoke", "critical_path"})
    public void registerNewUserViaTrelloTest() throws InterruptedException {
        /**
         * Open https://trello.com/signup
         * Type the email from https://temp-mail.io/  then submit it.
         * On https://id.atlassian.com/signup type the email from https://temp-mail.io/  then submit it.
         **/
        registrationPage.tryToCreateNewUser();
    }

    @Test(description = "TC ID TRE002 Registration with an existing data",
            retryAnalyzer = FlakingTestOneChanceToPass.class)
    public void registerWithExistingDataTest() throws InterruptedException {
        /**
         * Type [default email name] in email text field then click the "Sign up it's free" button.
         * In the "Display name" type random name then submit.
         **/
        registrationPage.tryToRegisterWithExistingLoginCredentials();
    }
}
