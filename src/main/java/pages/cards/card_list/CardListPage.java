package pages.cards.card_list;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.Random;

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
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-open-quick-card-editor')]")})
    private List<WebElement> activeCardPropertyButton;
    @FindBy(xpath = "//div[@id='convert-card-role-button-react-root']")
    private WebElement convertCardToRegularButton;
    @FindBy(xpath = "//div/a[contains(@class,'js-edit-cover')]")
    private WebElement changeCardCoverButton;
    @FindAll({@FindBy(xpath = "//div[@class='YkrFKRFIBVAiVq']/button")})
    private List<WebElement> coverList;
    @FindBy(xpath = "//input[contains(@class,'js-save-edits')]")
    private WebElement submitCover;

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

    public CardListPage createCard() throws InterruptedException {
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

    public CardListPage addCardCover() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCardPropertyButton.get(0));
        activeCardPropertyButton.get(activeCardPropertyButton.size() - 1).click();
        try {
            convertCardToRegularButton.click();
            Thread.sleep(2000);
            changeCardCoverButton.click();
        }

        catch(org.openqa.selenium.ElementNotInteractableException e)
        {
            changeCardCoverButton.click();
        }
        Thread.sleep(2000);
        Random random = new Random();
        coverList.get(random.nextInt(5)).click();
        submitCover.click();
        return this;
    }
}
