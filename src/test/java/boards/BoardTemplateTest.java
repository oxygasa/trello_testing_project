package boards;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;
import pages.boards.TemplatePage;
import pages.cards.header.CardsHeader;

import static commons.CommonActions.driver;
public class BoardTemplateTest  extends BaseTest {
    TemplatePage templatePage = new TemplatePage(driver);
    CardsHeader cardsHeader = new CardsHeader(driver);
    BoardsPage boardsPage = new BoardsPage(driver);

    //TC ID TRE022 Make a board from Template.
    @Test (groups={"critical_path"})
    public void makeBoardFromTemplateTest() throws InterruptedException {
        /*** Precondition: login, navigate to template page page. **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        /*** Create a Kanban board **/
        templatePage.searchAndConnectKanbanBoard();
        cardsHeader.checkTheBoardNameIsExpected(templatePage.expectedBoardName);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }
}
