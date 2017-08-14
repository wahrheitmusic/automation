package pageobject.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

import core.webdriver.ProjectWaits;
import core.webelement.factory.ExtendedFieldDecorator;
import pageobject.core.annotations.At;
import pageobject.core.annotations.DefaultUrl;

/**
 * Created by Someone on 15.01.2017.
 */
public abstract class AbstractPage {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class.getSimpleName());
    private WebDriver driver;
    private Duration implicitWaitTimeout = new Duration(
            Long.valueOf(System.getProperty("webdriver.implicitly.timeout")), TimeUnit.MILLISECONDS);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public void open() {
        driver.get(getBaseUrl() + getDefaultUrl());
    }

    /**
     * DefaultUrl annotation must have %s placeholders to accept parameters
     * 
     * @param parameters
     */
    public void open(final String[] parameters) {
        driver.get(getBaseUrl() + format(getDefaultUrl(), parameters));
    }

    /**
     * Override this to change base url
     * 
     * @return
     */
    public String getBaseUrl() {
        return System.getProperty("webdriver.base.url");
    }

    /**
     * Uses {@link pageobject.core.annotations.DefaultUrl} to determine default URL
     * 
     * @return
     */
    public String getDefaultUrl() {
        DefaultUrl annotation = getClass().getAnnotation(DefaultUrl.class);
        if (null == annotation) {
            throw new RuntimeException("Page cannot be opened because it has no DefaultUrl");
        }
        return annotation.value();
    }

    /**
     * Uses {@link pageobject.core.annotations.At} to determine allowed locations
     *
     * @return
     */
    public boolean isOpened() {
        String[] locationRegExpArr = getClass().getAnnotation(At.class).values();
        for (String locRegExp : locationRegExpArr) {
            if (driver.getCurrentUrl().matches(locRegExp))
                return true;
        }
        return false;
    }

    private Duration getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitFor(final ExpectedCondition expectedCondition) {
        ProjectWaits.getWebDriverWait(implicitWaitTimeout.in(TimeUnit.SECONDS), driver).until(expectedCondition);
    }

    public void waitForJquery() {
        LOG.debug("jQuery loading started");
        try {
            waitFor(v -> {
                boolean b = (boolean) evaluateJavascript("return window.jQuery != undefined && jQuery.active == 0;");
                LOG.debug("jquery processing: {}", !b);
                return b;
            });
            LOG.debug("jQuery loading finished");
        } catch (TimeoutException te) {
            LOG.debug("jQuery wait exceeded timeout, proceeding anyway: {}", te.getMessage());
        }
    }

    public Object evaluateJavascript(final String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    public Actions withActions() {
        return new Actions(driver);
    }

    public void scrollBottom() {
        evaluateJavascript("window.scrollBy(0, document.documentElement.scrollHeight)");
    }
}
