package commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
    /*** This is a class for WebDriver and browser behaviour setups.
     * Make your choices only by editing values in a txt file /src/main/resources/WebdriverConfig.txt:
     ***/
    public static String platformAndBrowser;
    public static boolean clearCookies;
    public static boolean holdBrowserOpen;
    public static boolean makeScreenshots;

    static {
        try {
            platformAndBrowser = webdriverConfigBrowserFromTxtReader(); //init the variables on the top of this class
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            clearCookies = webdriverConfigCookiesFromTxtReader();
            holdBrowserOpen = webdriverConfigHoldOpenBrowserFromTxtReader();
            makeScreenshots = webdriverConfigMakeScreenshotsFromTxtReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*** This is a Scanner for /src/main/resources/WebdriverConfig.txt **/

    public static String webdriverConfigBrowserFromTxtReader() throws FileNotFoundException {
        String separator = File.separator;
        String path = "." + separator + "src" + separator + "main" + separator + "resources" + separator + "WebdriverConfig.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String platformAndBrowser = scanner.nextLine(); //scan the PLATFORM_AND_BROWSER line
        StringBuffer trimmedValueOfPlatformAndBrowser = new StringBuffer(platformAndBrowser);
        trimmedValueOfPlatformAndBrowser.delete(0, 24); // delete "PLATFORM_AND_BROWSER = " rubbish text
        trimmedValueOfPlatformAndBrowser.delete(trimmedValueOfPlatformAndBrowser.length() - 2,
                trimmedValueOfPlatformAndBrowser.length()); // delete till ";" sign the rubbish text
        /**
         * Specify the browser and operating system for tests in a txt file /src/main/resources/WebdriverConfig.txt:
         * CHROME_WINDOWS
         * CHROME_WINDOWS_HEADLESS
         * CHROME_LINUX
         * FIREFOX_WINDOWS
         * EDGE_WINDOWS
         * SAFARI_MAC
         **/
        String getPlatformAndBrowserFromTxt = String.valueOf(trimmedValueOfPlatformAndBrowser);
        scanner.close();
        return getPlatformAndBrowserFromTxt;
    }

    public static boolean webdriverConfigCookiesFromTxtReader() throws FileNotFoundException {
        String separator = File.separator;
        String path = "." + separator + "src" + separator + "main" + separator + "resources" + separator + "WebdriverConfig.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); //scan the PLATFORM_AND_BROWSER line
        /**
         * Specify is Clear all cookies needed after each test in a txt file /src/main/resources/WebdriverConfig.txt:
         */
        String clearCookiesLine = scanner.nextLine(); // scan the CLEAR_COOKIES line
        scanner.close();
        return clearCookiesLine.contains("true");
    }

    public static boolean webdriverConfigHoldOpenBrowserFromTxtReader() throws FileNotFoundException {
        String separator = File.separator;
        String path = "." + separator + "src" + separator + "main" + separator + "resources" + separator + "WebdriverConfig.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); //scan the PLATFORM_AND_BROWSER line
        scanner.nextLine(); //scan the CLEAR_COOKIES line
        /**
         * Specify is Keep browser open after all tests needed after each test in a txt file /src/main/resources/WebdriverConfig.txt:
         */
        String holdBrowserOpenLine = scanner.nextLine(); //scan the HOLD_BROWSER line
        scanner.close();
        return holdBrowserOpenLine.contains("true");
    }

    public static boolean webdriverConfigMakeScreenshotsFromTxtReader() throws FileNotFoundException {
        String separator = File.separator;
        String path = "." + separator + "src" + separator + "main" + separator + "resources" + separator + "WebdriverConfig.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); //scan the PLATFORM_AND_BROWSER line
        scanner.nextLine(); //scan the CLEAR_COOKIES line
        scanner.nextLine(); //scan the HOLD_BROWSER line
        /**
         * Specify is make a screenshot after each test needed after each test in a txt file /src/main/resources/WebdriverConfig.txt:
         * Screenshots are located at %ROOT_PROJECT%/screenshots/screenshot_date_and_time.png
         **/
        String makeScreenshotsLine = scanner.nextLine(); // scan the MAKE_SCREENSHOTS line
        scanner.close();
        return makeScreenshotsLine.contains("true");
    }
}
