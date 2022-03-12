package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginViaTrelloPage extends BasePage {
    WebDriver driver;
    public LoginViaTrelloPage(WebDriver driver) {
        this.driver=driver;
    }
    public static final String TRELLO_LOGIN_PAGE = "https://trello.com/login";
    public static final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public static final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    @FindBy (id = "user")
    public static WebElement username;
    @FindBy (id = "password")
    public static WebElement password;
    @FindBy (id = "login")
    public static WebElement submitButtonTrello;
    @FindBy (id = "login-submit")
    public static WebElement submitButtonAtlassian;
}
