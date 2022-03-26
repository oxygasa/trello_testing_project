package pages.boards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import java.util.List;

public class BoardsPage extends BasePage {
    WebDriver driver;
    public BoardsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//a[contains(@class,'js-show-sidebar')]")
    public static WebElement showRightSidebarButton;
    @FindBy(xpath = "//a[contains(@class,'js-open-more')]")
    public static WebElement rightSidebarMoreButton;
    @FindBy(xpath = "//a[contains(@class,'js-close-board')]")
    public static WebElement rightSidebarCloseBoardButton;
    @FindBy(xpath = "//input[@value='Close']")
    public static WebElement rightSidebarDialogBoxCloseBoardButton;
    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    public static WebElement newBoardNameInput;
    @FindBy(xpath = "//button[@data-test-id='create-board-submit-button']")
    public static WebElement newBoardSubmitButton;
    @FindAll({@FindBy(xpath = "//li[@data-test-id='create-board-tile']")})
    public static List<WebElement> createBoardFromBoardsPageButton;
    @FindBy(xpath = "//button[@data-test-id='start-free-trial-button']")
    public static WebElement startFreeTrialButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'board-tile')]")})
    public static List<WebElement> boardInstancesList;
    @FindBy(xpath = "//span[contains(@class,'icon-star')]")
    public static WebElement starIconInTheBoardPage;
    @FindBy(xpath = "//div[@class='board-tile mod-add']/p/span")
    public static WebElement createNewBoardSign;
    @FindAll({@FindBy(xpath = "//span[@class='board-tile-options']")})
    public static List<WebElement> boardsStarIcon;
    @FindBy(xpath = "//span[contains(@class,'starred')]")
    public static WebElement boardStarredIcon;
    @FindAll({@FindBy(xpath = "//span[@class='board-tile-options']/span")})
    public static List<WebElement> boardsStarredIcon;
    @FindAll({@FindBy(xpath ="//a[@class='board-tile']")})
    public static List<WebElement> boardTile;
    @FindAll({@FindBy(xpath ="//div[@class='board-tile-details-name']")})
    public static List<WebElement> boardTitles;
    @FindBy(xpath = "//div[contains(@class,'css-1og2rpm')]")
    public static WebElement sortByFilter;
    @FindBy(xpath = "//button[contains(@class,'_1Dp3s5P2VP237V')]")
    public static WebElement filterByCollectionDropdown;
    @FindBy(xpath = "//button[contains(@class,'_1tVNVS4PNoFo0k')]")
    public static WebElement filterByCollectionPremiumRequireButton;
    @FindBy(xpath = "//button[@data-test-id='popover-close']")
    public static WebElement filterByCollectionPremiumRequireClosePopupButton;
    @FindBy(xpath = "//input[@id='search']")
    public static WebElement boardsSearchBox;
    @FindAll({@FindBy(xpath ="//h3[contains(text(),'WORKSPACES')]//..//div[@class='board-tile-details-name']/div")})
    public static List<WebElement> boardsPageAllWorkspacesBoards;
    @FindAll({@FindBy(xpath ="//div[@class='board-tile-details-name']/div")})
    public static List<WebElement> boardsPageRecentBoardAndAllBoards;
    public static final String EXPECTED_PREMIUM_REQUIRE_TEXT = "Start free trial";
    public static final String TEN_BOARDS_TESTING_WORKSPACE = "https://trello.com/tenboardstestworkspace";
}
