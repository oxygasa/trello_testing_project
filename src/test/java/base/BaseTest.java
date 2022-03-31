package base;

import commons.CommonActions;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import pages.base.BasePage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static commons.Config.CLEAR_TEST_REPORT_AND_SCREENSHOT_DIRECTORY;
import static commons.Config.CLEAR_COOKIES;
import static commons.Config.MAKE_SCREENSHOTS;
import static commons.Config.HOLD_BROWSER_OPEN;

public class BaseTest extends BasePage {
    WebDriver driver;

    @BeforeTest
    public WebDriver startBrowser() throws IOException {
        driver = CommonActions.driver;
        File buildFolder = new File("./DimitriSamosiuk/build/");
        File allureResultFolder = new File(".allure-results");
        if (buildFolder.isDirectory() && CLEAR_TEST_REPORT_AND_SCREENSHOT_DIRECTORY) {
            FileUtils.deleteDirectory(new File(String.valueOf(buildFolder)));
            FileUtils.deleteDirectory(new File(String.valueOf(allureResultFolder)));
        }
        return driver;
    }

    @AfterSuite
    public void closeBrowser() {
        if (!HOLD_BROWSER_OPEN) {
            if (CLEAR_COOKIES) {
                driver.manage().deleteAllCookies();
            }
            driver.quit();
        }
    }

    @AfterMethod
    public void takingScreenshotsAfterEachTest() throws IOException {
        if (MAKE_SCREENSHOTS && !(driver == null)) {
            Date date = new Date();
            String currentTime = String.valueOf(date.getTime());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File bufferedScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(bufferedScreenshotFile, new File(new StringBuilder().
                    append("./screenshots/").append(currentTime).append(".png").toString()));
        }
    }

    @Attachment
    public byte[] makeAttachScreenshotToAllure(TakesScreenshot takesScreenshot) {
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
}
