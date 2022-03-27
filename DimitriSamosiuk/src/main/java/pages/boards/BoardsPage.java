package pages.boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static commons.CommonActions.driver;

public class BoardsPage extends BasePage {
    public static final String EXPECTED_PREMIUM_REQUIRE_TEXT = "Start free trial";
    public static final String TEN_BOARDS_TESTING_WORKSPACE = "https://trello.com/tenboardstestworkspace";
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
    @FindAll({@FindBy(xpath = "//a[@class='board-tile']")})
    public static List<WebElement> boardTile;
    @FindAll({@FindBy(xpath = "//div[@class='board-tile-details-name']")})
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
    @FindBy(xpath = "//button[contains(@class,'_3Ik0JLsERwh6Ui')]")
    public static WebElement premiumCreateCollection;
    @FindAll({@FindBy(xpath = "//h3[contains(text(),'WORKSPACES')]//..//div[@class='board-tile-details-name']/div")})
    public static List<WebElement> boardsPageAllWorkspacesBoards;
    @FindAll({@FindBy(xpath = "//div[@class='board-tile-details-name']/div")})
    public static List<WebElement> boardsPageRecentBoardAndAllBoards;
    WebDriver driver;
    public BoardsPage(WebDriver driver) {
        this.driver = driver;
    }

    /*** There are methods to make test steps code shorter **/

    public static List<String> createCollectionOfFourExpectedBoards() throws InterruptedException {
        List<String> expectedBoardNamesListener = new ArrayList<>();
        while (BoardsPage.boardInstancesList.size() < 4) {
            CommonActions.driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createBoardFromBoardsPageButton.get(0));
            BoardsPage.createBoardFromBoardsPageButton.get(0).click();
            String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
            BoardsPage.newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardSubmitButton);
            Thread.sleep(3000); //Thread.sleep is necessary, because this inactive button is clickable.
            //and don't create a board.
            BoardsPage.newBoardSubmitButton.click();
            expectedBoardNamesListener.add(newBoardNameTextFieldInputText);
        }
        return expectedBoardNamesListener;
    }

    public static List<String> chooseMostRecentlyFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter, 4);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        List<String> actualResultBoardNamesMost = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesMost.add(BoardsPage.boardTitles.get(i).getText());
        }
        return actualResultBoardNamesMost;
    }

    public static List<String> chooseLeastRecentlyFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter, 1);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        List<String> actualResultBoardNamesLeast = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesLeast.add(BoardsPage.boardTitles.get(i).getText());
        }
        return actualResultBoardNamesLeast;
    }

    public static List<String> chooseAZFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter, 2);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        List<String> actualResultBoardNamesAZ = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesAZ.add(BoardsPage.boardTitles.get(i).getText());
        }
        return actualResultBoardNamesAZ;
    }

    public static List<String> chooseZAFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter, 3);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        List<String> actualResultBoardNamesZA = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesZA.add(BoardsPage.boardTitles.get(i).getText());
        }
        return actualResultBoardNamesZA;
    }

    public static List<String> saveBoardPageRecentBoardListToCollection() throws InterruptedException {
        CommonActions.driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE + "/boards");
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardsPageAllWorkspacesBoards);
        List<String> actualResultBoardNames = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardsPageAllWorkspacesBoards.size(); i++) {
            actualResultBoardNames.add(BoardsPage.boardsPageAllWorkspacesBoards.get(i).getText());
        }
        return actualResultBoardNames;
    }

    public static List<String> saveBoardPageWorkspaceBoardListToCollection() throws InterruptedException {
        CommonActions.driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE + "/boards");
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardsPageRecentBoardAndAllBoards);
        List<String> actualResultBoardNames = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardsPageRecentBoardAndAllBoards.size(); i++) {
            actualResultBoardNames.add(BoardsPage.boardsPageRecentBoardAndAllBoards.get(i).getText());
        }
        return actualResultBoardNames;
    }
}
