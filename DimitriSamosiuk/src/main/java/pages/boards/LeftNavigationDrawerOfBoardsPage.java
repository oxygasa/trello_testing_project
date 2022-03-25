package pages.boards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import java.util.List;

public class LeftNavigationDrawerOfBoardsPage extends BasePage {
    WebDriver driver;
    public LeftNavigationDrawerOfBoardsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//a[@title='Table']")
    public static WebElement tableButton;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-expand-button']")
    public static WebElement expander;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public static WebElement addBoard;
    @FindAll({@FindBy(xpath ="//ul[@data-test-id='collapsible-list-items']//a")})
    public static List<WebElement> workspacesAndBoardsList;
    @FindAll({@FindBy(xpath ="//button[contains(@class,'1De3PKu4pMyBCW ')]")})
    public static List<WebElement> boardActionsMenu;
    @FindBy(xpath = "//button[contains(@aria-label,'Close board')]")
    public static WebElement closeBoardMenu;
    @FindAll({@FindBy(xpath ="//button[contains(@class,'_3HfJ71CiQ9nm2y _1Tu9wiuW4Te8Rx')]")})
    public static List<WebElement> confirmCloseBoardButton;
    @FindBy(xpath = "//button[@data-test-id='boards-list-show-more-button']")
    public static WebElement showMore;
}
