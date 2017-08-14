package net.kezzler.ssp.utils.web;

import net.kezzler.ssp.pageobject.AbstractPage;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.assertj.core.api.Assertions.fail;

public class AlertHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AlertHandler.class.getSimpleName());

    public static void confirmBrowserAlertWithWaiting(final AbstractPage page) {
        try {
            page.waitFor(ExpectedConditions.alertIsPresent());
            page.getDriver().switchTo().alert().accept();
            page.getDriver().switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
            LOG.error("Modal dialog did not appear", e);
            fail("Modal dialog is expected, but did not appear.");
        }
    }

    public static boolean isAlertPresent(final AbstractPage page) {
        try {
            page.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}
