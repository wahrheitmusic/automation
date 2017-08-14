package core.webdriver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WebDriverForPanel implements WebDriver, HasInputDevices {

    private WebDriver wrappedDriver;
    private WebElement wrappedElement;

    public WebDriverForPanel(final WebElement wrappedElement, final WebDriver wrappedDriver) {
        this.wrappedDriver = wrappedDriver;
        this.wrappedElement = wrappedElement;
    }

    @Override
    public void get(String url) {
        wrappedDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return wrappedDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return wrappedDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrappedElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return wrappedElement.findElement(by);
    }

    @Override
    public String getPageSource() {
        return wrappedDriver.getPageSource();
    }

    @Override
    public void close() {
        wrappedDriver.close();
    }

    @Override
    public void quit() {
        wrappedDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return wrappedDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return wrappedDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return wrappedDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return wrappedDriver.navigate();
    }

    @Override
    public Options manage() {
        return wrappedDriver.manage();
    }

    @Override
    public Keyboard getKeyboard() {
        return ((RemoteWebDriver) wrappedDriver).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((RemoteWebDriver) wrappedDriver).getMouse();
    }
}
