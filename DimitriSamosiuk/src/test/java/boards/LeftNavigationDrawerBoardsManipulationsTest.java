package boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.boards.LeftNavigationDrawerOfBoardsPage;

import static commons.CommonActions.driver;

public class LeftNavigationDrawerBoardsManipulationsTest {

    //TC ID TRE019 Left Navigation Drawer: Add, Favourite, close boards.
    @Test
    public static void addFavouriteCloseBoardsTest() throws InterruptedException {
        /**
         * Precondition
         * Select the Workspace on a header.
         * Close all visible boards.
         **/
        PageFactory.initElements(driver, LeftNavigationDrawerOfBoardsPage.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        CommonActions.closeAllVisibleBoards(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        /**
         * 1. Open any workspace.
         * 2. On the Left Navigation Drawer click "+" sign.
         * 3. Create a board with a random title.
         **/
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        LeftNavigationDrawerOfBoardsPage.expander.click();
        LeftNavigationDrawerOfBoardsPage.addBoard.click();
        String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        BoardsPage.newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
        Thread.sleep(3000); //The submit button is clickable and visible with inactive status.
        // The waiter means this element is active.
        // Need the Thread.sleep
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardSubmitButton);
        BoardsPage.newBoardSubmitButton.click();
        /**
         4. On the Left Navigation Drawer click three dots "···" sign then click the close the boards then close.
         **/
        while (LeftNavigationDrawerOfBoardsPage.workspacesAndBoardsList.size() > 2) {
            driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
            Actions actions = new Actions(driver);
            try {
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawerOfBoardsPage.workspacesAndBoardsList.get(0));
                actions.moveToElement(LeftNavigationDrawerOfBoardsPage.workspacesAndBoardsList.get(2)).build().perform();
            } catch (java.lang.IndexOutOfBoundsException e) {
                LeftNavigationDrawerOfBoardsPage.showMore.click();
                CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawerOfBoardsPage.workspacesAndBoardsList.get(0));
                actions.moveToElement(LeftNavigationDrawerOfBoardsPage.workspacesAndBoardsList.get(2)).build().perform();
            }
            CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawerOfBoardsPage.boardActionsMenu.get(0));
            actions.moveToElement(LeftNavigationDrawerOfBoardsPage.boardActionsMenu.get(0)).click().build().perform();
            CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawerOfBoardsPage.closeBoardMenu);
            LeftNavigationDrawerOfBoardsPage.closeBoardMenu.click();
            CommonActions.explicitWaitOfOneElementVisible(LeftNavigationDrawerOfBoardsPage.confirmCloseBoardButton.get(1));
            LeftNavigationDrawerOfBoardsPage.confirmCloseBoardButton.get(1).click();
        }
    }


    //TC ID TRE022 Left Navigation Drawer: Duplicating boards' functionality.
    @Test
    public static void duplicatingBoardsFunctionalityTest() throws InterruptedException {
        PageFactory.initElements(driver, LeftNavigationDrawerOfBoardsPage.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE+"/boards");
        LeftNavigationDrawerOfBoardsPage.navigationToBoardsTemplatesHomeWorkspaces.get(0).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/templates");
        LeftNavigationDrawerOfBoardsPage.navigationToBoardsTemplatesHomeWorkspaces.get(1).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://trello.com/");
    }
}
