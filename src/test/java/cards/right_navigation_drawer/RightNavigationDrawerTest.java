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
    BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
    RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
    CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
    CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
    FullscreenCardModePage fullscreenCardModePage = PageFactory.initElements(driver, FullscreenCardModePage.class);
    TempEmailSender tempEmailSender = PageFactory.initElements(driver, TempEmailSender.class);

    //TC ID TRE032 Change Workspace test.
    @Test(groups = {"critical_path"})
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

    //TC ID TRE033 Card cover test.
    @Test
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

    //TC ID TRE034 Commenting permissions.
    @Test
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

    //TC ID TRE035 Add, Remove permissions
    @Test
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

    //TC ID TRE036 Disallow Workspace members to edit and join.
    @Test(groups = {"critical_path"})
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

    //TC ID TRE037 Labels displaying on cards.
    @Test
    public void labelsDisplayingOnCardsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards()
                .addLabelWithText();
    }

    //TC ID TRE038 Collections premium require checking.
    @Test
    public void collectionsPremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.tryToActivateCollections();
    }

    //TC ID TRE039 Try Premium module is displaying and clickable.
    @Test
    public void tryPremiumFirstModuleTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.tryToUpgradeUserFirstModule();
    }

    //TC ID TRE040 Archived items manipulations checking.
    @Test
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

    //TC ID TRE041 Add cards via email.
    @Test
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
        tempEmailSender.sendEmail(mailtoGeneratedByTrello,cardTitle,bodyText);
        System.out.println("Sending mail 30 sec...");
        Thread.sleep(30000);
        System.out.println("Mail has been sent...");
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        fullscreenCardModePage
                .openSecondActiveCard()
                .isDescriptionContainsExpectedText(bodyText);
    }

    //TC ID TRE042 Watch button and email notification testing.
    @Test
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

    //TC ID TRE043 Make Template Premium required checking.
    @Test
    public void makeTemplatePremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToActivateTemplate();
    }

    //TC ID TRE044 Copy Board function testing.
    @Test
    public void copyBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyBoard();
        CommonActions.closeAllVisibleBoards(boardsPage.getSecondaryWorkspaceUrl());

    }

    //TC ID TRE045 Print and Export to suggested formats.
    @Test
    public void printAndExportTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.isJsonValid();
    }

    //TC ID TRE046 Close Board function testing.
    @Test
    public void closeBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }

    //TC ID TRE047 Link to this board testing.
    @Test
    public void linkToThisBoardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyAndNavigateToBoardLink();
    }

    //TC ID TRE048 QR Code test.
    @Test
    public void qrCodeTest() throws InterruptedException, IOException, NotFoundException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.copyAndNavigateToBoardQR();
    }


    //TC ID TRE049 About this board formatting testing.
    @Test
    public void aboutThisBoardFormattingTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.editAboutThisBoardDescription();
    }

    //TC ID TRE050 Commenting on a card testing.
    @Test
    public void commentOnCardTest() throws InterruptedException {
    //This menu redirects to the section of TC ID TRE034 Commenting permissions.
    }

    //TC ID TRE051 Change card background testing.
    @Test
    public void changeBoardBackgroundTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.changeBoardBackground();
    }

    //TC ID TRE052 Custom fields Premium requiring test.
    @Test
    public void customFieldPremiumRequireTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToActivateCustomFields();
    }

    //TC ID TRE053 Try Premium module checking.
    @Test
    public void tryPremiumSecondModuleTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        rightNavigationDrawer.tryToUpgradeUserSecondModule();
    }

    //TC ID TRE054 Stickers module testing.
    @Test
    public void stickersTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.selectStickers();
    }

    //TC ID TRE055 Activity history checking.
    @Test
    public void activityTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.checkActivityIsInteractive();
    }
}
