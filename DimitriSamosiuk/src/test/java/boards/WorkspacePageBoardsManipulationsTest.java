package boards;

import commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.boards.BoardsPage;
import pages.workspaces.WorkspaceListPage;
import java.util.Random;

import static commons.CommonActions.driver;

public class WorkspacePageBoardsManipulationsTest {

    //TC ID TRE014 Workspace page: Boards Creation
    @Test
    public static void boardsCreationTest() throws InterruptedException {
        /**
         * Steps to Reproduce
         * 1. Login to your account within precondition credentials.
         * 2. Select the Workspace on a header.
         * 3. Select Your Workspace.
         **/
        PageFactory.initElements(driver, WorkspaceListPage.class);
        PageFactory.initElements(driver, BoardsPage.class);
        CommonActions.loginIntoTrelloWithinDefaultPreconditionCredentials();
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(0).click();
        /**
         * Steps to Reproduce
         * 4. Close the last board where boards number is bigger than 8 (for automation purposes)
         **/
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(driver -> BoardsPage.remainingBoardsCounter);
        String remainingCounter = BoardsPage.remainingBoardsCounter.getText();
        char firstChar = remainingCounter.charAt(0);
        int counterNumber = Integer.parseInt(String.valueOf(firstChar));
        System.out.println(counterNumber);
        if (counterNumber < 5) {
            BoardsPage.existingBoardList.get(1).click();
            BoardsPage.showRightSidebarButton.click();
            BoardsPage.rightSidebarMoreButton.click();
            BoardsPage.rightSidebarCloseBoardButton.click();
            BoardsPage.rightSidebarDialogBoxCloseBoardButton.click();
        }
        /**
         * Steps to Reproduce
         * 5. Create a board with random parameters from Workspaces.
         **/
        Random random = new Random();
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(0).click();
        wait.until(driver -> BoardsPage.existingBoardList.get(0));
        BoardsPage.existingBoardList.get(0).click();
        BoardsPage.backgroundForTheBoardImageList.get(random.nextInt(7)).click();
        String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        wait.until(driver -> BoardsPage.newBoardNameInput);
        BoardsPage.newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
//      BoardsPage.workspaceDropdownMenu.click();
//      BoardsPage.workspaceDropdownMenuOption0.click(); //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
//      BoardsPage.workspaceVisibilityDropdownMenu.click();
//      BoardsPage.workspaceVisibilityDropdownMenuOption0.click(); //THIS LOCATOR IS HIDDEN IN HTML BY "REACT-SELECT".
        wait.until(driver -> BoardsPage.newBoardSubmitButton);
        BoardsPage.newBoardSubmitButton.click();
        wait.until(driver -> BoardsPage.createdBoardName);
        String createdBoardNameText = BoardsPage.createdBoardName.getText();
        Assert.assertEquals(createdBoardNameText, newBoardNameTextFieldInputText);

        /**
         * Steps to Reproduce
         * 6. Open Boards page.
         * 7. Check the Creation board module is opened on a Board page.
         **/

        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(0).click();
        Thread.sleep(2000);
        String boardsUrl = driver.getCurrentUrl() + "/boards";
        driver.get(boardsUrl);
        wait.until(driver -> BoardsPage.createBoardFromBoardsPageButton);
        BoardsPage.createBoardFromBoardsPageButton.get(0).click();
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());

        /**
         * 8. Check the Creation board module is opened in a Left Navigation Drawer.
         **/
        WorkspaceListPage.headerWorkspaceDropdown.click();
        WorkspaceListPage.workspacesNameList.get(0).click();
        wait.until(driver -> WorkspaceListPage.addBoardFromLeftNavigationDrawer);
        WorkspaceListPage.addBoardFromLeftNavigationDrawer.click();
        Assert.assertTrue(BoardsPage.newBoardNameInput.isDisplayed());
    }


    //TC ID TRE015 Workspace page: Boards Free account limit counter checking
    @Test
    public static void boardsFreeAccountLimitCounterTest() {
    }

    //TC ID TRE016 Workspace page: Boards deletion, recovery and permanent deletion.
    @Test
    public static void boardsDeleteRecoveryAndPermanentDeleteTest() {
    }

    //TC ID TRE017 Workspace page: Boards add to  favourite.
    @Test
    public static void boardsAddToFavouriteTest() {
    }

    //TC ID TRE018 Workspace page: Boards filter menu
    @Test
    public static void boardsFilterMenuTest() {
    }


}

