package workspaces;

import commons.CommonActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(1).click();
        Thread.sleep(2000);
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
        //  WorkspaceListPage.teamTypeSelectDropdown.click();
        //  WorkspaceListPage.teamTypeSelectDropdownOption1.click(); //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".


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
        Thread.sleep(4000);
        String resultTextOfDisplayNameTitle = WorkspaceListPage.savedDisplayNameTitle.getText();
        String resultTextOfDescriptionOptionalTitle = WorkspaceListPage.savedDescriptionOptionalTitle.getText();
        String resultTextOfWebsiteOptionalTitle = WorkspaceListPage.savedWebsiteOptionalTitle.getText();
        Assert.assertEquals(resultTextOfDisplayNameTitle, displayNameTextFieldInputText);
        Assert.assertEquals(resultTextOfDescriptionOptionalTitle, descriptionOptionalTextFieldInputText);
        Assert.assertEquals(resultTextOfWebsiteOptionalTitle, "http://" + websiteOptionalTextFieldInputText);
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
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(1).click();
        Thread.sleep(2000);
        WorkspaceListPage.workspaceCentralPageTabs.get(1).click();
        /**
         * Expected Results
         * 4. Premium account required message is displayed.
         **/
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
    }

    //TC ID TRE012 Left Navigation Drawer: Workspace table Premium require checking
    @Test
    public static void workspaceTableLeftNavigationDrawerPremiumRequireTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials
         * 2. Select the Workspace on a header.
         * 3. Select Your Workspace.
         * 4. Expand Left Navigation Drawer then click Workspace table button.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(1).click();
        Thread.sleep(2000);
        WorkspaceListPage.leftNavigationDrawerTableButton.click();
        String trialButtonActualTitle = WorkspaceListPage.trialButton.getText();
        /**
         * Expected Results
         * 4. Premium account required message is displayed.
         **/
        Assert.assertEquals(trialButtonActualTitle, WorkspaceListPage.expectedTrialButtonTitle);
    }


    //TC ID TRE013 Left Navigation Drawer on Boards: Create new Workspace.
/**
 * BLOCKER IN A STEP 3.
 *
 **/
//    //@Test
//    public static void createNewWorkspaceFromLeftNavigationDrawerOnBoards() throws InterruptedException {
//        /**
//         * Steps to Reproduce
//         * 1. Login to your account within precondition credentials then open Boards page.
//         * 2. On the Left Navigation Drawer on Workspace section click "+" button.
//         * 3. Type Random symbols to a Workspace name and Workspace description. Select random Workspace type.
//         * (Blocker:A dropdown menu list locators are in a hidden html (React-select). Can't be seen by xpath).
//         * 4. Invite random user using temp mail service.
//         **/
//        PageFactory.initElements(driver, WorkspaceListPage.class);
//        PageFactory.initElements(driver, TempMail.class);
//        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
//        WorkspaceListPage.headerAddWorkspace.click();
//        WorkspaceListPage.headerCreateWorkspaceButton.click();
//        String newWorkspaceInputText = RandomStringUtils.randomAlphanumeric(10);
//        WorkspaceListPage.newWorkspaceNameInput.sendKeys(newWorkspaceInputText);
//        //  WorkspaceListPage.workspaceTypeSelectDropdown.click();
//        //  WorkspaceListPage.teamTypeSelectDropdownOption1.click(); //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
//        String newWorkspaceDescriptionText = RandomStringUtils.randomAlphanumeric(10);
//        WorkspaceListPage.newWorkspaceDescriptionOptional.sendKeys(newWorkspaceDescriptionText);
//        WorkspaceListPage.newWorkspaceSubmitButton.click();
//        CommonActions.openUrlInNewBrowserTab(TempMail.TEMP_MAIL_PAGE_URL);
//        Thread.sleep(2000);
//        String randomTempEmailValue = TempMail.randomTempEmail.getAttribute("title");
//        CommonActions.getBackToThePreviousTab();
//        WorkspaceListPage.inviteTeamViaEmailInput.sendKeys(randomTempEmailValue);
//        WorkspaceListPage.inviteTeamSubmitButton.click();
//        CommonActions.getBackToThePreviousTab();
//        Thread.sleep(20000);
//        TempMail.incomeBoxMailListButtons.get(0).click();
//        ///// Describe "link locator in email" here.}
}
