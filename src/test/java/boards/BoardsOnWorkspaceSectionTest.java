package boards;

import base.BaseTest;
import commons.CommonActions;
import commons.FlakingTestRepeater;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;

import java.util.Collections;
import java.util.List;

import static commons.CommonActions.driver;

public class BoardsOnWorkspaceSectionTest extends BaseTest {

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public void boardsCreationTest() throws InterruptedException {
        /*** Precondition: login **/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        /*** Create a board with random parameters from Workspaces. **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.isFirstBoardDisplays();
        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards((boardsPage.getDefaultWorkspaceUrl()));
    }


    //TC ID TRE015 Workspace page: Boards Free account limit counter checking
    @Test (retryAnalyzer = FlakingTestRepeater.class)
    public void boardsFreeAccountLimitCounterTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        /*** Open or create any workspace, create 10 boards. **/
            while (boardsPage.countBoardsNumber() < 10) {
                try {
                    CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
                    driver.get(boardsPage.getDefaultWorkspaceUrl());
                } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                    CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
                    driver.get(boardsPage.getDefaultWorkspaceUrl());
                }
            }
            driver.get(boardsPage.getDefaultWorkspaceUrl());
            boardsPage.startCreateBoard(); //11-th time create the board.
            Thread.sleep(2000);
            /*** Expected result: A premium requiring pop up is shown after 10-th creating new board. **/
            boardsPage.the11thBoardFreeAccAssert();
            CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE017 Workspace page: Boards add to  favourite.
    @Test
    public void boardsAddToFavouriteTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        /*** Open any workspace. Add to favorite any board inside the board instance. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.isStartCreateBoardDisplays();
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard().activateStarSign();

        /**
         * Actions and assertions: Star icons are displayed or not displayed
         * on the favourite/unfavoured board due clicking the star icon on the board list page.
         **/
        boardsPage.isStarDisplaysInsideBoard();
        driver.navigate().back();
        boardsPage.isStarInteractiveInTheBoardList();
        boardsPage.openFirstExistingBoard();
        try {
            boardsPage.deactivateStarSign(); // flaking locator
        } catch (NoSuchElementException e) {
            driver.get(boardsPage.getDefaultWorkspaceUrl());
        }
        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE018 Workspace page: Boards filter menu
    @Test
    public void boardsFilterMenuTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. Create 4 boards (and remember their names). **/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        List<String> expectedBoardNamesListener = boardsPage.createCollectionOfFiveExpectedBoards();
        System.out.println("Expected List" + expectedBoardNamesListener);
        /*** Choose Most recently active. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesMostRecently = boardsPage.chooseMostRecentlyFromDropdownAndSaveToCollection();
        System.out.println("Most recently active " + actualResultBoardNamesMostRecently);
        Assert.assertEquals(actualResultBoardNamesMostRecently, expectedBoardNamesListener);
        /*** Choose Least recently active. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesLeastRecently = boardsPage.chooseLeastRecentlyFromDropdownAndSaveToCollection();
        System.out.println("Least recently " + actualResultBoardNamesLeastRecently);
        Assert.assertEquals(actualResultBoardNamesLeastRecently, expectedBoardNamesListener);
        /*** Choose Alphabetically A - Z. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        List<String> actualResultBoardNamesAZ = boardsPage.chooseAZFromDropdownAndSaveToCollection();
        System.out.println("A - Z " + actualResultBoardNamesAZ);
        Assert.assertEquals(actualResultBoardNamesAZ, expectedBoardNamesListener);
        /*** Choose Alphabetically Z - A. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesZA = boardsPage.chooseZAFromDropdownAndSaveToCollection();
        System.out.println("Z - A " + actualResultBoardNamesZA);
        Assert.assertEquals(actualResultBoardNamesZA, expectedBoardNamesListener);
        /** Filter by collection. Search field filling. **/
        boardsPage.activatePremiumBoardFilter()
                .checkSearchBoxFindingExistingBoard();

        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }
}

