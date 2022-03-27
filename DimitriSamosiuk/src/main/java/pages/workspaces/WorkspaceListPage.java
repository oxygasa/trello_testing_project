package pages.workspaces;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.BasePage;
import pages.register.TempMail;

import java.util.List;

import static commons.CommonActions.driver;

public class WorkspaceListPage extends BasePage {
    WebDriver driver;
    public WorkspaceListPage(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * Web elements
     **/
    public static final String expectedTrialButtonTitle = "Start 14-day free trial";
    @FindBy(xpath = "//button[@title='Workspaces']")
    public static WebElement headerWorkspaceDropdown;
    @FindAll({@FindBy(xpath = "//a[@data-test-id='workspace-switcher-popover-tile']")})
    public static List<WebElement> workspacesNameList;
    @FindBy(xpath = "//span[@aria-label='EditIcon']/ancestor::button[1]")
    public static WebElement editWorkspaceDetailsButton;
    @FindBy(id = "displayName")
    public static WebElement displayNameTextField;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'css-1og2rpm')]")})
    public static List<WebElement> teamTypeSelectDropdown;
    @FindBy(id = "name")
    public static WebElement shortNameTextField;
    @FindBy(id = "website")
    public static WebElement websiteOptionalTextField;
    @FindBy(id = "desc")
    public static WebElement descriptionOptionalTextField;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement saveButton;
    @FindBy(className = "_2bvXXmbrsPh8Ck")
    public static WebElement savedDisplayNameTitle;
    @FindBy(xpath = "//div[@class='Et7qpeaH3d--pj']/p")
    public static WebElement savedDescriptionOptionalTitle;
    @FindBy(xpath = "//p/a")
    public static WebElement savedWebsiteOptionalTitle;
    @FindAll({@FindBy(xpath = "//a[contains(@class, 'tabbed-pane-nav-item-button')]")})
    public static List<WebElement> workspaceCentralPageTabs;
    @FindBy(className = "_3yTFDpz8niFoyh")
    public static WebElement trialButton;
    @FindBy(xpath = "//button[@data-test-id='header-create-menu-button']")
    public static WebElement headerAddWorkspace;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-button']")
    public static WebElement headerCreateWorkspaceButton;
    @FindBy(xpath = "//input[@data-test-id='header-create-team-name-input']")
    public static WebElement newWorkspaceNameInput;
    @FindBy(xpath = "//div[@data-test-id='header-create-team-type-input']")
    public static WebElement workspaceTypeSelectDropdown;
    @FindBy(className = "_3WDCVZHeu3AAvH")
    public static WebElement newWorkspaceDescriptionOptional;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-submit-button']")
    public static WebElement newWorkspaceSubmitButton;
    @FindBy(xpath = "//input[@data-test-id='add-members-input']")
    public static WebElement inviteTeamViaEmailInput;
    @FindBy(xpath = "//button[@data-test-id='team-invite-submit-button']")
    public static WebElement inviteTeamSubmitButton;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public static WebElement addBoardFromLeftNavigationDrawer;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-expand-button']")
    public static WebElement workspaceNavigationExpandButton;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-collapse-button']")
    public static WebElement workspaceNavigationCollapseButton;
    @FindBy(xpath = "//a[@data-test-id='team-members-link']")
    public static WebElement peopleCounter;
    @FindBy(xpath = "//a[@data-tab='members']")
    public static WebElement workspaceMembersTab;
    @FindBy(xpath = "//a[@data-tab='settings']")
    public static WebElement workspaceSettingsTab;
    @FindBy(xpath = "//button[@data-test-id='delete-workspace-button']")
    public static WebElement deleteWorkspaceLink;
    @FindBy (xpath = "//h1[@class='_2bvXXmbrsPh8Ck']")
    public static WebElement newCreatedWorkspaceTitleName;
    @FindBy (xpath = "//input[@data-test-id='delete-workspace-confirm-field']")
    public static WebElement confirmDeletionWorkspaceNameInput;
    @FindBy (xpath = "//button[@data-test-id='delete-workspace-confirm-button']")
    public static WebElement confirmDeleteWorkspaceButton;
    @FindBy(xpath = "//a[@data-test-id='show-later-button']")
    public static WebElement inviteIDoItLaterLink;

    /*** There are methods to make test steps code shorter **/
    public static void createNewWorkspaceWithinInvite() throws InterruptedException {
        WorkspaceListPage.headerAddWorkspace.click();
        WorkspaceListPage.headerCreateWorkspaceButton.click();
        String newWorkspaceInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.newWorkspaceNameInput.sendKeys(newWorkspaceInputText);
        CommonActions.selectDropdownMenuNextValue(WorkspaceListPage.workspaceTypeSelectDropdown);
        String newWorkspaceDescriptionText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.newWorkspaceDescriptionOptional.sendKeys(newWorkspaceDescriptionText);
        WorkspaceListPage.newWorkspaceSubmitButton.click();
        CommonActions.openUrlInNewBrowserTab(TempMail.TEMP_MAIL_PAGE_URL);
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(TempMail.randomTempEmail);
        String randomTempEmailValue = TempMail.randomTempEmail.getAttribute("title");
        CommonActions.getBackToThePreviousTab();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.inviteTeamViaEmailInput);
        WorkspaceListPage.inviteTeamViaEmailInput.click();
        WorkspaceListPage.inviteTeamViaEmailInput.sendKeys(randomTempEmailValue);
        Actions action = new Actions(CommonActions.driver);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
        WorkspaceListPage.inviteTeamSubmitButton.click();
        CommonActions.getBackToThePreviousTab();
    }

    public static void createNewWorkspace() throws InterruptedException {
        WorkspaceListPage.headerAddWorkspace.click();
        WorkspaceListPage.headerCreateWorkspaceButton.click();
        String newWorkspaceInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.newWorkspaceNameInput.sendKeys(newWorkspaceInputText);
        CommonActions.selectDropdownMenuNextValue(WorkspaceListPage.workspaceTypeSelectDropdown);
        String newWorkspaceDescriptionText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.newWorkspaceDescriptionOptional.sendKeys(newWorkspaceDescriptionText);
        WorkspaceListPage.newWorkspaceSubmitButton.click();
        WorkspaceListPage.inviteIDoItLaterLink.click();
    }
    public static void confirmInviteNewMemberViaTeamEmail() throws InterruptedException {
        Thread.sleep(10000);
        CommonActions.driver.navigate().refresh();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(TempMail.incomeBoxMailListButtons.get(0));
        TempMail.incomeBoxMailListButtons.get(0).click();
        TempMail.joinWorkspaceList.click();
    }
    public static void editExistingWorkspaceAndAssertChanges() throws InterruptedException {
        WorkspaceListPage.displayNameTextField.clear();
        String displayNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        WorkspaceListPage.displayNameTextField.sendKeys(displayNameTextFieldInputText);
        CommonActions.selectDropdownMenuNextValue(WorkspaceListPage.teamTypeSelectDropdown.get(0));
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
        /*** Expected Results: all changes are saved. **/
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.savedWebsiteOptionalTitle);
        Thread.sleep(2000);
        String resultTextOfDisplayNameTitle = WorkspaceListPage.savedDisplayNameTitle.getText();
        String resultTextOfDescriptionOptionalTitle = WorkspaceListPage.savedDescriptionOptionalTitle.getText();
        String resultTextOfWebsiteOptionalTitle = WorkspaceListPage.savedWebsiteOptionalTitle.getText();
        Assert.assertEquals(resultTextOfDisplayNameTitle, displayNameTextFieldInputText);
        Assert.assertEquals(resultTextOfDescriptionOptionalTitle, descriptionOptionalTextFieldInputText);
        Assert.assertEquals(resultTextOfWebsiteOptionalTitle, "http://" + websiteOptionalTextFieldInputText);
    }

    public static void deleteWorkspace() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.workspaceSettingsTab);
        String getNewWorkspaceTitleName = WorkspaceListPage.newCreatedWorkspaceTitleName.getText();
        WorkspaceListPage.workspaceSettingsTab.click();
        CommonActions.explicitWaitOfOneElementVisible(WorkspaceListPage.deleteWorkspaceLink);
        WorkspaceListPage.deleteWorkspaceLink.click();
        WorkspaceListPage.confirmDeletionWorkspaceNameInput.sendKeys(getNewWorkspaceTitleName);
        WorkspaceListPage.confirmDeleteWorkspaceButton.click();
    }
}