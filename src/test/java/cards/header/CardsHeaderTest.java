package cards.header;

import base.BaseTest;
import boards.BoardsOnWorkspaceSectionTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.header.CardsHeader;
import pages.cards.power_ups.PowerUpsPage;
import pages.workspaces.WorkspaceListPage;

import static commons.CommonActions.driver;

public class CardsHeaderTest extends BaseTest {

    //TC ID TRE023 Board dropdown Premium require checking.
    @Test
    public void boardViewSwitcherPremiumRequireTest() throws InterruptedException {
        /*** Precondition**/
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        /*** Open any board. Click Board button. **/
        cardsHeader.tryToChangeBoardViewAsFreeUser();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE024 Board name changing.
    @Test
    public void boardNameChangingTest() throws InterruptedException {
        /*** Precondition: login**/
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        /*** Open any board. Change the board name. **/
        cardsHeader.renameBoard();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE025 Star button clickable.
    @Test
    public void starButtonClickableTest() throws InterruptedException {
        BoardsOnWorkspaceSectionTest boardsOnWorkspaceSectionTest = new BoardsOnWorkspaceSectionTest();
        boardsOnWorkspaceSectionTest.boardsAddToFavouriteTest();
    }

    //TC ID TRE026 Change and show workspaces.
    @Test
    public void changeAndShowWorkspacesTest() throws InterruptedException {
        /*** Precondition**/
       CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        /*** Open any board. Change the workspace. Check the place of the board is changed. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardsHeader.changeWorkspace();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(driver.getCurrentUrl());
    }

    //TC ID TRE027 Workspace visible change.
    @Test
    public void workspaceVisibleChangeTest() throws InterruptedException {
        /*** Precondition**/
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);

        try {
            driver.get(boardsPage.getDefaultWorkspaceUrl());
            CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
            CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            if (cardsHeader.getVisibleWorkspaceButtonText().equals("Public")) {
                cardsHeader.selectPrivateVisible();
                driver.get(boardsPage.getDefaultWorkspaceUrl());
                CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
                CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
            }
        }
        /*** 1. Open any board.
         2. Change a workspace visible (3. Private, 6. Public).
         **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardsHeader.selectPublicVisible();
        String boardUrl = driver.getCurrentUrl();
        cardsHeader.logoutFromTrello();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardUrl);
    }

    //TC ID TRE027 Workspace visible change.
    @Test
    public void workspaceVisibleChangeContinueTest() throws InterruptedException {
        /*** Precondition**/
       CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        if (cardsHeader.getVisibleWorkspaceButtonText().equals("Public")) {
            cardsHeader.selectPrivateVisible();
        }
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        /*** 1. Open any board.
         2. Change a workspace visible (3. Private, 4. Workspace).
         **/
        String boardUrl = driver.getCurrentUrl();
        cardsHeader.logoutFromTrello();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardUrl);
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    /*** BLOCKER: Can't copy hidden invite link for future usage**/
////TC ID TRE028 Member board permission change.

    /*** BLOCKER: Can't copy hidden invite link for future usage**/
////TC ID TRE029 Invite sending.

    //TC ID TRE030 Power-Ups functionality.
    @Test
    public void powerUpsFunctionalityTest() throws InterruptedException {
        /*** Precondition**/
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, PowerUpsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
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
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE 031 Filter testing.
    @Test
    public void filterTest() throws InterruptedException {
        /*** Precondition:login, create a board, create the card for the filter testing**/
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        /*** Select random filter values **/
        cardsHeader.selectRandomFilterDetails();
    }
}
