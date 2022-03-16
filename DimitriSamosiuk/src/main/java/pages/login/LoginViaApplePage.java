package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginViaApplePage extends BasePage {
    WebDriver driver;
    public LoginViaApplePage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public static final String PASSWORD_CREDENTIAL = "sfjkg@Gygfuyg%%^$12521";
    @FindBy(id = "signInWithAppleButton")
    public static WebElement appleOauthButton;
    @FindBy(id = "account_name_text_field")
    public static WebElement appleIDLoginTextField;
    @FindBy(id = "sign-in")
    public static WebElement appleIDLoginNextButton;
    @FindBy(id = "password_text_field")
    public static WebElement appleIDPasswordTextField;
    @FindBy(id = "didnt-get-code")
    public static WebElement apple2FADidntGetCodeLink;
}
