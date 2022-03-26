package boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static commons.CommonActions.driver;

public class BoardsPageManipulationTest {

    //TC ID TRE020 Boards page: Recently viewed.
    @Test
    public static void recentlyViewedTest() throws InterruptedException {
        /**
         * Preconditions
         * Close all visible boards, because the Java.Collection.List will be prepared for a saving the boards name.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(500);
        while (BoardsPage.boardInstancesList.size() > 0) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
        /**
         * Preconditions
         * Create 4 boards (and remember their names).
         **/
        List<String> expectedBoardNamesListener = new ArrayList<>();
        while (BoardsPage.boardInstancesList.size() < 4) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
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
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Expected List" + expectedBoardNamesListener);

        /**
         * Steps to Reproduce
         2. Check, the /boards page contains the same boards.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE + "/boards");
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardsPageAllWorkspacesBoards);
        List<String> actualResultBoardNames = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardsPageAllWorkspacesBoards.size(); i++) {
            actualResultBoardNames.add(BoardsPage.boardsPageAllWorkspacesBoards.get(i).getText());
        }
        System.out.println("Actual result " + actualResultBoardNames);
        Assert.assertEquals(actualResultBoardNames, expectedBoardNamesListener);

        /**
         * Post condition.
         * Close all visible boards
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(500);
        while (BoardsPage.boardInstancesList.size() > 0) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }

    //TC ID TRE021 Boards page: Your workspaces.
    @Test
    public static void yourWorkspacesTest() throws InterruptedException {
        /**
         * Preconditions
         * Close all visible boards, because the Java.Collection.List will be prepared for a saving the boards name.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(500);
        while (BoardsPage.boardInstancesList.size() > 0) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
        /**
         * Preconditions
         * Create 4 boards (and remember their names).
         **/
        List<String> expectedBoardNamesListener = new ArrayList<>();
        while (BoardsPage.boardInstancesList.size() < 1) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
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
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Expected List" + expectedBoardNamesListener.get(0));

        /**
         * Steps to Reproduce
         2. Check, the /boards page contains the same boards.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE + "/boards");
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardsPageRecentBoardAndAllBoards);
        List<String> actualResultBoardNames = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardsPageRecentBoardAndAllBoards.size(); i++) {
            actualResultBoardNames.add(BoardsPage.boardsPageRecentBoardAndAllBoards.get(i).getText());
        }
        System.out.println("Actual result " + actualResultBoardNames.get(0));
        Assert.assertEquals(actualResultBoardNames.get(0), expectedBoardNamesListener.get(0));

        /**
         * Post condition.
         * Close all visible boards
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(500);
        while (BoardsPage.boardInstancesList.size() > 0) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }
}
