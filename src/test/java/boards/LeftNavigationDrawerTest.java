package boards;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;

import static commons.CommonActions.driver;

public class LeftNavigationDrawerTest extends BaseTest {

    //TC ID TRE019 Left Navigation Drawer: Add, Favourite, close boards.
    @Test
    public void addAndCloseBoardsTest() throws InterruptedException {
        /*** Precondition: login, close all visible boards. **/
        PageFactory.initElements(driver, LeftNavigationDrawer.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.closeAllVisibleBoards(boardsPage.getDefaultWorkspaceUrl());
        /*** On the any Workspaces Left Navigation Drawer create a board with a random title. **/
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        LeftNavigationDrawer.createBoardInstance();
        /*** Close the boards from Left Navigation Drawer. **/
        LeftNavigationDrawer.closeAllVisibleBoardsViaLeftNaviDrawer();
        }


    //TC ID TRE022 Left Navigation Drawer: Duplicating boards' functionality.
    @Test
    public void duplicatingBoardsFunctionalityTest() throws InterruptedException {
        /*** Precondition: login, navigate to boards page. **/
        PageFactory.initElements(driver, LeftNavigationDrawer.class);
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl() + "/boards");
        /*** Open recent pages and check their presence. **/
        LeftNavigationDrawer.navigationToBoardsTemplatesHomeWorkspaces.get(0).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/templates");
        LeftNavigationDrawer.navigationToBoardsTemplatesHomeWorkspaces.get(1).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/");
    }
}
