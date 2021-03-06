package boards;

import base.BaseTest;
import commons.CommonActions;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;

import static commons.CommonActions.driver;

public class LeftNavigationDrawerTest extends BaseTest {
    LeftNavigationDrawer leftNavigationDrawer = new LeftNavigationDrawer(driver);
    BoardsPage boardsPage = new BoardsPage(driver);

    @Test(description = "TC ID TRE019 Left Navigation Drawer: Add, Favourite, close boards.",
            groups = {"smoke", "critical_path"})
    public void CRUDBoardsFromLeftNaviDrawerTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        /*** On the any Workspaces Left Navigation Drawer create a board with a random title. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        driver.navigate().refresh();
        Thread.sleep(5000);
        leftNavigationDrawer
                .expandLeftNaviDrawer()
                .createBoardInstance();
        /*** Close the boards from Left Navigation Drawer. **/
        leftNavigationDrawer.closeAllVisibleBoardsViaLeftNaviDrawer();
    }
}
