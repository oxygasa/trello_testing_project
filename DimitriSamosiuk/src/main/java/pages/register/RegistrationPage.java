package pages.register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class RegistrationPage extends BasePage {
    WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver=driver;
    }
    public static final String TRELLO_WELCOME_PAGE_URL = "https://trello.com";
    public static final String TRELLO_REGISTER_PAGE_URL = "https://trello.com/signup";
    public static final String CREATE_NEW_TEAM_PAGE_URL = "https://trello.com/create-first-team";
    public static final String EMAIL_REGISTERED_EARLIER = "trellou0@gmail.com";
    @FindBy(xpath = "//input[@name='email']")
    public static WebElement emailFromWelcomePage;
    @FindBy(xpath = "//button[@data-analytics-button='greenSignupHeroButton']")
    public static WebElement signUpFromWelcomePageButton;
    @FindBy(id = "email")
    public static WebElement emailFromRegisterPage;
    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    public static WebElement reCaptchaIframe;
}
