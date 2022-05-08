package cards.card_list_preview_page;

import base.BaseTest;
import commons.CommonActions;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.card_list.CardListPage;

import static commons.CommonActions.driver;

public class CardListPageTest extends BaseTest {
    BoardsPage boardsPage = new BoardsPage(driver);
    CardListPage cardListPage = new CardListPage(driver);

    @Test(description = "TC ID TRE056 Add another list", groups = {"smoke", "critical_path"})
    public void dragListsAndCardsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.moveCards()
                .moveLists();
    }

    @Test(description = "TC ID TRE057 Checking the list filling by cards.",
            groups = {"smoke", "critical_path"})
    public void addCardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        ///// Finish ideas for this test later
    }

    @Test(description = "TC ID TRE058 Dropright menu editing functions test.",
            groups = {"smoke", "critical_path"})
    public void droprightMenuTest() {
    }

    @Test(description = "TC ID TRE059 Card template test.",
            groups = {"smoke", "critical_path"})
    public void cardTemplateTest() {
    }
}
