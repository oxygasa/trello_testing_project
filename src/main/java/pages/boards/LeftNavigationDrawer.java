package pages.boards;

import commons.CommonActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

import java.util.List;

public class LeftNavigationDrawer extends BasePage {
    @FindBy(xpath = "//a[@title='Table']")
    public static WebElement tableButton;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-expand-button']")
    public static WebElement expander;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public static WebElement addBoard;
    @FindAll({@FindBy(xpath = "//ul[@data-test-id='collapsible-list-items']//a")})
    public static List<WebElement> workspacesAndBoardsList;
    @FindAll({@FindBy(xpath = "//button[contains(@class,'1De3PKu4pMyBCW ')]")})
    public static List<WebElement> boardActionsMenu;
    @FindBy(xpath = "//button[contains(@aria-label,'Close board')]")
    public static WebElement closeBoardMenu;
    @FindAll({@FindBy(xpath = "//button[contains(@class,'_3HfJ71CiQ9nm2y _1Tu9wiuW4Te8Rx')]")})
    public static List<WebElement> confirmCloseBoardButton;
    @FindBy(xpath = "//button[@data-test-id='boards-list-show-more-button']")
    public static WebElement showMore;
    @FindAll({@FindBy(xpath = "//a[@class='_2GFX5xx4d2BRju']")})
    public static List<WebElement> navigationToBoardsTemplatesHomeWorkspaces;
    WebDriver driver;
    public LeftNavigationDrawer(WebDriver driver) {
        this.driver = driver;
    }

    public static void createBoardInstance() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(CommonActions.driver, BoardsPage.class);

        LeftNavigationDrawer.expander.click();
        LeftNavigationDrawer.addBoard.click();
        boardsPage.typeRandomBoardTitle().submitBoardSave();
    }

    public static void closeAllVisibleBoardsViaLeftNaviDrawer() {
        BoardsPage boardsPage = PageFactory.initElements(CommonActions.driver, BoardsPage.class);
        CommonActions.driver.get(boardsPage.getDefaultWorkspaceUrl());
        while (LeftNavigationDrawer.workspacesAndBoardsList.size() > 1) {
            CommonActions.driver.get(boardsPage.getDefaultWorkspaceUrl());
            Actions actions = new Actions(CommonActions.driver);
            try {
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.workspacesAndBoardsList.get(0));
                //2 modules (a workspace module, a board module) have 1 size-changeable List<WebElement>
                actions.moveToElement(LeftNavigationDrawer.workspacesAndBoardsList.get(2)).build().perform();
            } catch (IndexOutOfBoundsException | InterruptedException e) {
                try {
                    LeftNavigationDrawer.showMore.click();
                    CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.workspacesAndBoardsList.get(0));
                    actions.moveToElement(LeftNavigationDrawer.workspacesAndBoardsList.get(2)).build().perform();
                } catch (NoSuchElementException | InterruptedException f) {
                    break; // boards are successfully closed.
                }
            }
            try {
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.boardActionsMenu.get(0));
                actions.moveToElement(LeftNavigationDrawer.boardActionsMenu.get(0)).click().build().perform();
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.closeBoardMenu);
                LeftNavigationDrawer.closeBoardMenu.click();
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.confirmCloseBoardButton.get(1));
                LeftNavigationDrawer.confirmCloseBoardButton.get(1).click();
            } catch (NoSuchElementException | IndexOutOfBoundsException | InterruptedException f) {
                break; // boards are successfully closed.
            }
        }
    }
}
