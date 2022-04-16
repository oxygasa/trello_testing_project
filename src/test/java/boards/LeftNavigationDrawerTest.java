package boards;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;
import static commons.CommonActions.driver;

public class LeftNavigationDrawerTest extends BaseTest {

    //TC ID TRE019 Left Navigation Drawer: Add, Favourite, close boards.
    @Test
    public void CRUDBoardsFromLeftNaviDrawerTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        LeftNavigationDrawer leftNavigationDrawer = PageFactory.initElements(driver, LeftNavigationDrawer.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        /*** On the any Workspaces Left Navigation Drawer create a board with a random title. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        leftNavigationDrawer
                .expandLeftNaviDrawer()
                .createBoardInstance();
        /*** Close the boards from Left Navigation Drawer. **/
        leftNavigationDrawer.closeAllVisibleBoardsViaLeftNaviDrawer();
        }
}
