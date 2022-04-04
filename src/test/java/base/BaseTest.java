package base;

import commons.CommonActions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.base.BasePage;
import pages.login.LoginViaTrelloPage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static commons.Config.*;

public class BaseTest extends BasePage {
    WebDriver driver;

    @BeforeTest
    public WebDriver startBrowser() throws InterruptedException {
        driver = CommonActions.driver;
        if (CLEAR_COOKIES) {
            driver.manage().deleteAllCookies();
        }
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        try {
            driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
            LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            LoginViaTrelloPage.submitButtonTrello.click();
            LoginViaTrelloPage.password.sendKeys(LoginViaTrelloPage.PASSWORD_CREDENTIAL);
            LoginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        } catch (NoSuchElementException | InterruptedException e) {
            driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
            LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            LoginViaTrelloPage.submitButtonTrello.click();
            LoginViaTrelloPage.password.sendKeys(LoginViaTrelloPage.PASSWORD_CREDENTIAL);
            LoginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        }
        return driver;
    }

    @AfterTest
    public void closeBrowser() throws IOException {
        if (MAKE_SCREENSHOTS && !(driver == null)) {
            Date date = new Date();
            String currentTime = String.valueOf(date.getTime());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File bufferedScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(bufferedScreenshotFile, new File("./screenshots/" + currentTime + ".png"));
        }
        if (!HOLD_BROWSER_OPEN) {
            if (CLEAR_COOKIES) {
                assert driver != null;
                driver.manage().deleteAllCookies();
            }
            assert driver != null;
            driver.quit();
        }
    }
}
