package pages.boards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class BoardsPage extends BasePage {
    WebDriver driver;
    public BoardsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindAll({@FindBy(xpath = "//ul[@class='boards-page-board-section-list']/li")})
    public static List<WebElement> existingBoardList;
    @FindBy(xpath = "//a[contains(@class,'js-show-sidebar')]")
    public static WebElement showRightSidebarButton;
    @FindBy(xpath = "//a[contains(@class,'js-open-more')]")
    public static WebElement rightSidebarMoreButton;
    @FindBy(xpath = "//a[contains(@class,'js-close-board')]")
    public static WebElement rightSidebarCloseBoardButton;
    @FindBy(xpath = "//input[@value='Close']")
    public static WebElement rightSidebarDialogBoxCloseBoardButton;
    @FindBy(xpath = "//p[@class='remaining']/span")
    public static WebElement remainingBoardsCounter;
    @FindAll({@FindBy(xpath = "//div[@class='_3qBPBEvJ1G0xtT']//button[contains(@class,'_3TTqkG5muwOzqZ')]")})
    public static List<WebElement> backgroundForTheBoardImageList;
    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    public static WebElement newBoardNameInput;
    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public static WebElement workspaceSelectionDropdownMenu;
    @FindBy(id = "react-select-3-option-0")
    public static WebElement workspaceDropdownMenuOption0; //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    @FindBy(xpath = "//input[@id='react-select-4-input']")
    public static WebElement workspaceVisibilityDropdownMenu;
    @FindBy(id = "react-select-3-option-0")
    public static WebElement workspaceVisibilityDropdownMenuOption0; //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
    @FindBy(xpath = "//button[@data-test-id='create-board-submit-button']")
    public static WebElement newBoardSubmitButton;
    @FindBy(xpath = "//div[contains(@class,'js-rename-board')]/h1")
    public static WebElement createdBoardName;
    @FindAll({@FindBy(xpath = "//li[@data-test-id='create-board-tile']")})
    public static List<WebElement> createBoardFromBoardsPageButton;
}
