package net.kezzler.ssp.pageobject.ssp.kcengine.panels.products;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductsPanel extends AbstractPanel {
    @FindBy(xpath = ".//a[@id='add-new-product-button']")
    private WebElementFacade addNewProductLnk;
    @FindBy(xpath = ".//table[contains(@class,'table')]")
    private WebElementFacade productsListPanelBase;

    public ProductsPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void clickAddNewProductLnk() {
        addNewProductLnk.click();
    }

    public ProductsListPanel getProductsListPanel() {
        productsListPanelBase.waitUntilVisible();
        return new ProductsListPanel(productsListPanelBase, getPage());
    }
}
