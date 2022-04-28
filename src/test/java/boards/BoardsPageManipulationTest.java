package boards;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;

import java.util.List;

import static commons.CommonActions.driver;

public class BoardsPageManipulationTest extends BaseTest {

    //TC ID TRE020 Boards page: Recently viewed.
    @Test
    public void recentlyViewedBoardIsDisplayingOnBoardsPageTest() {
        ///This TC has been covered by TC TRE021  Boards page: Your workspaces.
    }

    //TC ID TRE021 Boards page: Your workspaces.
    @Test (groups={"smoke", "critical_path"})
    public void boardsDisplayingInYourWorkspacesSectionTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. Create 4 boards (and remember their names). **/
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        List<String> expectedBoardNamesListener = boardsPage.createCollectionOfFiveExpectedBoards();
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Expected List" + expectedBoardNamesListener);
        /*** Check, the /boards page contains the same boards.**/
        List<String> actualResultBoardNames = boardsPage.saveBoardPageRecentBoardListToCollection();
        System.out.println("Actual result " + actualResultBoardNames);
        Assert.assertEquals(actualResultBoardNames, expectedBoardNamesListener);
        /*** Post condition: close all visible boards **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
    }
}
