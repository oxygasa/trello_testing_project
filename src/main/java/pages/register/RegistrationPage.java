package pages.register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.base.BasePage;
import static commons.CommonActions.explicitWaitOfOneElementVisible;

public class RegistrationPage extends BasePage {
    WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public final String TRELLO_WELCOME_PAGE_URL = "https://trello.com";
    public final String TRELLO_REGISTER_PAGE_URL = "https://trello.com/signup";
    public final String EMAIL_REGISTERED_EARLIER = "trellou0@gmail.com";
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailFromWelcomePage;
    @FindBy(xpath = "//button[@data-analytics-button='greenSignupHeroButton']")
    public WebElement signUpFromWelcomePageButton;
    @FindBy(id = "email")
    public WebElement emailFromRegisterPage;
    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    public WebElement reCaptchaIframe;

    public RegistrationPage tryToCreateNewUser() throws InterruptedException {
        TempMail tempMail = PageFactory.initElements(driver, TempMail.class);
        tempMail.createTempEmailInstance();
        String randomTempEmailValue = tempMail.generatedTempEmail();
        driver.manage().deleteAllCookies();
        driver.get(TRELLO_REGISTER_PAGE_URL);
        emailFromRegisterPage.sendKeys(randomTempEmailValue);
        emailFromRegisterPage.submit();
        Thread.sleep(10000);
        explicitWaitOfOneElementVisible(emailFromRegisterPage);
        emailFromRegisterPage.submit();
        /*** BLOCKER: Atlassian has a Google Captcha. **/
        Assert.assertEquals(reCaptchaIframe.getTagName(), "iframe");
        return this;
    }

    public RegistrationPage tryToRegisterWithExistingLoginCredentials(){
        driver.manage().deleteAllCookies();
        SoftAssert softAssert = new SoftAssert();
        driver.get(TRELLO_WELCOME_PAGE_URL);
        emailFromWelcomePage.sendKeys(EMAIL_REGISTERED_EARLIER);
        signUpFromWelcomePageButton.click();
        String emailOnRegisterPage = emailFromRegisterPage.getText();
        /*** Expected result: Id-atlassian Email mandatory is filled by email from trello home **/
        softAssert.assertEquals(EMAIL_REGISTERED_EARLIER, emailOnRegisterPage);
        emailFromRegisterPage.submit();
        /*** BLOCKER: Atlassian has a Google Captcha. **/
        Assert.assertEquals(reCaptchaIframe.getTagName(), "iframe");
        return this;
    }
}
