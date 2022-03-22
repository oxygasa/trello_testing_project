package boards;

import commons.CommonActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.workspaces.WorkspaceListPage;


import static commons.CommonActions.driver;

public class WorkspacePageBoardsManipulationsTest {

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public static void boardsCreationTestBegin() throws InterruptedException {
        /**
         * Steps to Reproduce
         * Login to your account within precondition credentials.
         * Select the Workspace on a header.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        /**
         * Create a board with random parameters from Workspaces.
         **/
        CommonActions.createOneRandomBoardInstance(BoardsPage.WORKSPACE25);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardNameInput);
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
        /**
         * Post condition
         * Close the created board.
         **/
        CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.WORKSPACE25);
    }

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public static void boardsCreationTestContinue() throws InterruptedException {
        /**
         * Create the board then open the Left Navigation Drawer.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, WorkspaceListPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        CommonActions.createOneRandomBoardInstance(BoardsPage.WORKSPACE25);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardNameInput);
        driver.get(BoardsPage.WORKSPACE25);
        WorkspaceListPage.workspaceNavigationExpandButton.click();
        WorkspaceListPage.addBoardFromLeftNavigationDrawer.click();
        /**
         * Left Navigation Drawer contains a Board created.
         **/
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
        /**
         * Post condition
         * 1. Close the created board.
         **/
        WorkspaceListPage.workspaceNavigationCollapseButton.click();
        CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.WORKSPACE25);
    }



    //TC ID TRE015 Workspace page: Boards Free account limit counter checking
    @Test
    public static void boardsFreeAccountLimitCounterContinueTest() throws InterruptedException {
        /**
         * Precondition
         * 1. Close all 10 created boards before.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(500);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            Thread.sleep(500);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
        /**
         * Steps to Reproduce
         * 1. Open or create any workspace
         * 2. Create 10 boards.
         **/
        try {
            for (int i = 0; i < 10; i++) {
                CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            }
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createBoardFromBoardsPageButton.get(0));
            BoardsPage.createBoardFromBoardsPageButton.get(0).click();
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.startFreeTrialButton);
            String startFreeTrialTextOnButton = BoardsPage.startFreeTrialButton.getText();
            /**
             * Expected result
             * 2. A premium requiring pop up is shown after 10-th creating new board.
             **/
            Assert.assertEquals(startFreeTrialTextOnButton, BoardsPage.EXPECTED_PREMIUM_REQUIRE_TEXT);
        } catch (NoSuchElementException e) {
            while (BoardsPage.boardList.size() > 1) {
                CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                Thread.sleep(500);
                CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
                Assert.fail("Flaking test fail");
            }
        }

        /**
         * Post condition
         * 1. Close all 10 created boards.
         **/
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            Thread.sleep(500);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }

    //TC ID TRE017 Workspace page: Boards add to  favourite.
    @Test
    public static void boardsAddToFavouriteTest() {

    }

    //TC ID TRE018 Workspace page: Boards filter menu
    @Test
    public static void boardsFilterMenuTest() {
    }


}

