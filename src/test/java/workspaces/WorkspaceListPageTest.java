package workspaces;

import base.BaseTest;
import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawer;
import pages.register.TempMail;
import pages.workspaces.WorkspaceListPage;

import static commons.CommonActions.driver;

public class WorkspaceListPageTest extends BaseTest {

    //TC ID TRE010 Workspace page: change the information about.
    @Test
    public static void changeInformationAboutWorkspaceTest() throws InterruptedException {
        /*** Precondition: login **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        /*** Navigate to Edit Workspace Details button. **/
        WorkspaceListPage.createNewWorkspace();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.editWorkspaceDetailsButton);
        WorkspaceListPage.editWorkspaceDetailsButton.click();
        /*** Type random values due editing the existing workspace. Assert all changes have been saved. **/
        WorkspaceListPage.editExistingWorkspaceAndAssertChanges();
        /*** Post condition: Delete the workspace. **/
        WorkspaceListPage.deleteWorkspace();
    }

    //TC ID TRE011 Workspace page: Workspace page: Workspace table Premium require
    @Test
    public static void workspaceTableTabPremiumRequireTest() throws InterruptedException {
        /*** Precondition: login**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        /*** Create new Workspace and navigate to the Workspace table tab.**/
        WorkspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.workspaceCentralPageTabs.get(1));
        WorkspaceListPage.workspaceCentralPageTabs.get(1).click();
        /*** Expected Results: premium account required message is displayed.**/
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
        /*** Post condition: Delete the workspace. **/
        driver.get(currentWorkspaceUrl);
        WorkspaceListPage.deleteWorkspace();
    }

    //TC ID TRE012 Left Navigation Drawer: Workspace table Premium require checking
    @Test
    public static void workspaceTableLeftNavigationDrawerPremiumRequireTest() throws InterruptedException {
        /*** Precondition: login and open the workspace**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, LeftNavigationDrawer.class);
        WorkspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        /*** Expand Left Navigation Drawer then click Workspace table button. **/
        CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.expander);
        LeftNavigationDrawer.expander.click();
        CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawer.tableButton);
        LeftNavigationDrawer.tableButton.click();
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        /*** Expected Results: Premium account required message is displayed. **/
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
        /*** Post condition: Delete the workspace. **/
        driver.get(currentWorkspaceUrl);
        WorkspaceListPage.deleteWorkspace();
    }


    //TC ID TRE013 Left Navigation Drawer on Boards: Create new Workspace.
    @Test
    public static void createNewWorkspaceFromLeftNavigationDrawerOnBoardsTest() throws InterruptedException {
        /*** Precondition: login**/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, TempMail.class);
        PageFactory.initElements(driver, BoardsPage.class);
        /*** Create the workspace from the board page.**/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE+"/boards");
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.boardLeftNavigationCreateWorkspace);
        BoardsPage.boardLeftNavigationCreateWorkspace.click();
        WorkspaceListPage.createNewWorkspaceHeadless();
        WorkspaceListPage.deleteWorkspace();
    }
}
