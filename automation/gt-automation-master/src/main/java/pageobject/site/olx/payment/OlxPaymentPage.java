package pageobject.site.olx.payment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.webelement.elements.WebElementFacade;
import pageobject.core.annotations.At;
import pageobject.site.olx.AbstractOlxPage;

/**
 * Created by Someone on 22.01.2017.
 */

@At(values = ".*/payment.*")
public class OlxPaymentPage extends AbstractOlxPage {

    @FindBy(xpath = "//div[contains(@class, 'info messagebox')]//strong")
    private WebElementFacade successMsg;

    public OlxPaymentPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMesssage() {
        return successMsg.getText();
    }
}
