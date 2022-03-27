package pages.login;

import commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginViaMicrosoftPage extends BasePage {
    WebDriver driver;
    public LoginViaMicrosoftPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String LOGIN_CREDENTIAL = "trellou0@outlook.com";
    public static final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    @FindBy(id = "msftButton")
    public static WebElement microsoftOauthButton;
    @FindBy(id = "i0116")
    public static WebElement microsoftLoginTextField;
    @FindBy(id = "idSIButton9")
    public static WebElement microsoftLoginNextButton;
    @FindBy(id = "i0118")
    public static WebElement microsoftPasswordTextField;
    @FindBy(id = "idSIButton9")
    public static WebElement microsoftSignInButton;
    @FindBy(id = "idBtn_Back")
    public static WebElement buttonNoAboutSavingSession;

    public static void loginViaMicrosoftLoginPage() throws InterruptedException {
        LoginViaMicrosoftPage.microsoftOauthButton.click();
        CommonActions.explicitWaitOfOneElementVisible(LoginViaMicrosoftPage.microsoftLoginTextField);
        LoginViaMicrosoftPage.microsoftLoginTextField.sendKeys(LoginViaMicrosoftPage.LOGIN_CREDENTIAL);
        LoginViaMicrosoftPage.microsoftLoginNextButton.click();
        CommonActions.explicitWaitOfOneElementVisible(LoginViaMicrosoftPage.microsoftPasswordTextField);
        LoginViaMicrosoftPage.microsoftPasswordTextField.sendKeys(LoginViaMicrosoftPage.PASSWORD_CREDENTIAL);
        LoginViaMicrosoftPage.microsoftSignInButton.click();
        CommonActions.explicitWaitOfOneElementVisible(LoginViaMicrosoftPage.buttonNoAboutSavingSession);
        LoginViaMicrosoftPage.buttonNoAboutSavingSession.click();
    }
}
