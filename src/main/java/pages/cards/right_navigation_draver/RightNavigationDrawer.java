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

import java.security.cert.X509Certificate;
import java.util.List;

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
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-pill']/..")
    private WebElement collectionsUpgradeToPremiumButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'list-card-cover')]")})
    private List<WebElement> createdCovers;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> commentPermissionOptions;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> addRemovePermOptions;
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-prompt']")
    private WebElement premiumModuleUpgradeToPremiumButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    List<WebElement> archivedCardList;
    @FindAll({@FindBy(xpath = "//a[@class='js-reopen']")})
    List<WebElement> reopenCards;
    @FindBy(xpath = "//a[contains(@class,'js-reopen')]/..")
    WebElement reopenList;
    @FindBy(xpath = "//a[contains(@class,'archive-controls-switch')]")
    WebElement archiveListSwitchButton;
    @FindBy(xpath = "//input[@id='boardEmail']")
    WebElement generatedMailField;
    private RightNavigationDrawer navigateToSettings() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer()
                .clickMoreButton();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(0).click();
        return this;
    }

    public RightNavigationDrawer changeWorkspace() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(0).click();
            Select workspaceDropdown = new Select(workspaceSelector);
            workspaceDropdown.selectByIndex(1);
            Thread.sleep(2000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
            Thread.sleep(2000);
            Assert.assertEquals(actualWorkspaceName.getText(), "Workspace name");
            settingsMenuList.get(0).click();
            workspaceDropdown.selectByIndex(0);
            Thread.sleep(4000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(0).click();
            Select workspaceDropdown = new Select(workspaceSelector);
            workspaceDropdown.selectByIndex(1);
            Thread.sleep(2000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
            Thread.sleep(2000);
            Assert.assertEquals(actualWorkspaceName.getText(), "Workspace name");
            settingsMenuList.get(0).click();
            workspaceDropdown.selectByIndex(0);
            Thread.sleep(4000);
            CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
            submitWorkspaceButton.click();
        }
        return this;
    }

    public RightNavigationDrawer disableCover() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);

        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(1).click();
            Assert.assertFalse(createdCovers.get(0).isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(1).click();
            Assert.assertFalse(createdCovers.get(0).isDisplayed());
        }
        return this;
    }

    public RightNavigationDrawer changeCommentPermission() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(2).click();
            CommonActions.explicitWaitOfOneElementVisible(commentPermissionOptions.get(1));
            commentPermissionOptions.get(1).click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(2).click();
            CommonActions.explicitWaitOfOneElementVisible(commentPermissionOptions.get(1));
            commentPermissionOptions.get(1).click();
        }
        return this;
    }

    public RightNavigationDrawer addRemovePermTurnOff() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(3).click();
            CommonActions.explicitWaitOfOneElementVisible(addRemovePermOptions.get(1));
            addRemovePermOptions.get(1).click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(3).click();
            CommonActions.explicitWaitOfOneElementVisible(addRemovePermOptions.get(1));
            addRemovePermOptions.get(1).click();
        }
        return this;
    }

    public RightNavigationDrawer inviteOnlyTurnOn() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(4).click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettings();
            CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
            settingsMenuList.get(4).click();
        }
        return this;
    }

    public RightNavigationDrawer navigateToDisallowedBoard(String disallowedBoardUrl) throws InterruptedException {
        driver.get(disallowedBoardUrl);
        return this;
    }

    public RightNavigationDrawer tryToActivateCollections() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(collectionsUpgradeToPremiumButton);
            collectionsUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(collectionsUpgradeToPremiumButton);
            collectionsUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        }
        return this;
    }

    public RightNavigationDrawer tryToUpgradeUser() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(premiumModuleUpgradeToPremiumButton);
            premiumModuleUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(premiumModuleUpgradeToPremiumButton);
            premiumModuleUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        }
        return this;
    }

    private RightNavigationDrawer navigateToArchive() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer()
                .clickMoreButton();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(2).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(archivedCardList.get(0));
        return this;
    }
    private RightNavigationDrawer navigateToEmailGenerator() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer()
                .clickMoreButton();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(3).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(generatedMailField);
        return this;
    }

    public RightNavigationDrawer returnAllCardsFromArchive() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(premiumModuleUpgradeToPremiumButton);
            premiumModuleUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            navigateToArchive();
            CommonActions.explicitWaitOfOneElementVisible(reopenCards.get(0));
            while (reopenCards.size() > 0) {
                reopenCards.get(0).click();
            }
        }
        return this;
    }

    public RightNavigationDrawer returnListOfCardsFromArchive() throws InterruptedException {
        archiveListSwitchButton.click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(reopenList);
        reopenList.click();
        return this;
    }

    public RightNavigationDrawer generateMailAddressByTrello() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(premiumModuleUpgradeToPremiumButton);
            premiumModuleUpgradeToPremiumButton.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            navigateToEmailGenerator();
        }
        return this;
    }
    public String getTrelloGeneratedMail(){
        return generatedMailField.getAttribute("value");
    }
}