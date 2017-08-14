package net.kezzler.ssp.pageobject.ssp.kcengine.panels.header;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SerializationDropDownPanel extends AbstractPanel {

    @FindBy(xpath = ".//a[contains(@id, 'order')]")
    private WebElementFacade ordersLnk;
    @FindBy(xpath = ".//a[@id='kcengine-menu-product']")
    private WebElementFacade productsLnk;
    @FindBy(xpath = ".//a[@id='kcengine-menu-metadata-schema']")
    private WebElementFacade metadataSchemaLnk;

    public SerializationDropDownPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void clickOrdersLnk() {
        ordersLnk.click();
        getPage().waitForAjaxToComplete();
    }

    public void clickMedataSchemasLnk() {
        metadataSchemaLnk.click();
    }

    public void clickProductsLnk() {
        productsLnk.click();
        getPage().waitForAjaxToComplete();
    }
}
