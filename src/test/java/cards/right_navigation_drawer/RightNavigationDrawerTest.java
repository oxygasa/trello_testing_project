package cards.right_navigation_drawer;

import base.BaseTest;
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

import static commons.CommonActions.driver;

public class RightNavigationDrawerTest extends BaseTest {
    //TC ID TRE032 Change Workspace test.
    @Test(groups = {"critical_path"})
    public void changeWorkspaceTest() throws InterruptedException {
        /*** Precondition**/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        FullscreenCardModePage fullscreenCardModePage = PageFactory.initElements(driver, FullscreenCardModePage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        FullscreenCardModePage fullscreenCardModePage = PageFactory.initElements(driver, FullscreenCardModePage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        rightNavigationDrawer.tryToUpgradeUser();
    }

    //TC ID TRE040 Archived items manipulations checking.
    @Test
    public void archivedItemsTest() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
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
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
        cardListPage.createFewCards();
        String cardTitle = cardListPage.rememberCardTitle();
        RightNavigationDrawer rightNavigationDrawer = PageFactory.initElements(driver, RightNavigationDrawer.class);
        rightNavigationDrawer.generateMailAddressByTrello();
        String mailtoGeneratedByTrello = rightNavigationDrawer.getTrelloGeneratedMail();
        String bodyText = "Nice to see you @useruser18578591 #green ";
        TempEmailSender tempEmailSender = PageFactory.initElements(driver, TempEmailSender.class);
        tempEmailSender.sendEmail(mailtoGeneratedByTrello,cardTitle,bodyText);
        System.out.println("Sending mail 20 sec...");
        Thread.sleep(20000);
        System.out.println("Mail has been sent...");
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        FullscreenCardModePage fullscreenCardModePage = PageFactory.initElements(driver, FullscreenCardModePage.class);
        fullscreenCardModePage
                .openSecondActiveCard()
                .isDescriptionContainsExpectedText(bodyText);
    }

    //TC ID TRE042 Watch button and email notification testing.
    @Test
    public void watchAndEmailNotificationTest() {


    }

    //TC ID TRE043 Make Template Premium required checking.
    @Test
    public void makeTemplatePremiumRequireTest() {
    }

    //TC ID TRE044 Copy Board function testing.
    @Test
    public void copyBoardTest() {
    }

    //TC ID TRE045 Print and Export to suggested formats.
    @Test
    public void printAndExportTest() {
    }

    //TC ID TRE046 Close Board function testing.
    @Test
    public void closeBoardTest() {
    }

    //TC ID TRE047 Link to this board testing.
    @Test
    public void linkToThisBoardTest() {
    }

    //TC ID TRE048 QR Code test.
    @Test
    public void qrCodeTest() {
    }

    //TC ID TRE049 About this board formatting testing.
    @Test
    public void aboutThisBoardFormattingTest() {
    }

    //TC ID TRE050 Commenting on a card testing.
    @Test
    public void commentOnCardTest() {
    }

    //TC ID TRE051 Change card background testing.
    @Test
    public void changeCardBackgroundTest() {
    }

    //TC ID TRE052 Custom fields Premium requiring test.
    @Test
    public void customFieldPremiumRequireTest() {
    }

    //TC ID TRE053 Try Premium module checking.
    @Test
    public void tryPremiumSecondModuleTest() {
    }

    //TC ID TRE054 Stickers module testing.
    @Test
    public void stickersTest() {
    }

    //TC ID TRE055 Activity history checking.
    @Test
    public void activityTest() {
    }
}
