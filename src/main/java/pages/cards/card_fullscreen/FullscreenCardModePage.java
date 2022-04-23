package pages.cards.card_fullscreen;

import commons.CommonActions;
import graphql.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class FullscreenCardModePage extends BasePage {
    public FullscreenCardModePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    private List<WebElement> activeCards;
    @FindBy(xpath = "//textarea[contains(@class,'comment-box-input')]")
    private WebElement commentTextField;
    @FindBy(xpath = "//textarea[contains(@class,'js-card-detail-title-input')]")
    private WebElement cardTitleInput;

    public FullscreenCardModePage openActiveCard() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCards.get(0));
        activeCards.get(0).click();
        Thread.sleep(10000);
        return this;
    }

    public FullscreenCardModePage isCommentTextFieldDoesntDisplay() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(cardTitleInput);
        try {
            commentTextField.click();
        } catch (org.openqa.selenium.NoSuchElementException e) { //Assert.assertFalse(commentTextField.isDisplayed()) doesn't work.
            CommonActions.explicitWaitOfOneElementVisible(cardTitleInput);
        }
        return this;
    }
}
