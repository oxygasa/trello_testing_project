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
import pages.cards.header.CardsHeader;
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
    private final String EXPECTED_TRIAL_BUTTON_TITLE = "Start 14-day free trial";
    @FindBy(xpath = "//button[@title='Workspaces']")
    private WebElement headerWorkspaceDropdown;
    @FindAll({@FindBy(xpath = "//a[@data-test-id='workspace-switcher-popover-tile']")})
    private List<WebElement> workspacesNameList;
    @FindBy(xpath = "//span[@aria-label='EditIcon']/ancestor::button[1]")
    private WebElement editWorkspaceDetailsButton;
    @FindBy(id = "displayName")
    private WebElement displayNameTextField;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'css-1og2rpm')]")})
    private List<WebElement> teamTypeSelectDropdown;
    @FindBy(id = "name")
    private WebElement shortNameTextField;
    @FindBy(id = "website")
    private WebElement websiteOptionalTextField;
    @FindBy(id = "desc")
    private WebElement descriptionOptionalTextField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@class='Et7qpeaH3d--pj']/p")
    private WebElement savedDescriptionOptionalTitle;

    @FindAll({@FindBy(xpath = "//a[contains(@class, 'tabbed-pane-nav-item-button')]")})
    private List<WebElement> workspaceCentralPageTabs;
    @FindBy(className = "_3yTFDpz8niFoyh")
    private WebElement trialButton;
    @FindBy(xpath = "//button[@data-test-id='header-create-menu-button']")
    private WebElement headerAddWorkspace;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-button']")
    private WebElement headerCreateWorkspaceButton;
    @FindBy(xpath = "//input[@data-test-id='header-create-team-name-input']")
    private WebElement newWorkspaceNameInput;
    @FindBy(xpath = "//div[@data-test-id='header-create-team-type-input']")
    private WebElement workspaceTypeSelectDropdown;
    @FindBy(className = "_3WDCVZHeu3AAvH")
    private WebElement newWorkspaceDescriptionOptional;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-submit-button']")
    private WebElement newWorkspaceSubmitButton;
    @FindBy(xpath = "//input[@data-test-id='add-members-input']")
    private WebElement inviteTeamViaEmailInput;
    @FindBy(xpath = "//button[@data-test-id='team-invite-submit-button']")
    private WebElement inviteTeamSubmitButton;
    @FindBy(xpath = "//a[@data-test-id='team-members-link']")
    private WebElement peopleCounter;
    @FindBy(xpath = "//a[@data-tab='members']")
    private WebElement workspaceMembersTab;
    @FindBy(xpath = "//a[@data-tab='settings']")
    private WebElement workspaceSettingsTab;
    @FindBy(xpath = "//button[@data-test-id='delete-workspace-button']")
    private WebElement deleteWorkspaceLink;
    @FindBy(xpath = "//h1[@class='_2bvXXmbrsPh8Ck']")
    private WebElement newCreatedWorkspaceTitleName;
    @FindBy(xpath = "//input[@data-test-id='delete-workspace-confirm-field']")
    private WebElement confirmDeletionWorkspaceNameInput;
    @FindBy(xpath = "//button[@data-test-id='delete-workspace-confirm-button']")
    private WebElement confirmDeleteWorkspaceButton;
    @FindBy(xpath = "//a[@data-test-id='show-later-button']")
    private WebElement inviteIDoItLaterLink;

    public String getExpectedTrialButtonTitle() {
        return EXPECTED_TRIAL_BUTTON_TITLE;
    }

    /*** CRUD manipulations with Workspaces***/
    public WorkspaceListPage createNewWorkspace() throws InterruptedException {
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(headerAddWorkspace);
        headerAddWorkspace.click();
        headerCreateWorkspaceButton.click();
        String newWorkspaceInputText = RandomStringUtils.randomAlphanumeric(10);
        newWorkspaceNameInput.sendKeys(newWorkspaceInputText);
        CommonActions.selectDropdownMenuNextValue(workspaceTypeSelectDropdown);
        String newWorkspaceDescriptionText = RandomStringUtils.randomAlphanumeric(10);
        newWorkspaceDescriptionOptional.sendKeys(newWorkspaceDescriptionText);
        newWorkspaceSubmitButton.click();
        inviteIDoItLaterLink.click();
        return this;
    }

    public WorkspaceListPage createNewWorkspaceHeadless() throws InterruptedException {
        Thread.sleep(2000);
        String newWorkspaceInputText = RandomStringUtils.randomAlphanumeric(10);
        newWorkspaceNameInput.sendKeys(newWorkspaceInputText);
        CommonActions.selectDropdownMenuNextValue(workspaceTypeSelectDropdown);
        String newWorkspaceDescriptionText = RandomStringUtils.randomAlphanumeric(10);
        newWorkspaceDescriptionOptional.sendKeys(newWorkspaceDescriptionText);
        newWorkspaceSubmitButton.click();
        inviteIDoItLaterLink.click();
        return this;
    }

    public WorkspaceListPage editExistingWorkspaceAndAssertChanges() throws InterruptedException {
        displayNameTextField.clear();
        String displayNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        displayNameTextField.sendKeys(displayNameTextFieldInputText);
        CommonActions.selectDropdownMenuNextValue(teamTypeSelectDropdown.get(0));
        shortNameTextField.clear();
        String shortNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        shortNameTextField.sendKeys(shortNameTextFieldInputText);
        websiteOptionalTextField.clear();
        String websiteOptionalTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        websiteOptionalTextField.sendKeys(websiteOptionalTextFieldInputText);
        descriptionOptionalTextField.clear();
        String descriptionOptionalTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        descriptionOptionalTextField.sendKeys(descriptionOptionalTextFieldInputText);
        saveButton.submit();
        /*** Expected Results: all changes are saved. **/

        return this;
    }

    public WorkspaceListPage deleteWorkspace() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(workspaceSettingsTab);
        String getNewWorkspaceTitleName = newCreatedWorkspaceTitleName.getText();
        workspaceSettingsTab.click();
        CommonActions.explicitWaitOfOneElementVisible(deleteWorkspaceLink);
        deleteWorkspaceLink.click();
        confirmDeletionWorkspaceNameInput.sendKeys(getNewWorkspaceTitleName);
        confirmDeleteWorkspaceButton.click();
        return this;
    }

    /*** Methods for shorting steps ***/
    public WorkspaceListPage navigateToEditWorkspace() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(editWorkspaceDetailsButton);
        editWorkspaceDetailsButton.click();
        return this;
    }

    public WorkspaceListPage navigateToTableTab() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(workspaceCentralPageTabs.get(1));
        workspaceCentralPageTabs.get(1).click();
        return this;
    }

    public WorkspaceListPage assertTableTrialAsk() {
        String trialButtonActualTitle = trialButton.getText();
        Assert.assertEquals(trialButtonActualTitle, getExpectedTrialButtonTitle());
        return this;
    }

    public WorkspaceListPage assertLeftNaviDrawerTrialAsk() {
        String trialButtonActualTitle = trialButton.getText();
        /*** Expected Results: Premium account required message is displayed. **/
        Assert.assertEquals(trialButtonActualTitle, getExpectedTrialButtonTitle());
        return this;
    }
}