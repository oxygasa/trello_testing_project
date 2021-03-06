package cards.right_navigation_drawer;

import base.BaseTest;
import com.google.zxing.NotFoundException;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.card_fullscreen.FullscreenCardModePage;
import pages.cards.card_list.CardListPage;
import pages.cards.header.CardsHeader;
import pages.cards.right_navigation_draver.RightNavigationDrawer;
import pages.register.TempEmailSender;

import java.io.IOException;

import static commons.CommonActions.driver;

public class RightNavigationDrawerTest extends BaseTest {
    BoardsPage boardsPage = new BoardsPage(driver);
    RightNavigationDrawer rightNavigationDrawer = new RightNavigationDrawer(driver);
    CardListPage cardListPage = new CardListPage(driver);
    CardsHeader cardsHeader = new CardsHeader(driver);
    FullscreenCardModePage fullscreenCardModePage = new FullscreenCardModePage(driver);
    TempEmailSender tempEmailSender = new TempEmailSender(driver);

    @Test(description = "TC ID TRE032 Change Workspace test.", groups = {"critical_path"})
    public void changeWorkspaceTest() throws InterruptedException {
        /*** Precondition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.changeWorkspace();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    @Test(description = "TC ID TRE033 Card cover test.")
    public void cardCoverTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards()
                .addCardCover();
        rightNavigationDrawer.disableCover();
        /*** Post condition**/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    @Test(description = "TC ID TRE034 Commenting permissions.")
    public void commentingPermissionsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.changeCommentPermission();
        cardsHeader
                .isInviteLinkValid()
                .startLoginAfterInvite();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        fullscreenCardModePage
                .openFirstActiveCard()
                .isCommentTextFieldDoesntDisplay();
    }

    @Test(description = "TC ID TRE035 Add, Remove permissions")
    public void addRemovePermissionsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.addRemovePermTurnOff();
        cardsHeader
                .isInviteLinkValid()
                .startLoginAfterInvite();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardsHeader.checkTheHigherAndSelfPermissionsAreInactive();
    }

    @Test(description = "TC ID TRE036 Disallow Workspace members to edit and join.",
            groups = {"critical_path"})
    public void disAllowWorkspaceMembersToEditAndJoinTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.inviteOnlyTurnOn();
        String disallowedBoard = driver.getCurrentUrl();
        cardsHeader.logoutFromTrello();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        rightNavigationDrawer.navigateToDisallowedBoard(disallowedBoard);
        fullscreenCardModePage
                .openFirstActiveCard()
                .isCommentTextFieldDoesntDisplay();
    }

    @Test(description = "TC ID TRE037 Labels displaying on cards.")
    public void labelsDisplayingOnCardsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards()
                .addLabelWithText();
    }

    @Test(description = "TC ID TRE038 Collections premium require checking.")
    public void collectionsPremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.tryToActivateCollections();
    }

    @Test(description = "TC ID TRE039 Try Premium module is displaying and clickable.")
    public void tryPremiumFirstModuleTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.tryToUpgradeUserFirstModule();
    }

    @Test(description = "TC ID TRE040 Archived items manipulations checking.")
    public void archivedItemsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        int expectedCardListCountResult = cardListPage.countCardsInBeginning();
        cardListPage.addSingleCardToArchive()
                .addAllCardsToArchive()
                .createFewCards();
        expectedCardListCountResult += cardListPage.countCardsInBeginning();
        cardListPage.addListOfCardsToArchive();
        rightNavigationDrawer
                .returnAllCardsFromArchive()
                .returnListOfCardsFromArchive();
        Thread.sleep(3000);
        int actualCardListCountResult = cardListPage.countCardsInEnd();
        Assert.assertEquals(actualCardListCountResult, expectedCardListCountResult);
    }

    @Test(description = "TC ID TRE041 Add cards via email.")
    public void addCardsViaEmailTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        cardListPage.createFewCards();
        String cardTitle = cardListPage.rememberCardTitle();
        rightNavigationDrawer.generateMailAddressByTrello();
        String mailtoGeneratedByTrello = rightNavigationDrawer.getTrelloGeneratedMail();
        String bodyText = "Nice to see you @useruser18578591 #green";
        tempEmailSender.sendEmail(mailtoGeneratedByTrello, cardTitle, bodyText);
        System.out.println("Sending mail 30 sec...");
        Thread.sleep(30000);
        System.out.println("Mail has been sent...");
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        fullscreenCardModePage
                .openSecondActiveCard()
                .isDescriptionContainsExpectedText(bodyText);
    }

    @Test(description = "TC ID TRE042 Watch button and email notification testing.")
    public void watchAndEmailNotificationTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.startWatchBoard();
        cardListPage.startWatchList()
                .stopWatchBoard()
                .stopwatchList();
    }

    @Test(description = "TC ID TRE043 Make Template Premium required checking.")
    public void makeTemplatePremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToActivateTemplate();
    }

    @Test(description = "TC ID TRE044 Copy Board function testing.")
    public void copyBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyBoard();
        CommonActions.closeAllVisibleBoards(boardsPage.getSecondaryWorkspaceUrl());

    }

    @Test(description = "TC ID TRE045 Print and Export to suggested formats.")
    public void printAndExportTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.isJsonValid();
    }

    @Test(description = "TC ID TRE046 Close Board function testing.")
    public void closeBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    @Test(description = "TC ID TRE047 Link to this board testing.")
    public void linkToThisBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyAndNavigateToBoardLink();
    }

    @Test(description = "TC ID TRE048 QR Code test.")
    public void qrCodeTest() throws InterruptedException, IOException, NotFoundException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyAndNavigateToBoardQR();
    }

    @Test(description = "TC ID TRE049 About this board formatting testing.")
    public void aboutThisBoardFormattingTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.editAboutThisBoardDescription();
    }

    @Test(description = "TC ID TRE050 Commenting on a card testing.")
    public void commentOnCardTest() throws InterruptedException {
        //This menu redirects to the section of TC ID TRE034 Commenting permissions.
    }

    @Test(description = "TC ID TRE051 Change card background testing.")
    public void changeBoardBackgroundTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.changeBoardBackground();
    }

    @Test(description = "TC ID TRE052 Custom fields Premium requiring test.")
    public void customFieldPremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToActivateCustomFields();
    }

    @Test(description = "TC ID TRE053 Try Premium module checking.")
    public void tryPremiumSecondModuleTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToUpgradeUserSecondModule();
    }

    @Test(description = "TC ID TRE054 Stickers module testing.")
    public void stickersTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.selectStickers();
    }

    @Test(description = "TC ID TRE055 Activity history checking.")
    public void activityTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.checkActivityIsInteractive();
    }
}
