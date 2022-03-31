package pages.cards.power_ups;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class PowerUpsPage extends BasePage {
    WebDriver driver;
    public PowerUpsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//button[@data-test-id='board-header-plugin-button']")
    public static WebElement powerUpsButton;
    @FindBy(xpath = "//button[contains(@class,'_3Ik0JLsERwh6Ui')]")
    public static WebElement confirmRedirectToPowerUpsPage;
    @FindBy(xpath = "//a[contains(@class,'trello-option')]")
    public static WebElement madeByTrelloButton;
    @FindBy(xpath = "//span[@title='Jira']/../..//button[contains(@class,'nch-button--primary')]")
    public static WebElement addPowerUpJiraButton;
    @FindBy(xpath = "//span[@title='Jira']/../..//a[contains(@class,'directory-settings-button')]")
    public static WebElement powerUpJiraSettingButton;
    @FindBy(xpath = "//a[contains(@class,'js-close-directory')]/..")
    public static WebElement closePowerUpsPageButton;
    @FindBy(xpath = "//button[contains(@class,'twKazOW7wj1Ow')]")
    public static WebElement installedPowerUpOpeningButton;
    @FindBy(xpath = "//div[contains(@class,'pop-over')]//div[@class='no-back']")
    public static WebElement installedPowerUpInteractiveWindow;
}
