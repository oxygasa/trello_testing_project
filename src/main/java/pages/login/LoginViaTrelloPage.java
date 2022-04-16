package pages.login;

import commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.base.BasePage;

import static commons.CommonActions.driver;

public class LoginViaTrelloPage extends BasePage {
    @FindBy(className = "error-message")
    public WebElement accountNotExistError;
    @FindBy(id = "password-error")
    public WebElement passwordIsNotTypedError;
    @FindBy(xpath = "//div[@id='email-sent-page']/p")
    public WebElement emailApprovalRequest;
    @FindBy(className = "w6CkIi_9-1xviK")
    public WebElement avatarEmail;
    public final String TRELLO_LOGIN_PAGE = "https://trello.com/login";
    public final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    public final String INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_ONE = "3123412@af.com";
    public final String INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO = "wename9713@shackvine.com";
    public final String SECOND_USER_LOGIN_CREDENTIAL = "trello2nduser@gmail.com";
    public final String SECOND_USER_PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    @FindBy(id = "user")
    public WebElement username;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(id = "login")
    public WebElement submitButtonTrello;
    @FindBy(id = "login-submit")
    public WebElement submitButtonAtlassian;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-button']")
    public WebElement avatarName;
    WebDriver driver;
    public LoginViaTrelloPage(WebDriver driver) {
        this.driver = driver;
    }

    /**Steps**/

    public LoginViaTrelloPage checkTheCorrectlyLoggedUserExist() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(avatarName);
        avatarName.click();
        Assert.assertEquals(avatarEmail.getText(), LOGIN_CREDENTIAL);
        return this;
    }

    public LoginViaTrelloPage loginWithNotRegisteredCredentials() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get(TRELLO_LOGIN_PAGE);
        driver.get(TRELLO_LOGIN_PAGE);
        username.sendKeys(INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_ONE);
        Thread.sleep(2000);
        submitButtonTrello.click();
        password.sendKeys(PASSWORD_CREDENTIAL);
        try {
            submitButtonAtlassian.click();
            Thread.sleep(3000);
            Assert.assertTrue(accountNotExistError.isEnabled());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.assertTrue(accountNotExistError.isEnabled());
        }
        return this;
    }

    public LoginViaTrelloPage loginWithNotConfirmedEmail() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get(TRELLO_LOGIN_PAGE);
        username.sendKeys(INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
        submitButtonTrello.click();
        CommonActions.explicitWaitOfOneElementVisible(emailApprovalRequest);
        /*** Module "Approve email" is displayed within the typed email value.**/
        Assert.assertEquals(emailApprovalRequest.
                getText(), INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO);
        return this;
    }

    public LoginViaTrelloPage loginWithEmptyPassword(){
        /*** Type a [Login credential], stay the password clear. **/
        driver.manage().deleteAllCookies();
        driver.get(TRELLO_LOGIN_PAGE);
        username.clear();
        username.sendKeys(LOGIN_CREDENTIAL);
        submitButtonTrello.click();
        /*** Expected result: Error message is displayed "Password is required" **/
        Assert.assertTrue(passwordIsNotTypedError.isEnabled());
        return this;
    }
}
