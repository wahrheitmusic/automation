package net.kezzler.ssp.pageobject.ssp.kcengine.panels.products;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.OrderWebItem;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.ProductWebItem;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class ProductsListPanel extends AbstractPanel {
    public ProductsListPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public List<ProductWebItem> getProductWebItemsList() {
        return initItems(ProductWebItem.class, getPanelBase().thenFindAll(".//tbody/tr"));
    }
}
