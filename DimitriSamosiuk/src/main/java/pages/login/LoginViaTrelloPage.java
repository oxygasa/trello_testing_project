package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginViaTrelloPage extends BasePage {
    WebDriver driver;
    public LoginViaTrelloPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String TRELLO_LOGIN_PAGE = "https://trello.com/login";
    public static final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public static final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    public static final String INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_ONE = "3123412@af.com";
    public static final String INCORRECT_LOGIN_CREDENTIAL_EXAMPLE_TWO = "wename9713@shackvine.com";
    @FindBy(id = "user")
    public static WebElement username;
    @FindBy(id = "password")
    public static WebElement password;
    @FindBy(id = "login")
    public static WebElement submitButtonTrello;
    @FindBy(id = "login-submit")
    public static WebElement submitButtonAtlassian;
    @FindBy(className = "error-message")
    public static WebElement accountNotExistError;
    @FindBy(id = "password-error")
    public static WebElement passwordIsNotTypedError;
    @FindBy(xpath = "//p[contains(@class,'sc-kjoXOD')]")
    public static WebElement emailApprovalRequest;
    @FindBy(xpath = "//button[@aria-label='Open member menu']")
    public static WebElement avatarName;
    @FindBy(className = "w6CkIi_9-1xviK")
    public static WebElement avatarEmail;
}
