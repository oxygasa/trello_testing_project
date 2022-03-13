package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginViaGooglePage extends BasePage {
    WebDriver driver;
    public LoginViaGooglePage(WebDriver driver) {
        this.driver=driver;
    }
    public static final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public static final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    @FindBy (id = "googleButton")
    public static WebElement googleOauthButton;
    @FindBy (id = "identifierId")
    public static WebElement googleLoginTextField;
    @FindBy (id = "identifierNext")
    public static WebElement googleLoginNextButton;
    @FindBy (id = "headingText")
    public static WebElement googleSeleniumBlockerMessage;
}
