package pages.register;

import commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

import static commons.CommonActions.driver;

public class TempMail extends BasePage {
    WebDriver driver;
    public TempMail(WebDriver driver) {
        this.driver = driver;
    }
    public final String TEMP_MAIL_PAGE_URL = "https://temp-mail.io/";
    @FindBy(id = "email")
    private WebElement randomTempEmail;
    @FindAll({@FindBy(xpath = "//li[contains(@class,'message list-complete')]")})
    private List<WebElement> incomeBoxMailListButtons;
    @FindBy(xpath = "//a[contains(text(),'Workspace')]")
    private WebElement joinWorkspaceList;

    public String createTempEmailInstance() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            driver.get(TEMP_MAIL_PAGE_URL);
        }
        CommonActions.explicitWaitOfOneElementVisible(randomTempEmail);
        return randomTempEmail.getAttribute("title");
    }

   public String generatedTempEmail() throws InterruptedException {
        return createTempEmailInstance();
   }

   public TempMail sendMail(String sendToMail, String message) throws InterruptedException {
       for (int i = 0; i < 3; i++) {
           driver.get(TEMP_MAIL_PAGE_URL);
           CommonActions.explicitWaitOfOneElementVisible(randomTempEmail);

       }
        return this;
   }
}
