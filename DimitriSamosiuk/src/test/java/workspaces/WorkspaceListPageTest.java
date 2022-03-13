package workspaces;

import commons.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
        WorkspaceListPage.displayNameTextField.sendKeys(RandomStringUtils.randomAlphanumeric(10));

        /**
         * 6. Select random Workspace Type. [DROPDOWN IS DEVELOPED AS "INPUT", NOT AS "SELECT"]
         **/
      //  WorkspaceListPage.teamTypeSelectDropdown.click();
      //  WorkspaceListPage.teamTypeSelectDropdownOption1.click(); //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".


        /**
         * 7. Type random text in the text boxes Name, Website (optional), Description (optional).
         * 8. Type random text in the Shortname text field.
         * 9. Click the Save button.
         **/
        WorkspaceListPage.shortNameTextField.sendKeys(RandomStringUtils.randomAlphanumeric(10));
        WorkspaceListPage.websiteOptionalTextField.sendKeys(RandomStringUtils.randomAlphanumeric(10));
        WorkspaceListPage.descriptionOptionalTextField.sendKeys(RandomStringUtils.randomAlphanumeric(10));
        WorkspaceListPage.saveButton.submit();
    }

    //TC ID TRE011 Workspace page: Workspace page: Workspace table Premium require
    @Test
    public static void workspaceTableTabPremiumRequireTest(){}

    //TC ID TRE012 Left Navigation Drawer: Workspace table Premium require checking
    @Test
    public static void workspaceTableLeftNavigationDrawerPremiumRequireTest(){}

    //TC ID TRE013 Left Navigation Drawer on Boards: Create new Workspace.
    @Test
    public static void createNewWorkspaceFromLeftNavigationDrawerOnBoards(){}


}
