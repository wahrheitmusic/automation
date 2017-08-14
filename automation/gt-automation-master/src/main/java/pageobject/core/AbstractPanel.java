package pageobject.core;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.webdriver.WebDriverForPanel;
import core.webelement.factory.ExtendedFieldDecorator;
import core.webelement.elements.WebElementFacade;

/**
 * Created by Someone on 15.01.2017.
 */
public abstract class AbstractPanel {
    @FindBy(xpath = ".")
    private WebElementFacade panelBase;

    private final AbstractPage page;

    protected AbstractPage getPage() {
        return page;
    }

    private final WebDriverForPanel webDriverForPanel;

    public AbstractPanel(final WebElementFacade panelBase, final AbstractPage page) {
        this.page = page;
        page.waitFor(v -> panelBase.isVisible());
        this.webDriverForPanel = new WebDriverForPanel(panelBase, page.getDriver());
        PageFactory.initElements(new ExtendedFieldDecorator(webDriverForPanel), this);
    }

    public List<WebElementFacade> findAllByXpath(final String xpath) {
        return panelBase.findAllByXpath(xpath);
    }

    public WebElementFacade findByXpath(final String xpath) {
        return panelBase.findByXpath(xpath);
    }

    public void waitForJquery() {
        page.waitForJquery();
    }
}
