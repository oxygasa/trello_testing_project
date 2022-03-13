package pages.workspaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import java.util.List;

public class WorkspaceListPage extends BasePage {
    WebDriver driver;
    public WorkspaceListPage(WebDriver driver) {
        this.driver=driver;
    }
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
    @FindBy (id="react-select-3-option-7") //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    public static WebElement teamTypeSelectDropdownOption1; //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    @FindBy(id = "name")
    public static WebElement shortNameTextField;
    @FindBy(id = "website")
    public static WebElement websiteOptionalTextField;
    @FindBy(id = "desc")
    public static WebElement descriptionOptionalTextField;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement saveButton;
}
