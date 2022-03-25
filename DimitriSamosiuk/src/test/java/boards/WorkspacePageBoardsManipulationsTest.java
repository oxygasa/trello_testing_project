package boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.workspaces.WorkspaceListPage;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
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
                CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
                Assert.fail("Flaking test fail");
            }
        }

        /**
         * Post condition
         * 1. Close all visible boards.
         **/
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }

    //TC ID TRE017 Workspace page: Boards add to  favourite.
    @Test
    public static void boardsAddToFavouriteTest() throws InterruptedException {
        /**
         * Precondition
         * 1. Close all visible boards.
         **/
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
        /**
         * Steps to Reproduce
         * 1. Open any workspace.
         * 2. Add/remove to/from favorite any board.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.boardTile.get(0));
        BoardsPage.boardTile.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.starIconInTheBoardPage);
        BoardsPage.starIconInTheBoardPage.click();
        /**
        * Expected result
        * 2. Changes and star icons are displayed or not displayed on the favourite/unfavoured board.
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
        try{
        BoardsPage.boardStarredIcon.click();
        } catch (NoSuchElementException e) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        }
        /**
         * Post condition
         * 1. Close all visible boards.
         **/
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }

    //TC ID TRE018 Workspace page: Boards filter menu
    @Test
    public static void boardsFilterMenuTest() throws InterruptedException {
        /**
         * Preconditions
         * Close all visible boards, because the Java.Collection.List will be prepared for a saving the boards name.
         **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
        /**
         * Preconditions
         * Create 4 boards (and remember their names).
         **/
        List<String> expectedBoardNamesListener = new ArrayList<>();
        while (BoardsPage.boardList.size() < 4) {
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
        System.out.println("Expected List" + expectedBoardNamesListener);

        /**
         * Steps to Reproduce
         2. Choose Most recently active.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter,4);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesMost = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesMost.add(BoardsPage.boardTitles.get(i).getText());
        }
        System.out.println("Most recently active " + actualResultBoardNamesMost);
        Assert.assertEquals(actualResultBoardNamesMost,expectedBoardNamesListener);
        /**
         * Steps to Reproduce
         * 3. Choose Least recently active.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter,1);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesLeast = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesLeast.add(BoardsPage.boardTitles.get(i).getText());
        }
        System.out.println("Least recently " + actualResultBoardNamesLeast);
        Assert.assertEquals(actualResultBoardNamesLeast,expectedBoardNamesListener);
        /**
         * Steps to Reproduce
         * 4. Choose Alphabetically A - Z.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter,2);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        List<String> actualResultBoardNamesAZ = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesAZ.add(BoardsPage.boardTitles.get(i).getText());
        }
        System.out.println("A - Z " + actualResultBoardNamesLeast);
        Assert.assertEquals(actualResultBoardNamesAZ,expectedBoardNamesListener);
        /**
         * Steps to Reproduce
         * 5. Choose Alphabetically Z - A.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(BoardsPage.sortByFilter,3);
        CommonActions.explicitWaitOfElementsListVisible(BoardsPage.boardTitles);
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(expectedBoardNamesListener);
        List<String> actualResultBoardNamesZA = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            actualResultBoardNamesZA.add(BoardsPage.boardTitles.get(i).getText());
        }
        System.out.println("Z - A " + actualResultBoardNamesLeast);
        Assert.assertEquals(actualResultBoardNamesZA,expectedBoardNamesListener);
        /**
         * Steps to Reproduce
         * 6. Filter by collection.
         * 7. Search field filling.
         **/
        BoardsPage.filterByCollectionDropdown.click();
        Assert.assertEquals(BoardsPage.filterByCollectionPremiumRequireButton.getText(),"Try it free for 14 days");
        BoardsPage.filterByCollectionPremiumRequireClosePopupButton.click();
        BoardsPage.boardsSearchBox.sendKeys(actualResultBoardNamesZA.get(0));
        List<String> searchBoardResult = new ArrayList<>();
        for (int i = 0; i < BoardsPage.boardTitles.size(); i++) {
            searchBoardResult.add(BoardsPage.boardTitles.get(i).getText());
        }
        Assert.assertEquals(searchBoardResult.size(),1);
        /**
         * Post condition
         * 1. Close all visible boards.
         **/
        while (BoardsPage.boardList.size() > 1) {
            CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createNewBoardSign);
        }
    }
}

