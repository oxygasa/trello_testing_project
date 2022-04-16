package base;

import commons.CommonActions;
import commons.Config;
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

public class BaseTest extends BasePage {
    WebDriver driver;

    @BeforeTest
    public WebDriver startBrowser() throws InterruptedException {
        driver = CommonActions.driver;
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        try {
            driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
            loginViaTrelloPage.username.sendKeys(loginViaTrelloPage.LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            loginViaTrelloPage.submitButtonTrello.click();
            loginViaTrelloPage.password.sendKeys(loginViaTrelloPage.PASSWORD_CREDENTIAL);
            loginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        } catch (NoSuchElementException | InterruptedException e) {
            driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
            loginViaTrelloPage.username.sendKeys(loginViaTrelloPage.LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            loginViaTrelloPage.submitButtonTrello.click();
            loginViaTrelloPage.password.sendKeys(loginViaTrelloPage.PASSWORD_CREDENTIAL);
            loginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(7000); // <== Hardly need for finish load the login
                                     // completely and prevent Cookies loss and Flake all tests.
        }
        return driver;
    }

    @AfterTest
    public void closeBrowser() throws IOException {
        if (Config.makeScreenshots && !(driver == null)) {
            Date date = new Date();
            String currentTime = String.valueOf(date.getTime());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File bufferedScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(bufferedScreenshotFile, new File("./screenshots/" + currentTime + ".png"));
        }
        if (!Config.holdBrowserOpen) {
            if (Config.clearCookies) {
                assert driver != null;
                driver.manage().deleteAllCookies();
            }
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
}
