package cards.header;

import base.BaseTest;
import boards.WorkspaceBoardsTest;
import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.card_list_preview_page.CardListPreviewPage;
import pages.cards.header.CardsHeader;
import pages.cards.power_ups.PowerUpsPage;
import pages.workspaces.WorkspaceListPage;

import static commons.CommonActions.driver;

public class CardsHeaderTest extends BaseTest {

    //TC ID TRE023 Board dropdown Premium require checking.
    @Test
    public static void boardViewSwitcherPremiumRequireTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Click Board button. **/
        try {
            CommonActions.explicitWaitOfOneElementVisible(CardsHeader.boardViewSwitcherButton);
            CardsHeader.boardViewSwitcherButton.click();
            /*** A Board is opened. A module with a Premium asking is displayed.**/
            Assert.assertTrue(CardsHeader.boardViewSwitcherFreeTrialButton.isDisplayed()); //trial assert trying
        } catch (org.openqa.selenium.NoSuchElementException e)
        {
            CardsHeader.BoardViewSwitcherPremiumBoardButton.isDisplayed(); //premium assert trying
        }
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE024 Board name changing.
    @Test
    public static void boardNameChangingTest() throws InterruptedException {
        /*** Precondition: login**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Change the board name. **/
        Actions actions = new Actions(driver);
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.boardRenameInput);
        String boardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        actions.click(CardsHeader.boardRenameInput).sendKeys(boardNameTextFieldInputText).sendKeys(Keys.ENTER).build().perform();
        String expectedTitleAfterRename = CardsHeader.boardRenameInput.getText();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        System.out.println("expected" + expectedTitleAfterRename);
        System.out.println("actual" + BoardsPage.boardTitles.get(0).getText());
        Assert.assertEquals(BoardsPage.boardTitles.get(0).getText(), expectedTitleAfterRename);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE025 Star button clickable.
    @Test
    public static void starButtonClickableTest() throws InterruptedException {
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        WorkspaceBoardsTest.boardsAddToFavouriteTest();
    }

    //TC ID TRE026 Change and show workspaces.
    @Test
    public static void changeAndShowWorkspacesTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Change the workspace. Check the place of the board is changed. **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.boardInstancesList.get(0).click();
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
        Assert.assertEquals(resultTextOfDisplayNameTitle, workspaceNameExpected);
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
        try {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            if (CardsHeader.workspaceVisibleButtonText.getText().equals("Public")) {
                CardsHeader.workspaceVisibleButton.click();
                CommonActions.explicitWaitOfOneElementVisible(CardsHeader.privateBoardPermissionSelect);
                CardsHeader.privateBoardPermissionSelect.click();
                driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
                CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            }
        }
        /*** 1. Open any board.
         2. Change a workspace visible (3. Private, 6. Public).
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.boardInstancesList.get(0).click();
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
        Assert.assertEquals(CardsHeader.boardTitleFor2ndUser.getText(), expectedBoardName);
    }

    //TC ID TRE027 Workspace visible change.
    @Test
    public static void workspaceVisibleChangeContinueTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        BoardsPage.boardInstancesList.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButton);
        if (CardsHeader.workspaceVisibleButtonText.getText().equals("Public")) {
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
        Assert.assertEquals(CardsHeader.boardTitleFor2ndUser.getText(), expectedBoardName);
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

    /*** BLOCKER: Can't copy hidden invite link for future usage**/
//    //TC ID TRE029 Invite sending.
//    @Test
//    public static void inviteSendingTest() throws InterruptedException {
//        CardsHeader.inviteAndConnect2ndUserToTheBoardViaLink();
//    }

    //TC ID TRE030 Power-Ups functionality.
    @Test
    public static void powerUpsFunctionalityTest() throws InterruptedException {
        /*** Precondition**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, PowerUpsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /*** Open any board. Click Board button. **/
        CommonActions.explicitWaitOfOneElementVisible(PowerUpsPage.powerUpsButton);
        PowerUpsPage.powerUpsButton.click();
        CommonActions.explicitWaitOfOneElementVisible(PowerUpsPage.confirmRedirectToPowerUpsPage);
        PowerUpsPage.confirmRedirectToPowerUpsPage.click();
        Thread.sleep(8000);
        CommonActions.explicitWaitOfOneElementVisible(PowerUpsPage.madeByTrelloButton);
        PowerUpsPage.madeByTrelloButton.click();
        CommonActions.explicitWaitOfOneElementVisible(PowerUpsPage.addPowerUpJiraButton);
        PowerUpsPage.addPowerUpJiraButton.click();
        Assert.assertTrue(PowerUpsPage.powerUpJiraSettingButton.isDisplayed());
        PowerUpsPage.closePowerUpsPageButton.click();
        PowerUpsPage.installedPowerUpOpeningButton.click();
        CommonActions.explicitWaitOfOneElementVisible(PowerUpsPage.installedPowerUpInteractiveWindow);
        Assert.assertTrue(PowerUpsPage.installedPowerUpInteractiveWindow.isDisplayed());
        /*** Post condition**/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE 031 Filter testing.
    @Test
    public static void filterTest() throws InterruptedException {
        /*** Precondition:login, create a board, create the card for the filter testing**/
        PageFactory.initElements(driver, CardsHeader.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.createOneRandomBoardInstance(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        String expectedCardName = CardListPreviewPage.createRandomCardOnTheList(); // тут у меня null pointer, lol
        int randomLabelColorPositionNumber = CardListPreviewPage.randomLabelColor();
        /*** Open any board. Click Board button. **/
        CardsHeader.filterPopoverButton.click();
        CardsHeader.filerKeywordInput.sendKeys(expectedCardName);
        CardsHeader.cardsAssignedToMeCheckBox.click();//assigned to me
        CardsHeader.allFilterCheckboxes.get(3).click();//no date
        CardsHeader.allFilterDropdowns.get(0).click();//labels color
        CommonActions.selectDropdownMenuValueByPositionNumber(CardsHeader.allFilterDropdowns.get(0),randomLabelColorPositionNumber);//labels color
    }
}
