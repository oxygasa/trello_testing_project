package workspaces;

import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.register.TempMail;
import pages.workspaces.WorkspaceListPage;
import org.apache.commons.lang3.RandomStringUtils;

import static commons.CommonActions.driver;

public class WorkspaceListPageTest {

    //TC ID TRE010 Workspace page: change the information about.
    @Test
    public static void changeInformationAboutWorkspaceTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials.
         * 2. Select the Workspace on a header.
         * 3. Select Your Workspace.
         * 4. Click the Edit Workspace Details button.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.editWorkspaceDetailsButton);
        WorkspaceListPage.editWorkspaceDetailsButton.click();
        /**
         * Steps to Reproduce
         * 5. Type random text in the Display name text box.
         **/
        WorkspaceListPage.displayNameTextField.clear();
        String displayNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.displayNameTextField.sendKeys(displayNameTextFieldInputText);

        /**
         * Steps to Reproduce
         * 6. Select random Workspace Type. [DROPDOWN IS DEVELOPED AS "INPUT", NOT AS "SELECT"]
         **/
        CommonActions.selectDropdownMenuNextValue(WorkspaceListPage.teamTypeSelectDropdown.get(0));

        /**
         * Steps to Reproduce
         * 7. Type random text in the text boxes Name, Website (optional), Description (optional).
         * 8. Type random text in the Shortname text field.
         * 9. Click the Save button.
         **/
        WorkspaceListPage.shortNameTextField.clear();
        String shortNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.shortNameTextField.sendKeys(shortNameTextFieldInputText);
        WorkspaceListPage.websiteOptionalTextField.clear();
        String websiteOptionalTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.websiteOptionalTextField.sendKeys(websiteOptionalTextFieldInputText);
        WorkspaceListPage.descriptionOptionalTextField.clear();
        String descriptionOptionalTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.descriptionOptionalTextField.sendKeys(descriptionOptionalTextFieldInputText);
        WorkspaceListPage.saveButton.submit();
        /**
         * Expected Results
         * 9. All changes are saved.
         **/
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.savedWebsiteOptionalTitle);
        Thread.sleep(2000);
        String resultTextOfDisplayNameTitle = WorkspaceListPage.savedDisplayNameTitle.getText();
        String resultTextOfDescriptionOptionalTitle = WorkspaceListPage.savedDescriptionOptionalTitle.getText();
        String resultTextOfWebsiteOptionalTitle = WorkspaceListPage.savedWebsiteOptionalTitle.getText();
        Assert.assertEquals(resultTextOfDisplayNameTitle, displayNameTextFieldInputText);
        Assert.assertEquals(resultTextOfDescriptionOptionalTitle, descriptionOptionalTextFieldInputText);
        Assert.assertEquals(resultTextOfWebsiteOptionalTitle, "http://" + websiteOptionalTextFieldInputText);
        /**
         * Post condition
         * 1. Delete the workspace.
         **/
        WorkspaceListPage.deleteWorkspace();
    }

    //TC ID TRE011 Workspace page: Workspace page: Workspace table Premium require
    @Test
    public static void workspaceTableTabPremiumRequireTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials.
         * 2. Select the Workspace on a header.
         * 3. Select Your Workspace.
         * 4. Select the Workspace table tab.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.workspaceCentralPageTabs.get(1));
        WorkspaceListPage.workspaceCentralPageTabs.get(1).click();
        /**
         * Expected Results
         * 4. Premium account required message is displayed.
         **/
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
        /**
         * Post condition
         * 1. Delete the workspace.
         **/
        driver.get(currentWorkspaceUrl);
        WorkspaceListPage.deleteWorkspace();
    }

    //TC ID TRE012 Left Navigation Drawer: Workspace table Premium require checking
    @Test
    public static void workspaceTableLeftNavigationDrawerPremiumRequireTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 3. Select Your Workspace.
         * 4. Expand Left Navigation Drawer then click Workspace table button.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.createNewWorkspace();
        String currentWorkspaceUrl = driver.getCurrentUrl();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.leftNavigationDrawerExpander);
        WorkspaceListPage.leftNavigationDrawerExpander.click();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.leftNavigationDrawerTableButton);
        WorkspaceListPage.leftNavigationDrawerTableButton.click();
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        /**
         * Expected Results
         * 4. Premium account required message is displayed.
         **/
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
        /**
         * Post condition
         * 1. Delete the workspace.
         **/
        driver.get(currentWorkspaceUrl);
        WorkspaceListPage.deleteWorkspace();
    }


    //TC ID TRE013 Left Navigation Drawer on Boards: Create new Workspace.
    @Test
    public static void createNewWorkspaceFromLeftNavigationDrawerOnBoards() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials then open Boards page.
         * 2. On the Left Navigation Drawer on Workspace section click "+" button.
         * 3. Type Random symbols to a Workspace name and Workspace description. Select random Workspace type.
         * (Blocker:A dropdown menu list locators are in a hidden html (React-select). Can't be seen by xpath).
         * 4. Invite random user using temp mail service.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, TempMail.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.createNewWorkspaceWithinInvite();
        WorkspaceListPage.confirmInviteNewMemberViaTeamEmail();
        CommonActions.getBackToThePreviousTab();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.workspaceMembersTab);
        WorkspaceListPage.workspaceMembersTab.click();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.peopleCounter);
        /**
         * Expected result
         * 5. A workspace is created. 2 members are displayed.
         **/
        Assert.assertEquals(WorkspaceListPage.peopleCounter.getText(),"Workspace members (2)");
        /**
         * Post condition
         * 1. Delete the workspace.
         **/
        WorkspaceListPage.deleteWorkspace();
    }
}
