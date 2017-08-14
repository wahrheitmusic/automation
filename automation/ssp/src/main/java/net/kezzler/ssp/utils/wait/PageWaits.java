package net.kezzler.ssp.utils.wait;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Predicate;
import net.kezzler.ssp.pageobject.AbstractPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageWaits {

    private static final Logger LOG = LoggerFactory.getLogger(PageWaits.class.getSimpleName());

    private PageWaits() {
    }

    public static void waitForBackDrop(final WebElementFacade element, final int timeoutBefore,
            final int timeoutAfter) {
        LOG.info("looking for some permanent element");
        final FluentWait<WebElementFacade> waitBefore = new FluentWait<>(element);
        waitBefore.withTimeout(timeoutBefore, TimeUnit.SECONDS).pollingEvery(250, TimeUnit.MILLISECONDS);
        try {
            waitBefore.until(isVisibleElement(false));
        } catch (TimeoutException e) {
            LOG.debug("Not appeared exception: ", e);
            LOG.info("not appeared after {} seconds, skipping", timeoutBefore);
            return;
        }
        LOG.info("appeared");
        FluentWait<WebElementFacade> waitAfter = new FluentWait<>(element);
        waitAfter.withTimeout(timeoutAfter, TimeUnit.SECONDS).pollingEvery(250, TimeUnit.MILLISECONDS);
        waitAfter.until(isVisibleElement(false));
        LOG.info("wait complete");
    }

    public static void waitForAjaxToComplete(final AbstractPage page) {
        final Date startTime = new Date();
        try {
            page.waitFor(
                    (Boolean) -> page.evaluateJavascript("return window.jQuery != undefined && jQuery.active == 0;"));
            final Date endTime = new Date();
            LOG.info("ajax execution on page took about {} milliseconds", endTime.getTime() - startTime.getTime());
        } catch (org.openqa.selenium.TimeoutException te) {
            LOG.error("ajax wait exceeded timeout, proceeding with test anyway\nmessage: ", te);
        }
    }

    private static Predicate<WebElementFacade> isVisibleElement(final Boolean shouldBeVisible) {
        return (WebElementFacade element) -> shouldBeVisible ? element.isVisible() : element.isCurrentlyVisible();
    }

}
