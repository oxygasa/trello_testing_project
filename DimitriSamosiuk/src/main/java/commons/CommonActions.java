package commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.login.LoginViaTrelloPage;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import static commons.Config.PLATFORM_AND_BROWSER;

public class CommonActions {
    public static WebDriver driver;
    /**
     * This is a browser setup methods.
     * Choose your properties only via file "src/main/java/commons/Config"
     **/

    static {
        switch (PLATFORM_AND_BROWSER) {
            case "CHROME_WINDOWS":
                  driver = new ChromeDriver();
                break;
            case "FIREFOX_WINDOWS":
                  driver = new FirefoxDriver();
                break;
            default:
                Assert.fail("Incorrect browser name. Choose name of browser in src/main/java/commons/Config Browser name for now is: " + PLATFORM_AND_BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     * New tab opening within Url typing
     **/
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
    public static void closePreviousTab() throws InterruptedException{
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
     * Method for Log in before any test within default credentials.
     * Credentials are located in LoginViaTrelloPage class.
     **/
    public static void loginIntoTrelloWithinDefaultPreconditionCredentials() throws InterruptedException {
        PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(LoginViaTrelloPage.TRELLO_LOGIN_PAGE);
        LoginViaTrelloPage.username.sendKeys(LoginViaTrelloPage.LOGIN_CREDENTIAL);
        Thread.sleep(2000);
        LoginViaTrelloPage.submitButtonTrello.click();
        LoginViaTrelloPage.password.sendKeys(LoginViaTrelloPage.PASSWORD_CREDENTIAL);
        LoginViaTrelloPage.submitButtonAtlassian.click();
        Thread.sleep(5000);
    }
}
