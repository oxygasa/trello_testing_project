package pages.cards.card_list;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;
import java.util.Random;

import static commons.CommonActions.driver;

public class CardListPage extends BasePage {
    WebDriver driver;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-open-add-list')]/..")})
    private List<WebElement> addListButton;
    @FindAll({@FindBy(xpath = "//input[@class='list-name-input']")})
    private List<WebElement> listNameInput;
    @FindAll({@FindBy(xpath = "//input[contains(@class,'mod-list-add-button js-save-edit')]")})
    private List<WebElement> saveListNameButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-open-card-composer')]/..")})
    private List<WebElement> addCardTitleButton;
    @FindAll({@FindBy(xpath = "//textarea[contains(@class,'list-card-composer-textarea')]")})
    private List<WebElement> cardTextArea;
    @FindAll({@FindBy(xpath = "//input[contains(@class,'js-add-card')]")})
    private List<WebElement> saveCardTitleButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-cc-menu')]")})
    private List<WebElement> cardMoreOptionsButton;
    @FindAll({@FindBy(xpath = "//a[@class='js-mem-selector']/..")})
    private List<WebElement> membersSelector;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'js-select-member')]/..")})
    private List<WebElement> memberList;
    @FindBy(xpath = "//a[contains(@class,'pop-over-header-close-btn')]")
    private WebElement closePopupButton;
    @FindAll({@FindBy(xpath = "//a[@class='js-label-selector']/..")})
    private List<WebElement> labelsSelector;
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-select-label')]")})
    private List<WebElement> colorList;
    @FindBy(xpath = "//span[@data-color='green']")
    private WebElement greenLabel;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    private List<WebElement> activeCardList;
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-open-quick-card-editor')]")})
    private List<WebElement> activeCardPropertyButton;
    @FindBy(xpath = "//div[@class='list-header-extras']")
    WebElement listPropertyButton;
    @FindBy(xpath = "//div[@id='convert-card-role-button-react-root']")
    private WebElement convertCardToRegularButton;
    @FindBy(xpath = "//div/a[contains(@class,'js-edit-cover')]")
    private WebElement changeCardCoverButton;
    @FindBy(xpath = "//div/a[contains(@class,'js-archive')]")
    private WebElement archiveSingleCardButton;
    @FindBy(xpath = "//div/a[contains(@class,'js-edit-labels')]")
    private WebElement changeLabelButton;
    @FindAll({@FindBy(xpath = "//ul[contains(@class,'js-labels-list')]/li")})
    private List<WebElement> labelList;
    @FindAll({@FindBy(xpath = "//ul[contains(@class,'js-labels-list')]/li/a")})
    private List<WebElement> editLabelList;
    @FindAll({@FindBy(xpath = "//div[@class='YkrFKRFIBVAiVq']/button")})
    private List<WebElement> coverList;
    @FindBy(xpath = "//input[contains(@class,'js-save-edits')]")
    private WebElement submitCover;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    private WebElement submitLabel;
    @FindBy(xpath = "//input[@id='labelName']")
    private WebElement labelNameInput;
    @FindAll({@FindBy(xpath = "//div[@class='u-clearfix']/span")})
    private List<WebElement> editLabelColorList;

    @FindAll({@FindBy(xpath = "//ul[contains(@class,'js-labels-list')]/li/span")})
    private List<WebElement> observeLabelColorList;
    @FindBy(xpath = "//ul[@class='pop-over-list']//a[@class='js-archive-cards']/..")
    private WebElement archiveAllCardsButton;
    @FindBy(xpath = "//ul[@class='pop-over-list']//a[@class='js-close-list']/..")
    private WebElement archiveListButton;
    @FindBy(xpath = "//input[contains(@class,'js-confirm')]")
    private WebElement archiveAllConfirmButton;

    public List<WebElement> getColorList() {
        return colorList;
    }

    public CardListPage(WebDriver driver) {
        this.driver = driver;
    }

    public int randomLabelColor() {
        Random random = new Random();
        return random.nextInt(5);
    }

    public CardListPage createFewCards() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(addListButton.get(0));
        addListButton.get(0).click();
        String addListTitle = RandomStringUtils.randomAlphanumeric(10);
        listNameInput.get(0).sendKeys(addListTitle);
        saveListNameButton.get(0).click();
        addCardTitleButton.get(0).click();
        String cardTitleInput = RandomStringUtils.randomAlphanumeric(10);
        cardTextArea.get(0).sendKeys(cardTitleInput);
        saveCardTitleButton.get(0).click();
        cardMoreOptionsButton.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(membersSelector.get(0));
        membersSelector.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(memberList.get(0));
        memberList.get(0).click();
        closePopupButton.click();
        return this;
    }

    public String rememberCardTitle() {
        return activeCardList.get(0).getText();
    }


    public CardListPage addCardCover() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCardPropertyButton.get(0));
        activeCardPropertyButton.get(activeCardPropertyButton.size() - 1).click();
        try {
            convertCardToRegularButton.click();
            Thread.sleep(2000);
            changeCardCoverButton.click();
        } catch (org.openqa.selenium.ElementNotInteractableException e) {
            changeCardCoverButton.click();
        }
        Thread.sleep(2000);
        Random random = new Random();
        coverList.get(random.nextInt(5)).click();
        submitCover.click();
        return this;
    }

    public CardListPage addLabelWithText() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCardPropertyButton.get(0));
        activeCardPropertyButton.get(activeCardPropertyButton.size() - 1).click();
        try {
            convertCardToRegularButton.click();
            CommonActions.explicitWaitOfOneElementVisible(changeLabelButton);
            changeLabelButton.click();
        } catch (org.openqa.selenium.ElementNotInteractableException e) {
            CommonActions.explicitWaitOfOneElementVisible(changeLabelButton);
            changeLabelButton.click();
        }
        editLabelList.get(1).click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(labelNameInput);
        String labelNameValue = RandomStringUtils.randomAlphanumeric(10);
        labelNameInput.sendKeys(labelNameValue);
        Thread.sleep(2000);
        Random random = new Random();
        editLabelColorList.get(random.nextInt(9)).click();
        submitLabel.click();
        Thread.sleep(4000);
        CommonActions.explicitWaitOfOneElementVisible(observeLabelColorList.get(0));

        int i;
        for (i = 0; i < 5; i++) {
            if (observeLabelColorList.get(i).getText().contains(labelNameValue)) {
                break;
            }
        }
        if (i >= 5) {
            Assert.fail();
        }

        return this;
    }

    public CardListPage addSingleCardToArchive() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCardPropertyButton.get(0));
        activeCardPropertyButton.get(activeCardPropertyButton.size() - 1).click();
        try {
            convertCardToRegularButton.click();
            Thread.sleep(2000);
            archiveSingleCardButton.click();
        } catch (org.openqa.selenium.ElementNotInteractableException e) {
            archiveSingleCardButton.click();
        }
        return this;
    }

    public int countCardsInBeginning() {
        return activeCardList.size();
    }

    public int countCardsInEnd() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCardList.get(0));
        return activeCardList.size();
    }

    public CardListPage addAllCardsToArchive() {
        listPropertyButton.click();
        archiveAllCardsButton.click();
        archiveAllConfirmButton.click();
        return this;
    }

    public CardListPage addListOfCardsToArchive() {
        listPropertyButton.click();
        archiveListButton.click();
        return this;
    }
}
