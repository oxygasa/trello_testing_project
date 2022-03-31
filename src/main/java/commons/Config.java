package commons;
public class Config {


    /*** This is a class for WebDriver and browser behaviour setups.**/

    /**
     * Specify the browser name and operating system for tests:
     * CHROME_WINDOWS_LINUX
     * FIREFOX_WINDOWS
     **/
    public static final String PLATFORM_AND_BROWSER = "CHROME_WINDOWS_LINUX";

    /** Clear all cookies and cache after each interaction.*/
    public static final Boolean CLEAR_COOKIES = false;
    /** To keep browser open after all tests.*/
    public static final Boolean HOLD_BROWSER_OPEN = false;
    /**
     * To make a screenshot after each test.
     * Screenshots are located at DimitriQA/build/screenshots
     **/
    public static final Boolean MAKE_SCREENSHOT_FOR_ALLURE = true;
    /**
     * After each run the reports and screenshots will be deleted.
     * The target folders for clearing are DimitriSamosiuk/build
     * and DimitriSamosiuk/../allure-results
     **/
    public static final Boolean CLEAR_TEST_REPORT_AND_SCREENSHOT_DIRECTORY = false;
}
