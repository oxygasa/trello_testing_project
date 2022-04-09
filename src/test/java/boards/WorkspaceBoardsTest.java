package boards;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.workspaces.WorkspaceListPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static commons.CommonActions.driver;

public class WorkspaceBoardsTest extends BaseTest {

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public static void boardsCreationBeginTest() throws InterruptedException {
        /*** Precondition: login **/
        PageFactory.initElements(driver, BoardsPage.class);
        /*** Create a board with random parameters from Workspaces. **/
        CommonActions.closeAllVisibleBoards((BoardsPage.TEN_BOARDS_TESTING_WORKSPACE));
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Assert.assertTrue(BoardsPage.boardInstancesList.get(0).isDisplayed());
        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards((BoardsPage.TEN_BOARDS_TESTING_WORKSPACE));
    }

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public static void boardsCreationContinueTest() throws InterruptedException {
        /*** Precondition: login, create a board instance **/
        PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, WorkspaceListPage.class);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Thread.sleep(2000);
        WorkspaceListPage.workspaceNavigationExpandButton.click();
        WorkspaceListPage.addBoardFromLeftNavigationDrawer.click();
        /*** Expected result: Left Navigation Drawer contains a Board instance created. **/
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
        /*** Post condition: Close all visible boards. **/
        WorkspaceListPage.workspaceNavigationCollapseButton.click();
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE015 Workspace page: Boards Free account limit counter checking
    @Test
    public static void boardsFreeAccountLimitCounterTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        PageFactory.initElements(driver, BoardsPage.class);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open or create any workspace, create 10 boards. **/
        try {
            while (BoardsPage.boardInstancesList.size() < 10) {
                try {
                    CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                    driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                    CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                    driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                }
            }
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createBoardFromBoardsPageButton.get(0));
            BoardsPage.createBoardFromBoardsPageButton.get(0).click();
            Thread.sleep(2000);
            String startFreeTrialTextOnButton = BoardsPage.startFreeTrialButton.getText();
            /*** Expected result: A premium requiring pop up is shown after 10-th creating new board. **/
            Assert.assertEquals(startFreeTrialTextOnButton, BoardsPage.EXPECTED_PREMIUM_REQUIRE_TEXT); //free version ask for premium.
            CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            Assert.assertEquals(BoardsPage.premiumUserStatusText.getText(), "Premium"); //is it premium account?
            CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }
    }

    //TC ID TRE017 Workspace page: Boards add to  favourite.
    @Test
    public static void boardsAddToFavouriteTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any workspace. Add to favorite any board inside the board instance. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.boardTile.get(0));
        BoardsPage.boardTile.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.starIconInTheBoardPage);
        BoardsPage.starIconInTheBoardPage.click();
        /**
         * Actions and assertions: Star icons are displayed or not displayed
         * on the favourite/unfavoured board due clicking the star icon on the board list page.
         **/
        Assert.assertTrue(BoardsPage.boardStarredIcon.isDisplayed());
        driver.navigate().back();
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.boardsStarredIcon.get(0));
        BoardsPage.boardsStarredIcon.get(0).click();
        Assert.assertFalse(BoardsPage.boardsStarredIcon.get(0).isSelected());
        BoardsPage.boardsStarredIcon.get(0).click();
        Assert.assertTrue(BoardsPage.boardsStarredIcon.get(0).isDisplayed());
        BoardsPage.boardsStarIcon.get(0).click();
        Assert.assertFalse(BoardsPage.boardsStarredIcon.get(0).isSelected());
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.boardTile.get(0));
        BoardsPage.boardTile.get(0).click();
        try {
            BoardsPage.boardStarredIcon.click();
        } catch (NoSuchElementException e) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }
        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE018 Workspace page: Boards filter menu
    @Test
    public static void boardsFilterMenuTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. Create 4 boards (and remember their names). **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        List<String> expectedBoardNamesListener = BoardsPage.createCollectionOfFiveExpectedBoards();
        System.out.println("Expected List" + expectedBoardNamesListener);

        /*** Choose Most recently active. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesMostRecently = BoardsPage.chooseMostRecentlyFromDropdownAndSaveToCollection();
        System.out.println("Most recently active " + actualResultBoardNamesMostRecently);
        Assert.assertEquals(actualResultBoardNamesMostRecently, expectedBoardNamesListener);
        /*** Choose Least recently active. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesLeastRecently = BoardsPage.chooseLeastRecentlyFromDropdownAndSaveToCollection();
        System.out.println("Least recently " + actualResultBoardNamesLeastRecently);
        Assert.assertEquals(actualResultBoardNamesLeastRecently, expectedBoardNamesListener);
        /*** Choose Alphabetically A - Z. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        List<String> actualResultBoardNamesAZ = BoardsPage.chooseAZFromDropdownAndSaveToCollection();
        System.out.println("A - Z " + actualResultBoardNamesAZ);
        Assert.assertEquals(actualResultBoardNamesAZ, expectedBoardNamesListener);
        /*** Choose Alphabetically Z - A. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesZA = BoardsPage.chooseZAFromDropdownAndSaveToCollection();
        System.out.println("Z - A " + actualResultBoardNamesZA);
        Assert.assertEquals(actualResultBoardNamesZA, expectedBoardNamesListener);
        /** Filter by collection. Search field filling. **/
        BoardsPage.filterByCollectionDropdown.click();
        try {
            Assert.assertEquals(BoardsPage.filterByCollectionPremiumRequireButton.getText(), "Try it free for 14 days");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.assertEquals(BoardsPage.premiumCreateCollection.getText(), "Create a collection");
        }

        BoardsPage.filterByCollectionPremiumRequireClosePopupButton.click();
        BoardsPage.boardsSearchBox.sendKeys(actualResultBoardNamesZA.get(0));
        List<String> searchBoardResult = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            searchBoardResult.add(BoardsPage.boardTitles.get(i).getText());
        }
        Assert.assertEquals(searchBoardResult.size(), 1);
        /*** Post condition: Close all visible boards. **/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }
}

