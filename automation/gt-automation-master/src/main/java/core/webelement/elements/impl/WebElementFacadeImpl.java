package core.webelement.elements.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.FluentWait;

import core.webdriver.ProjectWaits;
import core.webelement.elements.WebElementFacade;

/**
 * Created by Someone on 22.01.2017.
 */
public class WebElementFacadeImpl implements WebElementFacade {
    private Duration implicitWaitTimeout = new Duration(
            Long.valueOf(System.getProperty("webdriver.implicitly.timeout")), TimeUnit.MILLISECONDS);
    private WebElement wrappedElement;
    private WebDriver driver;
    private FluentWait<WebDriver> wait;

    public WebElementFacadeImpl(final WebElement wrappedElement, final WebDriver driver) {
        this.wrappedElement = wrappedElement;
        this.driver = driver;
        this.wait = ProjectWaits.getWebDriverWait(implicitWaitTimeout.in(TimeUnit.SECONDS), driver);
    }

    @Override
    public void click() {
        wrappedElement.click();
    }

    @Override
    public void submit() {
        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        wrappedElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        return wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return wrappedElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return wrappedElement.isEnabled();
    }

    @Override
    public String getText() {
        return wrappedElement.getText();
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
    public boolean isDisplayed() {
        return wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return wrappedElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return wrappedElement.getScreenshotAs(target);
    }

    @Override
    public boolean isVisible() {
        try {
            WebElement se = wrappedElement;
            return se != null && se.isDisplayed();
        } catch (ElementNotVisibleException var2) {
            return false;
        } catch (NoSuchElementException var3) {
            return false;
        } catch (StaleElementReferenceException var4) {
            return false;
        }
    }

    @Override
    public void waitUntilVisible() {
        wait.until((Function<? super WebDriver, Boolean>) v -> isVisible());
    }

    @Override
    public void waitUntilNotVisible() {
        wait.until((Function<? super WebDriver, Boolean>) v -> !isVisible());
    }

    @Override
    public WebElementFacade findByXpath(final String xpath) {
        return wrapElement(wrappedElement.findElement(By.xpath(xpath)), driver);
    }

    @Override
    public List<WebElementFacade> findAllByXpath(final String xpath) {
        return wrappedElement.findElements(By.xpath(xpath)).stream().map(we -> wrapElement(we, driver))
                .collect(Collectors.toList());
    }

    @Override
    public void hover() {
        withActions().moveToElement(wrappedElement).perform();
    }

    private Actions withActions() {
        return new Actions(driver);
    }

    private WebElementFacadeImpl wrapElement(WebElement element, WebDriver driver) {
        return new WebElementFacadeImpl(element, driver);
    }
}
