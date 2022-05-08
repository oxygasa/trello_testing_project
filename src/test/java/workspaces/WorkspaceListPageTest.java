package workspaces;

import base.BaseTest;
import commons.FlakingTestOneChanceToPass;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;
import pages.workspaces.WorkspaceListPage;

import static commons.CommonActions.driver;

public class WorkspaceListPageTest extends BaseTest {

    WorkspaceListPage workspaceListPage = new WorkspaceListPage(driver);
    LeftNavigationDrawer leftNavigationDrawer = new LeftNavigationDrawer(driver);
    BoardsPage boardsPage = new BoardsPage(driver);


    @Test(description = "ID TRE010 Workspace page: change the information about.",
            retryAnalyzer = FlakingTestOneChanceToPass.class,
            groups = {"smoke", "critical_path"})
    public void changeInformationAboutWorkspaceTest() throws InterruptedException {
        /*** Create, change workspace description field values. **/
        workspaceListPage
                .createNewWorkspace()
                .navigateToEditWorkspace()
                .editExistingWorkspaceAndAssertChanges()
                .deleteWorkspace();
    }

    @Test(description = "TC ID TRE011 Workspace page: Workspace page: Workspace table Premium require")
    public void workspaceTableTabPremiumRequireTest() throws InterruptedException {
        /*** Create new Workspace and navigate to the Workspace table tab.**/
        workspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        driver.navigate().refresh();
        Thread.sleep(5000);
        workspaceListPage
                .navigateToTableTab()
                .assertTableTrialAsk();
        /*** Post condition: Delete the workspace. **/
        driver.get(currentWorkspaceUrl);
        workspaceListPage.deleteWorkspace();
    }


    @Test(description = "TC ID TRE012 Left Navigation Drawer: Workspace table Premium require checking",
            retryAnalyzer = FlakingTestOneChanceToPass.class)
    public void workspaceTableLeftNavigationDrawerPremiumRequireTest() throws InterruptedException {
        workspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        driver.navigate().refresh();
        /*** Expand Left Navigation Drawer then click Workspace table button. **/
        leftNavigationDrawer
                .expandLeftNaviDrawer()
                .selectWorkspaceTable();
        workspaceListPage.assertLeftNaviDrawerTrialAsk();
        /*** Post condition: Delete the workspace. **/
        driver.get(currentWorkspaceUrl);
        workspaceListPage.deleteWorkspace();
    }


    @Test(description = "TC ID TRE013 Left Navigation Drawer on Boards: Create new Workspace.",
            retryAnalyzer = FlakingTestOneChanceToPass.class, groups = {"critical_path"})
    public void createNewWorkspaceFromLeftNavigationDrawerOnBoardsTest() throws InterruptedException {
        /*** Create the workspace from the board page.**/
        driver.get(boardsPage.getDefaultWorkspaceUrl() + "/boards");
        boardsPage.createWorkspaceFromLeftNaviDrawer();
        workspaceListPage.createNewWorkspaceHeadless();
        workspaceListPage.deleteWorkspace();
    }
}
