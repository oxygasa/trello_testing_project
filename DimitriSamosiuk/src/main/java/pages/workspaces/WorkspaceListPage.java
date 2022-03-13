package pages.workspaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class WorkspaceListPage extends BasePage {
    public static final String expectedTrialButtonTitle = "Start 14-day free trial";
    @FindBy(xpath = "//button[@title='Workspaces']")
    public static WebElement headerWorkspaceDropdown;
    @FindAll({@FindBy(xpath = "//a[@data-test-id='workspace-switcher-popover-tile']")})
    public static List<WebElement> workspacesNameList;
    @FindBy(xpath = "//span[@aria-label='EditIcon']/ancestor::button[1]")
    public static WebElement editWorkspaceDetailsButton;
    @FindBy(id = "displayName")
    public static WebElement displayNameTextField;
    @FindBy(xpath = "//input[@id='teamTypeSelect']")
    public static WebElement teamTypeSelectDropdown;
    @FindBy(id = "react-select-3-option-7") //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    public static WebElement teamTypeSelectDropdownOption1; //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
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
    @FindBy(xpath = "//a[@title='Table']")
    public static WebElement leftNavigationDrawerTableButton;
    @FindBy(xpath = "//button[@data-test-id='header-create-menu-button']")
    public static WebElement headerAddWorkspace;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-button']")
    public static WebElement headerCreateWorkspaceButton;
    @FindBy(xpath = "//input[@data-test-id='header-create-team-name-input']")
    public static WebElement newWorkspaceNameInput;
    @FindBy(xpath = "//input[@id='teamTypeSelect']")
    public static WebElement workspaceTypeSelectDropdown;
    @FindBy(xpath = "//div[@data-test-id='header-create-team-type-input-education']")
    public static WebElement teamTypeSelectDropdownOption7; //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    @FindBy(className = "_3WDCVZHeu3AAvH")
    public static WebElement newWorkspaceDescriptionOptional;
    @FindBy(xpath = "//button[@data-test-id='header-create-team-submit-button']")
    public static WebElement newWorkspaceSubmitButton;
    @FindBy(xpath = "//input[@data-test-id='add-members-input']")
    public static WebElement inviteTeamViaEmailInput;
    @FindBy(xpath = "//input[@data-test-id='team-invite-submit-button'")
    public static WebElement inviteTeamSubmitButton;
    WebDriver driver;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public static WebElement addBoardFromLeftNavigationDrawer;
    public WorkspaceListPage(WebDriver driver) {
        this.driver = driver;
    }
}
