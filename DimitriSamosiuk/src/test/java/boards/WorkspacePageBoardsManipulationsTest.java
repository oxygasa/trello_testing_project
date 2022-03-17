package boards;

import commons.CommonActions;
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
    public static void boardsCreationTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials.
         * 2. Select the Workspace on a header.
         * 3. Select Your Workspace.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.WORKSPACE25);
        /**
         * Steps to Reproduce
         * 4. Close the last board where boards number is bigger than 8 (for automation purposes)
         **/
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(driver -> BoardsPage.remainingBoardsCounter);
        String remainingCounter = BoardsPage.remainingBoardsCounter.getText();
        char firstChar = remainingCounter.charAt(0);
        int counterNumber = Integer.parseInt(String.valueOf(firstChar));
        if (counterNumber < 5) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.WORKSPACE25);
        }
        /**
         * Steps to Reproduce
         * 5. Create a board with random parameters from Workspaces.
         **/
        CommonActions.createOneRandomBoardInstance(BoardsPage.WORKSPACE25);


        /**
         * Steps to Reproduce
         * 6. Open Boards page.
         * 7. Check the Creation board module is opened on a Board page.
         **/

        driver.get(BoardsPage.WORKSPACE25);
        String boardsUrl = driver.getCurrentUrl() + "/boards";
        driver.get(boardsUrl);
        wait.until(driver -> BoardsPage.createBoardFromBoardsPageButton);
        BoardsPage.createBoardFromBoardsPageButton.get(0).click();
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());

        /**
         * 8. Check the Creation board module is opened in a Left Navigation Drawer.
         **/
        driver.get(BoardsPage.WORKSPACE25);
        wait.until(driver -> WorkspaceListPage.addBoardFromLeftNavigationDrawer);
        WorkspaceListPage.addBoardFromLeftNavigationDrawer.click();
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
    }


    //TC ID TRE015 Workspace page: Boards Free account limit counter checking
    @Test
    public static void boardsFreeAccountLimitCounterTest() throws InterruptedException {
        /**
         * Precondition
         * 1. Close all 10 created boards before.
         **/
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        PageFactory.initElements(driver, BoardsPage.class);
        for (int i = 0; i < 10; i++) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            Thread.sleep(5000);
            String createNewBoardSignText = BoardsPage.createNewBoardSign.getText();
        if (!createNewBoardSignText.equals("Create new board")) {
                CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            }
        }
        /**
         * Steps to Reproduce
         * 1. Open or create any workspace
         * 2. Create 10 boards.
         **/
        for (int i = 0; i < 10; i++) {
            CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(5000);
        BoardsPage.createBoardFromBoardsPageButton.get(0).click();
        Thread.sleep(5000);
        String startFreeTrialTextOnButton = BoardsPage.startFreeTrialButton.getText();
        /**
         * Expected result
         * 2. A premium requiring pop up is shown after 10-th creating new board.
         **/
        Assert.assertEquals(startFreeTrialTextOnButton, BoardsPage.EXPECTED_PREMIUM_REQUIRE_TEXT);
        /**
         * Post condition
         * 1. Close all 10 created boards.
         **/
        for (int i = 0; i < 10; i++) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }
    }

    //TC ID TRE016 Workspace page: Boards deletion, recovery and permanent deletion.
    @Test
    public static void boardsDeleteRecoveryAndPermanentDeleteTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials
         * 2. Delete and recover any board.
         * 3. Delete permanently any board.
         * 4. Repeat with the header, your boards in the Left Navigation Drawer, boards in Workspace and Home page.
         **/
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Assert.assertTrue(BoardsPage.reopenBoardButton.isDisplayed());
        BoardsPage.reopenBoardButton.click();
        BoardsPage.reopenBoardConfirmButton.click();
        Thread.sleep(5000);
        CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.permanentDeleteLink.click();
        BoardsPage.permanentDeleteConfirmButton.click();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /**
         * Expected result
         * 3. Can't restore permanently deleted board.
         **/
        Assert.assertEquals(BoardsPage.createNewBoardSign.getText(), "Create new board");
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

