package cards.right_navigation_drawer;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.header.CardsHeader;
import pages.cards.right_navigation_draver.RightNavigationDrawer;

import static commons.CommonActions.driver;

public class RightNavigationDrawerTest  extends BaseTest {
    //TC ID TRE032 Change Workspace test.
    @Test (groups={"critical_path"})
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
    public void cardCoverTest() {
    }

    //TC ID TRE034 Commenting permissions.
    @Test
    public void commentingPermissionsTest() {
    }

    //TC ID TRE035 Add, Remove permissions
    @Test
    public void addRemovePermissionsTest() {
    }

    //TC ID TRE036 Allow Workspace members to edit and join.
    @Test (groups={"critical_path"})
    public void allowWorkspaceMembersToEditAndJoinTest() {
    }

    //TC ID TRE037 Labels displaying on cards.
    @Test
    public void labelsDisplayingOnCardsTest() {
    }

    //TC ID TRE038 Collections premium require checking.
    @Test
    public void collectionsPremiumRequireTest() {
    }

    //TC ID TRE039 Try Premium module is displaying and clickable.
    @Test
    public void tryPremiumFirstModuleTest() {
    }

    //TC ID TRE040 Archived items manipulations checking.
    @Test
    public void archivedItemsTest() {
    }

    //TC ID TRE041 Add cards via email.
    @Test
    public void addCardsViaEmailTest() {
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
