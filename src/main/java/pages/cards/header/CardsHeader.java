package pages.cards.header;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.base.BasePage;
import pages.boards.BoardsPage;
import pages.cards.card_list_preview_page.CardListPreviewPage;
import pages.workspaces.WorkspaceListPage;

import java.util.List;

import static commons.CommonActions.driver;

public class CardsHeader extends BasePage {
    WebDriver driver;
    @FindBy(xpath = "//button[@data-test-id='board-views-switcher-button']")
    private WebElement boardViewSwitcherButton;
    @FindBy(xpath = "//button[@data-test-id='board-views-upsell-prompt-start-free-trial-button']")
    private WebElement boardViewSwitcherFreeTrialButton;
    @FindBy(xpath = "//div[contains(@class,'js-rename-board')]")
    private WebElement boardRenameInput;
    @FindBy(xpath = "//h1[contains(@class,'board-header-btn-text')]")
    private WebElement boardTitleFor2ndUser;
    @FindBy(xpath = "//a[contains(@class,'js-open-org-menu')]")
    private WebElement openWorkspaceMenu;
    @FindBy(xpath = "//a[contains(@class,'js-change-org')]")
    private WebElement changeWorkspaceMenu;
    @FindBy(xpath = "//select[@class='js-org']")
    private WebElement changeWorkspaceDropdownMenu;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    private WebElement changeWorkspaceSubmitButton;
    @FindBy(xpath = "//a[@class='js-view-org']")
    private WebElement viewWorkspaceMenu;
    @FindBy(id = "//a[@id='permission-level']/..")
    private WebElement workspaceVisibleButton;
    @FindBy(xpath = "//a[@id='permission-level']//span[@class='board-header-btn-text']")
    private WebElement workspaceVisibleButtonText;
    @FindBy(xpath = "//a[@name='public']")
    private WebElement publicBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='private']")
    private WebElement privateBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='org']")
    private WebElement workspaceBoardPermissionSelect;
    @FindBy(xpath = "//button[contains(@class,'js-submit')]")
    private WebElement publicBoardPermissionConfirmButton;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-button']")
    private WebElement avatarIcon;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-logout']")
    private WebElement logoutButton;
    @FindBy(xpath = "//a[contains(@class,'board-header-btn-invite')]")
    private WebElement inviteButton;
    @FindBy(xpath = "//a[contains(@class,'js-show-invitation-link')]")
    private WebElement inviteLinkCreator;
    @FindBy(xpath = "//input[contains(@class,'js-invitation-link')]")
    private WebElement invitationLinkForCopyField;
    @FindBy(xpath = "//a[contains(@class,'pop-over-header-close-btn')]")
    private WebElement invitationWindowCloseButton;
    @FindBy(xpath = "//input[@data-test-id='header-search-input']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[@data-test-id='filter-popover-button']")
    private WebElement filterPopoverButton;
    @FindBy(xpath = "//input[contains(@class,'nch-textfield__input')]")
    private WebElement filerKeywordInput;
    @FindBy(xpath = "//div[contains(@title,'assigned')]")
    private WebElement cardsAssignedToMeCheckBox;
    @FindBy(xpath = "//span[@data-test-id='board-view-option']/..")
    private WebElement BoardViewSwitcherPremiumBoardButton;
    @FindAll({@FindBy(xpath = "//div[@class='D7o35mpYYtXnpz']")})
    private List<WebElement> allFilterCheckboxes;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'css-ufz0vj-control')]")})
    private List<WebElement> allFilterDropdowns;
    private String expectedBoardName;

    public CardsHeader(WebDriver driver) {
        this.driver = driver;
    }

    public CardsHeader inviteAndConnect2ndUserToTheBoardViaLink() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(inviteButton);
        inviteButton.click();
        CommonActions.explicitWaitOfOneElementVisible(inviteLinkCreator);
        inviteLinkCreator.click();
        Thread.sleep(2500);
        Actions actions = new Actions(CommonActions.driver);
        actions.moveToElement(invitationLinkForCopyField).doubleClick().build().perform();
        invitationLinkForCopyField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        invitationWindowCloseButton.click();
        avatarIcon.click();
        logoutButton.click();
        CommonActions.driver.manage().deleteAllCookies();
        CommonActions.loginIntoTrelloBySecondUserCredentials();
        actions.moveToElement(searchBox).sendKeys(Keys.chord(Keys.CONTROL, "v"));
        String inviteLink = searchBox.getText();
        CommonActions.driver.get(inviteLink);
        return this;
    }

    public CardsHeader tryToChangeBoardViewAsFreeUser() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(boardViewSwitcherButton);
        boardViewSwitcherButton.click();
        /*** A Board is opened. A module with a Premium asking is displayed.**/
        Assert.assertTrue(boardViewSwitcherFreeTrialButton.isDisplayed()); //trial assert trying
        return this;
    }

    public CardsHeader checkIsUserIsPremium() {
        BoardViewSwitcherPremiumBoardButton.isDisplayed(); //premium assert trying
        return this;
    }

    public CardsHeader renameBoard() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        Actions actions = new Actions(driver);
        CommonActions.explicitWaitOfOneElementVisible(boardRenameInput);
        String boardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        actions.click(boardRenameInput).sendKeys(boardNameTextFieldInputText).sendKeys(Keys.ENTER).build().perform();
        String expectedTitleAfterRename = boardRenameInput.getText();
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        System.out.println("expected" + expectedTitleAfterRename);
        System.out.println("actual" + boardsPage.getFirstBoardTitle());
        Assert.assertEquals(boardsPage.getFirstBoardTitle(), expectedTitleAfterRename);
        return this;
    }

    public CardsHeader changeWorkspace() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(openWorkspaceMenu);
        openWorkspaceMenu.click();
        CommonActions.explicitWaitOfOneElementVisible(changeWorkspaceMenu);
        changeWorkspaceMenu.click();
        CommonActions.selectDropdownMenuNextValue(changeWorkspaceDropdownMenu);
        CommonActions.explicitWaitOfOneElementVisible(changeWorkspaceSubmitButton);
        changeWorkspaceSubmitButton.click();
        Thread.sleep(3000);
        openWorkspaceMenu.click();
        viewWorkspaceMenu.click();
        return this;
    }

    public String getVisibleWorkspaceButtonText() {
        return workspaceVisibleButtonText.getText();
    }

    public CardsHeader selectPrivateVisible() throws InterruptedException {
        workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(privateBoardPermissionSelect);
        privateBoardPermissionSelect.click();
        return this;
    }

    public CardsHeader selectPublicVisible() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(workspaceVisibleButton);

        this.expectedBoardName = boardRenameInput.getText();
        workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(publicBoardPermissionSelect);
        publicBoardPermissionSelect.click();
        CommonActions.explicitWaitOfOneElementVisible(publicBoardPermissionConfirmButton);
        publicBoardPermissionConfirmButton.click();
        return this;
    }

    public CardsHeader selectWorkspaceVisible() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(workspaceVisibleButton);
        this.expectedBoardName = boardRenameInput.getText();
        workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(workspaceBoardPermissionSelect);
        workspaceBoardPermissionSelect.click();
        return this;
    }

    public CardsHeader logoutFromTrello() throws InterruptedException {
        Thread.sleep(3000);
        avatarIcon.click();
        logoutButton.click();
        driver.manage().deleteAllCookies();
        return this;
    }

    public CardsHeader isTheSameBoard() {
        Assert.assertEquals(boardTitleFor2ndUser.getText(), this.expectedBoardName);
        return this;
    }

    public CardsHeader selectRandomFilterDetails() throws InterruptedException {
        String expectedCardName = CardListPreviewPage.createRandomCardOnTheList(); // тут у меня null pointer, lol
        int randomLabelColorPositionNumber = CardListPreviewPage.randomLabelColor();
        filterPopoverButton.click();
        filerKeywordInput.sendKeys(expectedCardName);
        cardsAssignedToMeCheckBox.click();//assigned to me
        allFilterCheckboxes.get(3).click();//no date
        allFilterDropdowns.get(0).click();//labels color
        CommonActions.selectDropdownMenuValueByPositionNumber(allFilterDropdowns.get(0), randomLabelColorPositionNumber);//labels color
        return this;
    }
}
