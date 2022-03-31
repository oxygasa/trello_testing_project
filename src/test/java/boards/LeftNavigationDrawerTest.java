package boards;

import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;

import static commons.CommonActions.driver;

public class LeftNavigationDrawerTest {

    //TC ID TRE019 Left Navigation Drawer: Add, Favourite, close boards.
    @Test
    public static void addAndCloseBoardsTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        PageFactory.initElements(driver, LeftNavigationDrawer.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** On the any Workspaces Left Navigation Drawer create a board with a random title. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        LeftNavigationDrawer.createBoardInstance();
        /*** Close the boards from Left Navigation Drawer. **/
        LeftNavigationDrawer.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }


    //TC ID TRE022 Left Navigation Drawer: Duplicating boards' functionality.
    @Test
    public static void duplicatingBoardsFunctionalityTest() throws InterruptedException {
        /*** Precondition: login, navigate to boards page. **/
        PageFactory.initElements(driver, LeftNavigationDrawer.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE+ "/boards");
        /*** Open recent pages and check their presence. **/
        LeftNavigationDrawer.navigationToBoardsTemplatesHomeWorkspaces.get(0).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/templates");
        LeftNavigationDrawer.navigationToBoardsTemplatesHomeWorkspaces.get(1).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/");
    }
}
