package pageobject.site.olx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.webelement.elements.WebElementFacade;
import pageobject.core.AbstractPage;
import pageobject.site.olx.home.panels.HeaderPanel;

/**
 * Created by Someone on 15.01.2017.
 */
public abstract class AbstractOlxPage extends AbstractPage {

    @FindBy(xpath = ".//header[@id = 'header-container']")
    private WebElementFacade headerPanelBase;

    @FindBy(xpath = ".//div[@id = 'smsVerificationContainer']")
    private WebElementFacade smsVerificationContainer;

    public AbstractOlxPage(WebDriver driver) {
        super(driver);
    }

    public HeaderPanel getHeaderPanel() {
        return new HeaderPanel(headerPanelBase, this);
    }
}
