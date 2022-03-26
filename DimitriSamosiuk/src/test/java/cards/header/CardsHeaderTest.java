package cards.header;

import boards.WorkspacePageBoardsManipulationsTest;
import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.header.CardsHeader;
import pages.workspaces.WorkspaceListPage;
import workspaces.WorkspaceListPageTest;

import static commons.CommonActions.driver;

public class CardsHeaderTest {

    //TC ID TRE023 Board dropdown Premium require checking.
    @Test
    public static void boardViewSwitcherPremiumRequireTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Click Board button. **/
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.boardViewSwitcherButton);
        CardsHeader.boardViewSwitcherButton.click();
        /*** A Board is opened. A module with a Premium asking is displayed.**/
        Assert.assertTrue(CardsHeader.boardViewSwitcherFreeTrialButton.isDisplayed());
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE024 Board name changing.
    @Test
    public static void boardNameChangingTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Change the board name. **/
        Actions actions = new Actions(driver);
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.boardRenameInput);
        String boardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        actions.click(CardsHeader.boardRenameInput).sendKeys(boardNameTextFieldInputText).sendKeys(Keys.ENTER).build().perform();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        System.out.println("expected"+boardNameTextFieldInputText);
        System.out.println("actual"+BoardsPage.boardTitles.get(0).getText());
        Assert.assertEquals(BoardsPage.boardTitles.get(0).getText(),boardNameTextFieldInputText);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE025 Star button clickable.
    @Test
    public static void starButtonClickableTest() throws InterruptedException {
        WorkspacePageBoardsManipulationsTest.boardsAddToFavouriteTest();
    }

    //TC ID TRE026 Change and show workspaces.
    @Test
    public static void changeAndShowWorkspacesTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Change the workspace. Check the place of the board is changed. **/
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.openWorkspaceMenu);
        CardsHeader.openWorkspaceMenu.click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.changeWorkspaceMenu);
        CardsHeader.changeWorkspaceMenu.click();
        CommonActions.selectDropdownMenuNextValue(CardsHeader.changeWorkspaceDropdownMenu);
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.changeWorkspaceSubmitButton);
        CardsHeader.changeWorkspaceSubmitButton.click();
        Thread.sleep(3000);
        String workspaceNameExpected = CardsHeader.openWorkspaceMenu.getText();
        CardsHeader.openWorkspaceMenu.click();
        CardsHeader.viewWorkspaceMenu.click();
        String resultTextOfDisplayNameTitle = WorkspaceListPage.savedDisplayNameTitle.getText();
        Assert.assertEquals(resultTextOfDisplayNameTitle,workspaceNameExpected);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(driver.getCurrentUrl());
    }

    //TC ID TRE027 Workspace visible change.
    @Test
    public static void workspaceVisibleChangeTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.boardInstancesList.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButton);
        if(CardsHeader.workspaceVisibleButtonText.getText().equals("Public")){
            CardsHeader.workspaceVisibleButton.click();
            CommonActions.explicitWaitOfOneElementVisible(CardsHeader.privateBoardPermissionSelect);
            CardsHeader.privateBoardPermissionSelect.click();
        }
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** 1. Open any board.
         2. Change a workspace visible (3. Private, 6. Public).
         **/
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButton);
        String expectedBoardName = CardsHeader.boardRenameInput.getText();
        CardsHeader.workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.publicBoardPermissionSelect);
        CardsHeader.publicBoardPermissionSelect.click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.publicBoardPermissionConfirmButton);
        CardsHeader.publicBoardPermissionConfirmButton.click();
        Thread.sleep(3000);
        String boardUrl = driver.getCurrentUrl();
        CardsHeader.avatarIcon.click();
        CardsHeader.logoutButton.click();
        driver.manage().deleteAllCookies();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardUrl);
        Assert.assertEquals(CardsHeader.boardTitleFor2ndUser.getText(),expectedBoardName);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE027 Workspace visible change.
    @Test
    public static void workspaceVisibleChangeContinueTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.boardInstancesList.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButton);
        if(CardsHeader.workspaceVisibleButtonText.getText().equals("Public")){
            CardsHeader.workspaceVisibleButton.click();
            CommonActions.explicitWaitOfOneElementVisible(CardsHeader.privateBoardPermissionSelect);
            CardsHeader.privateBoardPermissionSelect.click();
        }
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** 1. Open any board.
         2. Change a workspace visible (3. Private, 4. Workspace).
         **/
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButton);
        String expectedBoardName = CardsHeader.boardRenameInput.getText();
        CardsHeader.workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceBoardPermissionSelect);
        CardsHeader.workspaceBoardPermissionSelect.click();
        Thread.sleep(3000);
        String boardUrl = driver.getCurrentUrl();
        CardsHeader.avatarIcon.click();
        CardsHeader.logoutButton.click();
        driver.manage().deleteAllCookies();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardUrl);
        Assert.assertEquals(CardsHeader.boardTitleFor2ndUser.getText(),expectedBoardName);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    /*** BLOCKER: Can't copy hidden invite link for future usage**/
//    //TC ID TRE028 Member board permission change.
//    @Test
//    public static void memberBoardPermissionTest() throws InterruptedException {
//        /*** Precondition**/
//        PageFactory.initElements(driver, CardsHeader.class);
//        PageFactory.initElements(driver, BoardsPage.class);
//        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
//        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
//        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
//        /***
//         * Steps to Reproduce
//         * Open any board, then click an account icon on a member board.
//         * Check the following redirections Edit profile info button, View member’s board activity button,
//         * Change permissions button.
//         **/
//        CardsHeader.inviteAndConnect2ndUserToTheBoardViaLink();
//
//        Assert.assertTrue(CardsHeader.boardViewSwitcherFreeTrialButton.isDisplayed());
//        /*** Post condition**/
//        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
//    }
//
//    //TC ID TRE029 Invite sending.
//    @Test
//    public static void inviteSendingTest() throws InterruptedException {
//        CardsHeader.inviteAndConnect2ndUserToTheBoardViaLink();
//    }

    //TC ID TRE030 Power-Ups functionality.
    @Test
    public static void powerUpsFunctionalityTest() {
    }

    //TC ID TRE 031 Filter testing.
    @Test
    public static void filterTest() {
    }
}
