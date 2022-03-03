package pages.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final By ENGLISH_COPYRIGHT_ON_THE_FOOTER = By.xpath("//div[...]");
    private final By RUSSIAN_COPYRIGHT_ON_THE_FOOTER = By.xpath("//div[...]");


}
