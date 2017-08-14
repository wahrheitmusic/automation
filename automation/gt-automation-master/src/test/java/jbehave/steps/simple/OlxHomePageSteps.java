package jbehave.steps.simple;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import pageobject.site.olx.home.OlxHomePage;
import pageobject.site.olx.payment.OlxPaymentPage;

/**
 * Created by Someone on 15.01.2017.
 */
public class OlxHomePageSteps extends AbstractSteps {

    public OlxHomePageSteps(final WebDriver driver) {
        super(driver);
    }

    OlxHomePage olxHomePage = new OlxHomePage(getDriver());

    public void openOlxHomePage() {
        olxHomePage.open();
    }

    public void clickPostNewAdBtn() {
        olxHomePage.getHeaderPanel().clickPostNewAdLink();
    }
}
