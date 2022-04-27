package pages.cards.right_navigation_draver;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.base.BasePage;
import pages.boards.BoardsPage;
import pages.cards.header.CardsHeader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @FindBy(xpath = "//select[@name='org-id']")
    private WebElement workspaceOptions;
    @FindBy(xpath = "//a[contains(@href,'workspacename25')]//span[@class='board-header-btn-text']")
    private WebElement actualWorkspaceName;
    @FindBy(xpath = "//div[@class='js-loaded']/input")
    private WebElement submitWorkspaceButton;
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-pill']/..")
    private WebElement collectionsUpgradeToPremiumButton;
    @FindBy(xpath = "//button[@data-test-id='templates-upgrade-pill']/..")
    private WebElement templatesUpgradeToPremiumButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'list-card-cover')]")})
    private List<WebElement> createdCovers;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> commentPermissionOptions;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> addRemovePermOptions;
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-prompt']")
    private WebElement premiumModuleUpgradeToPremiumButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    private List<WebElement> archivedCardList;
    @FindAll({@FindBy(xpath = "//a[@class='js-reopen']")})
    private List<WebElement> reopenCards;
    @FindBy(xpath = "//a[@download='trello-board-qr-code.png']")
    private WebElement QRcodeDownloadButton;
    @FindBy(xpath = "//a[contains(@class,'js-reopen')]/..")
    private WebElement reopenList;
    @FindBy(xpath = "//a[contains(@class,'archive-controls-switch')]")
    private WebElement archiveListSwitchButton;
    @FindBy(xpath = "//input[@id='boardEmail']")
    private WebElement generatedMailField;
    @FindBy(xpath = "//input[@id='boardNewTitle']")
    private WebElement clonedBoardNewTitleField;
    @FindBy(xpath = "//div[@class='js-qr-code']//a")
    private WebElement linkToQR;
    @FindBy(xpath = "//input[@id='id-short-url']")
    private WebElement boardUrl;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    private WebElement submitCloneBoardButton;
    @FindBy(xpath = "//pre[contains(@style,'word-wrap')]")
    private WebElement json;
    @FindBy(xpath = "//a[@class='js-export-json']/..")
    private WebElement exportToJson;
    @FindBy(xpath = "//div[@class='app__select-photos']")
    private WebElement qrReader;
    @FindBy(xpath = "//input[@id='result']")
    private WebElement qrResultLink;

    private RightNavigationDrawer navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettingsMoreSection();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettingsMoreSection();
        }
        return this;
    }

    private RightNavigationDrawer navigateToMoreSectionWithinDrawerOpeningStatusChecking() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private RightNavigationDrawer navigateToSettingsMoreSection() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer()
                .clickMoreButton();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(0).click();
        return this;
    }

    public RightNavigationDrawer changeWorkspace() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
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
        return this;
    }

    public RightNavigationDrawer disableCover() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(1).click();
        Assert.assertFalse(createdCovers.get(0).isDisplayed());
        return this;
    }

    public RightNavigationDrawer changeCommentPermission() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(2).click();
        CommonActions.explicitWaitOfOneElementVisible(commentPermissionOptions.get(1));
        commentPermissionOptions.get(1).click();
        return this;
    }

    public RightNavigationDrawer addRemovePermTurnOff() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(3).click();
        CommonActions.explicitWaitOfOneElementVisible(addRemovePermOptions.get(1));
        addRemovePermOptions.get(1).click();
        return this;
    }

    public RightNavigationDrawer inviteOnlyTurnOn() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(4).click();
        return this;
    }

    public RightNavigationDrawer navigateToDisallowedBoard(String disallowedBoardUrl) throws InterruptedException {
        driver.get(disallowedBoardUrl);
        return this;
    }

    public RightNavigationDrawer tryToActivateCollections() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(collectionsUpgradeToPremiumButton);
        collectionsUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToActivateTemplate() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(templatesUpgradeToPremiumButton);
        templatesUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToUpgradeUser() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(premiumModuleUpgradeToPremiumButton);
        premiumModuleUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer startWatchBoard() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(4).click();
        return this;
    }

    public RightNavigationDrawer copyAndNavigateToBoardLink() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        String expectedBoardUrl = boardUrl.getAttribute("value");
        driver.get(expectedBoardUrl);
        String actualBoardName = cardsHeader.getBoardName();
        Assert.assertEquals(actualBoardName, expectedBoardName);
        return this;
    }

    private void findDownloadedQrCodeImage(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.getName().toLowerCase().startsWith("trello-board-qr-code")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
    private void deleteRepeatingQRimagesFromFS() {
        for (File myFile : Objects.requireNonNull(new File("C:\\Users\\user\\Downloads").listFiles()))
            if (myFile.getName().contains("trello-board-qr-code")) myFile.delete();
    }
    private void analizeFileSystemAboutExistingQRimagesAndDeleteThem() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(linkToQR);
        linkToQR.click();
        CommonActions.explicitWaitOfOneElementVisible(QRcodeDownloadButton);
        ArrayList<File> fileList = new ArrayList<>();
        findDownloadedQrCodeImage(new File("C:\\Users\\user\\Downloads"), fileList);
    }
    private void downloadQRcode() throws InterruptedException {
        QRcodeDownloadButton.click();
        Thread.sleep(5000);
    }
    public RightNavigationDrawer copyAndNavigateToBoardQR() throws InterruptedException, IOException, NotFoundException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        analizeFileSystemAboutExistingQRimagesAndDeleteThem();
        deleteRepeatingQRimagesFromFS();
        downloadQRcode();
        ArrayList<File> fileList1 = new ArrayList<>();
        findDownloadedQrCodeImage(new File("C:\\Users\\user\\Downloads"), fileList1);
        File qrCodeInFiles = new File(fileList1.get(fileList1.size() - 1).getPath());
        BufferedImage bufferedImage = ImageIO.read(qrCodeInFiles);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        String expectedBoardUrl = result.getText();
        driver.get(expectedBoardUrl);
        String actualBoardName = cardsHeader.getBoardName();
        Assert.assertEquals(actualBoardName, expectedBoardName);
        return this;
    }

    public RightNavigationDrawer copyBoard() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(5).click();
        Thread.sleep(1000);
        CommonActions.explicitWaitOfOneElementVisible(clonedBoardNewTitleField);
        Select workspaceDropdown = new Select(workspaceOptions);
        workspaceDropdown.selectByIndex(1);
        Thread.sleep(2000);
        String newBoardName = RandomStringUtils.randomAlphanumeric(10);
        clonedBoardNewTitleField.sendKeys(newBoardName);
        submitCloneBoardButton.click();
        Thread.sleep(500);
        submitCloneBoardButton.click();
        driver.get(boardsPage.getSecondaryWorkspaceUrl());
        Assert.assertEquals(boardsPage.getFirstBoardTitle(), newBoardName);
        return this;
    }


    public RightNavigationDrawer isJsonValid() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(6).click();
        exportToJson.click();
        Assert.assertTrue(json.getText().contains(expectedBoardName));
        return this;
    }

    private RightNavigationDrawer navigateToArchive() throws InterruptedException {
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(2).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(archivedCardList.get(0));
        return this;
    }

    private RightNavigationDrawer navigateToEmailGenerator() throws InterruptedException {
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
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

    public String getTrelloGeneratedMail() {
        return generatedMailField.getAttribute("value");
    }
}