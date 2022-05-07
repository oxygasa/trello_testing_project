package cards.card_list_preview_page;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.cards.card_list.CardListPage;

import static commons.CommonActions.driver;

public class CardListPageTest extends BaseTest {
    BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
    CardListPage cardListPage = PageFactory.initElements(driver, CardListPage.class);
    //TC ID TRE056 Add another list
    @Test (groups={"smoke", "critical_path"})
    public void dragListsAndCardsTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.moveCards()
                    .moveLists();
    }

    //TC ID TRE057 Checking the list filling by cards.
    @Test (groups={"smoke", "critical_path"})
    public void addCardTest() throws InterruptedException {
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        CommonActions.createOneRandomBoardInstance(boardsPage.getDefaultWorkspaceUrl());
        boardsPage.openFirstExistingBoard();
        cardListPage.createFewCards();
        ///// Finish ideas for this test later
    }

    //TC ID TRE058 Dropright menu editing functions test.
    @Test (groups={"smoke", "critical_path"})
    public void droprightMenuTest() {
    }

    //TC ID TRE059 Card template test.
    @Test (groups={"smoke", "critical_path"})
    public void cardTemplateTest() {
    }
}
