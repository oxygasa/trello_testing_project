package pages.cards.right_navigation_draver;

import commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.base.BasePage;
import pages.boards.BoardsPage;

import java.util.List;

import static commons.CommonActions.driver;

public class RightNavigationDrawer extends BasePage {
    WebDriver driver;
    public RightNavigationDrawer(WebDriver driver) {
        this.driver = driver;
    }
    @FindAll({@FindBy(xpath = "//li[@class='board-menu-navigation-item']")})
    private List<WebElement> moreMenuList;
    @FindAll({@FindBy(xpath = "//ul[contains(@class,'pop-over-list')]/li")})
    private List<WebElement> settingsMenuList;
    @FindBy(xpath = "//select[@class='js-org']")
    private WebElement workspaceSelector;
    @FindBy(xpath = "//a[contains(@href,'workspacename25')]//span[@class='board-header-btn-text']")
    private WebElement actualWorkspaceName;
    @FindBy(xpath = "//div[@class='js-loaded']/input")
    private WebElement submitWorkspaceButton;
    public RightNavigationDrawer changeWorkspace() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);

        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer()
                    .openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
            moreMenuList.get(0).click();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(0).click();
            Select workspaceDropdown = new Select(workspaceSelector);
            workspaceDropdown.selectByIndex(1);
            Thread.sleep(2000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
            Thread.sleep(2000);
            Assert.assertEquals(actualWorkspaceName.getText(),"Workspace name");
            settingsMenuList.get(0).click();
            workspaceDropdown.selectByIndex(0);
            Thread.sleep(4000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
            moreMenuList.get(0).click();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(0).click();
            Select workspaceDropdown = new Select(workspaceSelector);
            workspaceDropdown.selectByIndex(1);
            Thread.sleep(2000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
            Thread.sleep(2000);
            Assert.assertEquals(actualWorkspaceName.getText(),"Workspace name");
            settingsMenuList.get(0).click();
            workspaceDropdown.selectByIndex(0);
            Thread.sleep(4000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
        }
        return this;
    }
}
