package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static commons.Config.PLATFORM_AND_BROWSER;
import static constants.Constant.TimeoutVariables.IMPLICIT_WAIT;


public class CommonActions {
    public static WebDriver driver;

    /*
     *This is a browser setup methods.
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
            case "INTERNET_EXPLORER_WINDOWS":
                driver = new InternetExplorerDriver();
                break;
            default:
                Assert.fail("Incorrect browser name. Choose name of browser in src/main/java/commons/Config Browser name for now is: " + PLATFORM_AND_BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }
}
