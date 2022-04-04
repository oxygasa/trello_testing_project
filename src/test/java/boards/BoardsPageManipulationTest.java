package boards;

import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;

import java.util.List;

import static commons.CommonActions.driver;

public class BoardsPageManipulationTest {

    //TC ID TRE020 Boards page: Recently viewed.
    @Test
    public static void recentlyViewedTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. Create 4 boards (and remember their names). **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        List<String> expectedBoardNamesListener = BoardsPage.createCollectionOfFourExpectedBoards();
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Expected List" + expectedBoardNamesListener);
        /*** Check, the /boards page contains the same boards.**/
        List<String> actualResultBoardNames = BoardsPage.saveBoardPageRecentBoardListToCollection();
        System.out.println("Actual result " + actualResultBoardNames);
        Assert.assertEquals(actualResultBoardNames, expectedBoardNamesListener);
        /*** Post condition: close all visible boards **/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }

    //TC ID TRE021 Boards page: Your workspaces.
    @Test
    public static void yourWorkspacesTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. Create 4 boards (and remember their names). **/
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        List<String> expectedBoardNamesListener = BoardsPage.createCollectionOfFourExpectedBoards();
        expectedBoardNamesListener.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Expected List" + expectedBoardNamesListener);
        /*** Check, the /boards page contains the same boards.**/
        List<String> actualResultBoardNames = BoardsPage.saveBoardPageWorkspaceBoardListToCollection();
        System.out.println("Actual result " + actualResultBoardNames.get(0));
        Assert.assertEquals(actualResultBoardNames.get(0), expectedBoardNamesListener.get(0));
        /*** Post condition: close all visible boards **/
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }
}
