package net.kezzler.ssp.pageobject.ssp.kcengine.panels;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.header.SerializationDropDownPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Created by romanlapii on 2/25/17.
 */
public class HeaderPanel extends AbstractPanel {

    @FindBy(xpath = ".//a[@id='headermenu-serialization']")
    private WebElementFacade serializatinLnk;
    @FindBy(xpath = ".//a[@id='headermenu-serialization']/..//li")
    private WebElementFacade serializDropDownBase;

    public HeaderPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public SerializationDropDownPanel getSerializationDropDownPanel() {
        serializDropDownBase.waitUntilVisible();
        return new SerializationDropDownPanel(serializDropDownBase, getPage());
    }

    public void clickSerializatinLnk() {
        serializatinLnk.click();
    }
}
