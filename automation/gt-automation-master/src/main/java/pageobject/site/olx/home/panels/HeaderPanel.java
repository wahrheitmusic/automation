package pageobject.site.olx.home.panels;

import core.webelement.elements.Link;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.webelement.elements.WebElementFacade;
import pageobject.core.AbstractPage;
import pageobject.core.AbstractPanel;

/**
 * Created by Someone on 15.01.2017.
 */
public class HeaderPanel extends AbstractPanel {

    @FindBy(xpath = ".//a[@id = 'postNewAdLink']")
    private Link postNewAdLink;

    public HeaderPanel(WebElementFacade panelBase, AbstractPage page) {
        super(panelBase, page);
    }

    public void clickPostNewAdLink() {
        postNewAdLink.click();
    }
}
