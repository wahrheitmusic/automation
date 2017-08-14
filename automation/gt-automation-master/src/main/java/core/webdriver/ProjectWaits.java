package core.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public final class ProjectWaits {

    private ProjectWaits() {
    }

    public static FluentWait<WebDriver> getWebDriverWait(long timeoutInSeconds, WebDriver driver) {
        return (new WebDriverWait(driver, timeoutInSeconds)).pollingEvery(150, TimeUnit.MILLISECONDS);
    }
}
