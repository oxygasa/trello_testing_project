package pages.register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class TempMail extends BasePage {
    WebDriver driver;
    public TempMail(WebDriver driver) {
        this.driver=driver;
    }
    public static final String TEMP_MAIL_PAGE_URL = "https://temp-mail.io/";
    @FindBy(id = "email")
    public static WebElement randomTempEmail;
}
