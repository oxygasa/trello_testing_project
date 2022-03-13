package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ForgotPasswordPage extends BasePage {
    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    @FindBy(xpath = "//ul//a[@href='/forgot']")
    public static WebElement forgotPasswordLink;
    @FindBy(id = "email")
    public static WebElement forgotPasswordEmailMandatory;
    @FindBy(id = "submit")
    public static WebElement submitButton;
    @FindBy(id = "aa-forgot-link")
    public static WebElement atlassianSettingsLink;
    @FindBy(xpath = "//div/p[@class]")
    public static WebElement atlassianForgotPasswordConfirmation;
    @FindBy(className = "css-19r5em7")
    public static WebElement atlassianSubmitButton;
}