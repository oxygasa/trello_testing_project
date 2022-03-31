package pages.register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class TempMail extends BasePage {
    WebDriver driver;
    public TempMail(WebDriver driver) {
        this.driver = driver;
    }
    public static final String TEMP_MAIL_PAGE_URL = "https://temp-mail.io/";
    @FindBy(id = "email")
    public static WebElement randomTempEmail;
    @FindAll({@FindBy(xpath = "//li[contains(@class,'message list-complete')]")})
    public static List<WebElement> incomeBoxMailListButtons;
    @FindBy(xpath = "//a[contains(text(),'Workspace')]")
    public static WebElement joinWorkspaceList;
}
