package jbehave.steps.simple;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

import pageobject.site.olx.payment.OlxPaymentPage;

/**
 * Created by Someone on 22.01.2017.
 */
public class OlxPaymentPageSteps extends AbstractSteps {

    private OlxPaymentPage olxPaymentPage = new OlxPaymentPage(getDriver());

    public OlxPaymentPageSteps(final WebDriver driver) {
        super(driver);
    }

    public void checkNewAdWasPublishedSuccessfully(final String expectedSuccessMsg) {
        checkAt(olxPaymentPage);
        assertThat(olxPaymentPage.getSuccessMesssage()).as("New AD was posted success msg is not as expected")
                .isEqualTo(expectedSuccessMsg);
    }
}