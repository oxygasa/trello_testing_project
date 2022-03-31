package pages.cards.header;

import commons.CommonActions;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

import static commons.CommonActions.driver;

public class CardsHeader extends BasePage {
    WebDriver driver;
    public CardsHeader(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//button[@data-test-id='board-views-switcher-button']")
    public static WebElement boardViewSwitcherButton;
    @FindBy(xpath = "//button[@data-test-id='board-views-upsell-prompt-start-free-trial-button']")
    public static WebElement boardViewSwitcherFreeTrialButton;
    @FindBy(xpath = "//div[contains(@class,'js-rename-board')]")
    public static WebElement boardRenameInput;
    @FindBy(xpath = "//h1[contains(@class,'board-header-btn-text')]")
    public static WebElement boardTitleFor2ndUser;
    @FindBy(xpath = "//a[contains(@class,'js-open-org-menu')]")
    public static WebElement openWorkspaceMenu;
    @FindBy(xpath = "//a[contains(@class,'js-change-org')]")
    public static WebElement changeWorkspaceMenu;
    @FindBy(xpath = "//select[@class='js-org']")
    public static WebElement changeWorkspaceDropdownMenu;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    public static WebElement changeWorkspaceSubmitButton;
    @FindBy(xpath = "//a[@class='js-view-org']")
    public static WebElement viewWorkspaceMenu;
    @FindBy(id = "permission-level")
    public static WebElement workspaceVisibleButton;
    @FindBy(xpath = "//a[@id='permission-level']//span[@class='board-header-btn-text']")
    public static WebElement workspaceVisibleButtonText;
    @FindBy(xpath = "//a[@name='public']")
    public static WebElement publicBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='private']")
    public static WebElement privateBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='org']")
    public static WebElement workspaceBoardPermissionSelect;
    @FindBy(xpath = "//button[contains(@class,'js-submit')]")
    public static WebElement publicBoardPermissionConfirmButton;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-button']")
    public static WebElement avatarIcon;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-logout']")
    public static WebElement logoutButton;
    @FindBy(xpath = "//a[contains(@class,'board-header-btn-invite')]")
    public static WebElement inviteButton;
    @FindBy(xpath = "//a[contains(@class,'js-show-invitation-link')]")
    public static WebElement inviteLinkCreator;
    @FindBy(xpath = "//input[contains(@class,'js-invitation-link')]")
    public static WebElement invitationLinkForCopyField;
    @FindBy(xpath = "//a[contains(@class,'pop-over-header-close-btn')]")
    public static WebElement invitationWindowCloseButton;
    @FindBy(xpath = "//input[@data-test-id='header-search-input']")
    public static WebElement searchBox;
    @FindBy(xpath = "//button[@data-test-id='filter-popover-button']")
    public static WebElement filterPopoverButton;
    @FindBy(xpath = "//input[contains(@class,'nch-textfield__input')]")
    public static WebElement filerKeywordInput;
    @FindBy(xpath = "//div[contains(@title,'assigned')]")
    public static WebElement cardsAssignedToMeCheckBox;
    @FindAll({@FindBy(xpath = "//div[@class='D7o35mpYYtXnpz']")})
    public static List<WebElement> allFilterCheckboxes;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'css-ufz0vj-control')]")})
    public static List<WebElement> allFilterDropdowns;
    public static void inviteAndConnect2ndUserToTheBoardViaLink() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.inviteButton);
        CardsHeader.inviteButton.click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.inviteLinkCreator);
        CardsHeader.inviteLinkCreator.click();
        Thread.sleep(2500);
        Actions actions = new Actions(CommonActions.driver);
        actions.moveToElement(CardsHeader.invitationLinkForCopyField).doubleClick().build().perform();
        CardsHeader.invitationLinkForCopyField.sendKeys(Keys.chord(Keys.CONTROL,"c"));
        CardsHeader.invitationWindowCloseButton.click();
        CardsHeader.avatarIcon.click();
        CardsHeader.logoutButton.click();
        CommonActions.driver.manage().deleteAllCookies();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        actions.moveToElement(CardsHeader.searchBox).sendKeys(Keys.chord(Keys.CONTROL,"v"));
        String inviteLink = CardsHeader.searchBox.getText();
        CommonActions.driver.get(inviteLink);
    }
}
