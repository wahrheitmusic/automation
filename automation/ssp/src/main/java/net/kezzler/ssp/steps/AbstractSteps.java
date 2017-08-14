package net.kezzler.ssp.steps;

import java.util.concurrent.TimeUnit;

import net.kezzler.ssp.ProjectProperties;
import net.kezzler.ssp.pageobject.AnyPage;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.util.EnvironmentVariables;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Fail.fail;

public abstract class AbstractSteps extends ScenarioSteps {

    protected static final String UNEXPECTED_PAGE_LOADED = "Unexpected Page is loaded: ";

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSteps.class.getSimpleName());
    private final EnvironmentVariables environmentVariables;
    private AnyPage anyPage;

    public AbstractSteps() {
        super();
        environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
    }

    @Step
    public <T extends PageObject> void wait_current_page_is(final Class<T> pageClass) {
        LOG.info("wait_current_page_is: {}", pageClass);

        final FluentWait<WebDriver> wait = new FluentWait<>(getDriver());
        wait.pollingEvery(500, TimeUnit.MILLISECONDS).withTimeout(
                environmentVariables.getPropertyAsInteger(ProjectProperties.Key.IMPLICIT_WAIT.toString() + 30000, 10000)
                        * 2,
                TimeUnit.MILLISECONDS);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(final WebDriver driver) {
                    if (getPages().isCurrentPageAt(pageClass)) {
                        anyPage.waitForAjaxToComplete();
                        LOG.info("Expected page was opened");
                        return true;
                    }
                    return false;
                }
            });
        } catch (TimeoutException te) {
            fail(UNEXPECTED_PAGE_LOADED + getDriver().getCurrentUrl(), te);
        }
    }
}
