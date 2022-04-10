package commons;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import pages.boards.BoardsPage;
import pages.cards.header.CardsHeader;
import pages.login.LoginViaTrelloPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonActions {
    public static WebDriver driver;

    /**
     * This is a browser setup method.
     * Make your choices only by editing values in a txt file /src/main/resources/WebdriverConfig.txt:
     **/

    static {
        switch (Config.platformAndBrowser) {
            case "CHROME_WINDOWS":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "CHROME_WINDOWS_HEADLESS":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("disable-gpu");
                driver = new ChromeDriver(options);
                break;
            case "CHROME_LINUX":
                WebDriverManager wdm = WebDriverManager.chromedriver().linux().enableVnc().enableRecording();
                driver = wdm.create();
                break;
            case "FIREFOX_WINDOWS":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "EDGE_WINDOWS":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "SAFARI_MAC":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                Assert.fail("Incorrect browser name. Choose name of browser in src/main/java/commons/Config Browser name for now is: " + Config.platformAndBrowser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /*** New Browser tab opening within Url typing **/

    public static void openUrlInNewBrowserTab(String Url) throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        js.executeScript("window.open()");
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;

        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
        driver.get(Url);
    }

    /*** Previous Browser tab choosing without closing the current tab **/
    public static void getBackToThePreviousTab() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
    }

    /*** Close the previous tab of a Browser **/
    public static void closePreviousTab() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
        driver.close();
    }


    /**
     * The same login, but by the second user.
     * For the workspace and board visibility tests.
     * For all other tests use the primary user.
     **/
    public static void loginIntoTrelloBySecondUserCredentials() throws InterruptedException {
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        try {
            driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
            LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.SECOND_USER_LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            LoginViaTrelloPage.submitButtonTrello.click();
            LoginViaTrelloPage.password.sendKeys(LoginViaTrelloPage.SECOND_USER_PASSWORD_CREDENTIAL);
            LoginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
            LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.SECOND_USER_LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            LoginViaTrelloPage.submitButtonTrello.click();
            LoginViaTrelloPage.password.sendKeys(LoginViaTrelloPage.SECOND_USER_PASSWORD_CREDENTIAL);
            LoginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        }
    }


    /*** A explicit waiter, which do an assertion of presence 1 element **/
    public static void explicitWaitOfOneElementVisible(WebElement webElementName) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(webElementName));
        Thread.sleep(600);
    }

    /*** An explicit waiter which do an assertion of presence elements list **/
    public static void explicitWaitOfElementsListVisible(List<WebElement> webElementsListName) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(webElementsListName));
        Thread.sleep(1000);
    }

    /**
     * Method is creating 1 empty Board in any Workspace.
     **/
    public static void createOneRandomBoardInstance(String workspaceLink) throws InterruptedException {
        PageFactory.initElements(driver, BoardsPage.class);
        driver.get(workspaceLink);
        Thread.sleep(1500);
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.createBoardFromBoardsPageButton.get(0));
        BoardsPage.createBoardFromBoardsPageButton.get(0).click();
        String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        BoardsPage.newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
        Thread.sleep(3000); //The submit button is clickable and visible with inactive status.
        // The waiter means this element is active.
        // Need the Thread.sleep
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardSubmitButton);
        BoardsPage.newBoardSubmitButton.click();
    }

    /**
     * 1 instance "Board" is closing (safe delete).
     **/
    public static void closeOneBoardInstanceFromTheWorkspacePage(String workspaceLink) throws InterruptedException {
        PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        driver.get(workspaceLink);
        BoardsPage.boardInstancesList.get(0).click();
        try {
            /**
             * Within opening the Right Navigation Drawer
             **/
            Thread.sleep(500);
            CardsHeader.hideRightSidebar.click();
            CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButtonText);
            if (CardsHeader.workspaceVisibleButtonText.getText().equals("Public")) {
                CardsHeader.workspaceVisibleButton.submit();
                CommonActions.explicitWaitOfOneElementVisible(CardsHeader.privateBoardPermissionSelect);
                CardsHeader.privateBoardPermissionSelect.click();
            }
            BoardsPage.showRightSidebarButton.click();
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.rightSidebarMoreButton);
            BoardsPage.rightSidebarMoreButton.click();
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.rightSidebarCloseBoardButton);
            BoardsPage.rightSidebarCloseBoardButton.click();
            CommonActions.explicitWaitOfOneElementVisible(BoardsPage.rightSidebarDialogBoxCloseBoardButton);
            BoardsPage.rightSidebarDialogBoxCloseBoardButton.click();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException e) {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            try {
                CardsHeader.hideRightSidebar.click();
            } catch (org.openqa.selenium.ElementNotInteractableException f) {
                CommonActions.explicitWaitOfOneElementVisible(CardsHeader.workspaceVisibleButtonText);
                if (CardsHeader.workspaceVisibleButtonText.getText().equals("Public")) {
                    CardsHeader.workspaceVisibleButton.click();
                    CommonActions.explicitWaitOfOneElementVisible(CardsHeader.privateBoardPermissionSelect);
                    CardsHeader.privateBoardPermissionSelect.click();
                }
                BoardsPage.rightSidebarMoreButton.click();
                CommonActions.explicitWaitOfOneElementVisible(BoardsPage.rightSidebarCloseBoardButton);
                BoardsPage.rightSidebarCloseBoardButton.click();
                CommonActions.explicitWaitOfOneElementVisible(BoardsPage.rightSidebarDialogBoxCloseBoardButton);
                BoardsPage.rightSidebarDialogBoxCloseBoardButton.click();
            }
        }

    }

    /**
     * All visible instances of "Board" are closing (safe delete).
     * It's need to keep 10 boards limit for free usage of the service.
     * It's need to clean space before fill List collections and Assert them after.
     **/
    public static void closeAllVisibleBoards(String workspaceLink) throws InterruptedException {
        PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        driver.get(workspaceLink);
        Thread.sleep(500);
        try {
            while (BoardsPage.boardInstancesList.size() > 0) {
                CommonActions.closeOneBoardInstanceFromTheWorkspacePage(workspaceLink);
                driver.get(workspaceLink);
            }
        } catch (IndexOutOfBoundsException | org.openqa.selenium.StaleElementReferenceException e) {
            driver.get(workspaceLink);
        }
    }

    /**
     * Trello's dropdown menu values have been hidden by React Select feature.
     * This method is opening the dropdown menu and select the next value in the dropdown menu by Keys.ARROW_DOWN
     **/
    public static void selectDropdownMenuNextValue(WebElement dropdownMenuWebElementName) {
        dropdownMenuWebElementName.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        dropdownMenuWebElementName.click();
    }

    public static void selectDropdownMenuValueByPositionNumber(WebElement dropdownMenuWebElementName, int countsOfPressArrowDownButton) {
        dropdownMenuWebElementName.click();
        Actions action = new Actions(driver);
        for (int i = 0; i < countsOfPressArrowDownButton; i++) {
            action.sendKeys(Keys.ARROW_DOWN).perform();
        }
        action.sendKeys(Keys.ENTER).perform();
        dropdownMenuWebElementName.click();
    }
}