package pages.boards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LeftNavigationDrawerOfBoardsPage extends BasePage {
    WebDriver driver;
    public LeftNavigationDrawerOfBoardsPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(xpath = "//a[@title='Table']")
    public static WebElement leftNavigationDrawerTableButton;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public static WebElement addBoardFromLeftNavigationDrawer;
}
