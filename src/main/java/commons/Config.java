package commons;
public class Config {


    /*** This is a class for WebDriver and browser behaviour setups.**/

    /**
     * Specify the browser name and operating system for tests:
     * CHROME_WINDOWS
     * CHROME_WINDOWS_HEADLESS
     * CHROME_LINUX
     * FIREFOX_WINDOWS
     **/
    public static final String PLATFORM_AND_BROWSER = "CHROME_WINDOWS";

    /** Clear all cookies and cache after each interaction.*/
    public static final Boolean CLEAR_COOKIES = false;
    /** To keep browser open after all tests.*/
    public static final Boolean HOLD_BROWSER_OPEN = false;
    /**
     * To make a screenshot after each test.
     * Screenshots are located at DimitriQA/build/screenshots
     **/
    public static final Boolean MAKE_SCREENSHOTS = true;
}
