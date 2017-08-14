package net.kezzler.ssp.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.kezzler.ssp.ProjectProperties;
import net.kezzler.ssp.engine.pagedata.PageDataListFactory;
import net.kezzler.ssp.engine.session.Session;
import net.kezzler.ssp.engine.session.dictionary.Key;
import net.kezzler.ssp.utils.wait.PageWaits;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class.getSimpleName());
    protected final EnvironmentVariables environmentVariables;
    private int addingJqueryRetryCount = 0;

    public AbstractPage(final WebDriver driver) {
        super(driver);
        environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
    }

    @Override
    public void addJQuerySupport() {
        try {
            super.addJQuerySupport();
        } catch (WebDriverException e) {
            // wait max 10 sec.
            if (addingJqueryRetryCount++ > 10 || !e.getMessage().contains("jQuery is not defined")) {
                addingJqueryRetryCount = 0;
                throw e;
            }
            waitFor(1000);
            addJQuerySupport();
        }
    }

    @WhenPageOpens
    public void eventOpened() {
        String browserProvidedType = environmentVariables.getProperty(ProjectProperties.Key.WEB_DRIVER.toString());
        if (Session.get(Key.BROWSER_FIRST_OPEN) == null) {
            if ("firefox".equalsIgnoreCase(browserProvidedType)) {
                maximizeBrowser();
            }
            Session.put(Key.BROWSER_FIRST_OPEN, 1);
        }
    }

    public boolean isElementVisible(final boolean expectedResult, WebElementFacade element) {
        boolean result = false;
        if (expectedResult) {
            result = element.isVisible();
        } else {
            setImplicitTimeout(50, TimeUnit.MILLISECONDS);
            result = element.isPresent() && element.isCurrentlyVisible();
            resetImplicitTimeout();
        }
        return result;
    }

    public void waitForAjaxToComplete() {
        PageWaits.waitForAjaxToComplete(this);
    }

    public <T> List<T> initItems(final Class clazz, final List<WebElementFacade> bases) {
        return PageDataListFactory.getInstance().getElementsNamed(clazz, bases, this);
    }

    private void maximizeBrowser() {
        final Window browserWindow = getDriver().manage().window();
        browserWindow.maximize();
        LOG.info("browser got maximized, checking dimensions...");
        Dimension actualDimension = browserWindow.getSize();
        final Integer actualHeight = actualDimension.getHeight();
        final Integer actualWidth = actualDimension.getWidth();
        LOG.info("width: {}, height: {}", actualWidth, actualHeight);
    }
}
