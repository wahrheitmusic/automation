
package net.kezzler.ssp.engine.pagedata;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.pages.WebElementFacade;

public abstract class AbstractDataItem extends AbstractPanel {

    public AbstractDataItem(WebElementFacade panelBaseLocation, AbstractPage page) {
        super(panelBaseLocation, page);
    }

}