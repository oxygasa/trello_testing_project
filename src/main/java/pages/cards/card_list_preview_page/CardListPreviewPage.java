package pages.cards.card_list_preview_page;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.Random;

public class CardListPreviewPage extends BasePage {
    WebDriver driver;
    public CardListPreviewPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-open-add-list')]")})
    public static List<WebElement> openListTitleEditorButton;
    @FindAll({@FindBy(xpath = "//input[@class='list-name-input']")})
    public static List<WebElement> listNameInput;
    @FindAll({@FindBy(xpath = "//input[contains(@class,'mod-list-add-button js-save-edit')]")})
    public static List<WebElement> saveListNameButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-open-card-composer')]")})
    public static List<WebElement> openCardTitleEditorButton;
    @FindAll({@FindBy(xpath = "//textarea[contains(@class,'list-card-composer-textarea')]")})
    public static List<WebElement> cardTitleInput;
    @FindAll({@FindBy(xpath = "//input[contains(@class,'js-add-card')]")})
    public static List<WebElement> saveCardNameButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'icon-close dark-hover')]")})
    public static List<WebElement> cancelEditingCardButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-cc-menu')]")})
    public static List<WebElement> cardMoreOptionsButton;
    @FindAll({@FindBy(xpath = "//a[@class='js-mem-selector']")})
    public static List<WebElement> membersLabelsPositionsOptions;
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-select-label')]")})
    public static List<WebElement> labelsColorOptions;
    @FindBy(xpath = "//a[contains(@class,'pop-over-header-close-btn')]")
    public static WebElement closeLabelPopupButton;

    public static int randomLabelColor(){
        Random random = new Random();
        return random.nextInt(5);
    }

    public static String createRandomCardOnTheList() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(CardListPreviewPage.openListTitleEditorButton.get(0));
        CardListPreviewPage.openListTitleEditorButton.get(0).click();
        String generateListTitleRandomName = RandomStringUtils.randomAlphanumeric(10);
        CardListPreviewPage.listNameInput.get(0).sendKeys(generateListTitleRandomName);
        CardListPreviewPage.saveListNameButton.get(0).click();
        CardListPreviewPage.openCardTitleEditorButton.get(0).click();
        String generateCardTitleRandomName = RandomStringUtils.randomAlphanumeric(10);
        CardListPreviewPage.cardTitleInput.get(0).sendKeys(generateCardTitleRandomName);
        CardListPreviewPage.saveCardNameButton.get(0).click();
        CardListPreviewPage.cardMoreOptionsButton.get(0).click();
        CardListPreviewPage.membersLabelsPositionsOptions.get(0).click();
        CardListPreviewPage.labelsColorOptions.get(0).click();
        CardListPreviewPage.labelsColorOptions.get(CardListPreviewPage.randomLabelColor()).click();
        CardListPreviewPage.closeLabelPopupButton.click();
        return generateCardTitleRandomName;
    }

}
