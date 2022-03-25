package boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
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
        PageFactory.initElements(driver, LeftNavigationDrawerOfBoardsPage.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        driver.get(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
        LeftNavigationDrawerOfBoardsPage.leftNavigationDrawerTableButton.click();
        LeftNavigationDrawerOfBoardsPage.addBoardFromLeftNavigationDrawer.click();
        BoardsPage.createBoardFromBoardsPageButton.get(0).click();
        String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        BoardsPage.newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
        Thread.sleep(3000); //The submit button is clickable and visible with inactive status.
        // The waiter means this element is active.
        // Need the Thread.sleep
        CommonActions.explicitWaitOfOneElementVisible(BoardsPage.newBoardSubmitButton);
        BoardsPage.newBoardSubmitButton.click();
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
        /**
         * Post condition
         * Close the created board.
         **/
        CommonActions.closeOneBoardInstanceFromTheWorkspacePage(BoardsPage.TEN_BOARDS_TESTING_WORKSPACE);
    }


    //TC ID TRE022 Left Navigation Drawer: Duplicating boards' functionality.
    @Test
    public static void duplicatingBoardsFunctionalityTest() {
    }
}
