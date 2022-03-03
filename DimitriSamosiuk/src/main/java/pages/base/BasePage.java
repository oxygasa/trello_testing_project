package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import static constants.Constant.TimeoutVariables.IMPLICIT_WAIT;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    /**
     * The method is to find element By locator.
     **/
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }
    /**
     * Refresh the page.
     **/
    public void refreshThePage(){
        driver.navigate().refresh();
    }

    /**
     * The method is to find element By locator and type text into it.
     **/
    public void typeText(By locator, String anyTextForInput) {
        driver.findElement(locator).sendKeys(anyTextForInput);
    }


    /*** Wait for visibility element in DOM model.*/
    public void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, IMPLICIT_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /*** Assert the element is actually displayed.*/
    public void assertElementIsDisplayed(By locator) {
        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }

    /***Assert the list of elements are actually displayed.*/
    public void assertElementsAreDisplayed(By locator) {
        List<WebElement> allElements = driver.findElements(locator);
        for (WebElement element : allElements) {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    /*** Assert the element contains text*/
    public void assertElementContainsText(By locator, String textForAssert) {
        WebElement element = driver.findElement(locator);
        String actualTextForAssertion = element.getText();
        Assert.assertEquals(actualTextForAssertion, textForAssert);
    }
}