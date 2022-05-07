package cards.header;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.card_list.CardListPage;
import pages.cards.header.CardsHeader;
import pages.cards.power_ups.PowerUpsPage;

import static commons.CommonActions.driver;

public class CardsHeaderTest extends BaseTest {
    BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
    PowerUpsPage powerUpsPage = PageFactory.initElements(driver, PowerUpsPage.class);
    CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
    CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
    //TC ID TRE023 Board dropdown Premium require checking.
    @Test
    public void boardViewSwitcherPremiumRequireTest() throws InterruptedException {
        /*** Precondition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        /*** Open any board. Click Board button. **/
        cardsHeader.tryToChangeBoardViewAsFreeUser();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE024 Board name changing.
    @Test (groups={"smoke", "critical_path"})
    public void boardNameChangingTest() throws InterruptedException {
        /*** Precondition: login**/
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
     //TC ID TRE 025 is a repeat of TC ID TRE017 Workspace page: Boards add to  favourite.
     // Because TC ID TRE017 cover inside board assert.
    }

    //TC ID TRE026 Change and show workspaces.
    @Test
    public void changeAndShowWorkspacesTest() throws InterruptedException {
        /*** Precondition**/
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
    @Test (groups={"smoke", "critical_path"})
    public void workspaceVisibleChangeTest() throws InterruptedException {
        /*** Precondition**/
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
    @Test (groups={"smoke", "critical_path"})
    public void workspaceVisibleChangeContinueTest() throws InterruptedException {
        /*** Precondition**/
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

    //TC ID TRE028 Member board permission change.
    @Test
    public void adminPermissionInactiveTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        cardsHeader
                .isInviteLinkValid()
                .startLoginAfterInvite();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardsHeader.checkTheHigherAndSelfPermissionsAreInactive();
    }

    //TC ID TRE029 Invite link is valid.
    @Test
    public void boardByInviteLinkTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        cardsHeader.isInviteLinkValid();

    }
    //TC ID TRE030 Power-Ups functionality.
    @Test (groups={"critical_path"})
    public void powerUpsFunctionalityTest() throws InterruptedException {
        /*** Precondition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        /*** Open any board. Click Board button. **/
        powerUpsPage.openPowUpsStore()
                        .installJira()
                        .IsJiraInstalled();
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE 031 Filter testing.
    @Test
    public void filterTest() throws InterruptedException {
        /*** Precondition:login, create a board, create the card for the filter testing**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        /*** Select random filter values **/
        cardListPage.createFewCards();
        cardsHeader.selectRandomFilterDetails();
    }
}
