package commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/***
 * If test flakes, this repeater makes 3 retries.
 * Only the 3rd fail fall the test down. 1st and 2nd fails marked as a miss.
 ***/
public class FlakingTestRepeater implements IRetryAnalyzer {
    private static final int MAX_RETRY = 3;
    private int actualRetry = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (actualRetry < MAX_RETRY) {
            actualRetry++;
            return true;
        } else {
            return false;
        }
    }
}
