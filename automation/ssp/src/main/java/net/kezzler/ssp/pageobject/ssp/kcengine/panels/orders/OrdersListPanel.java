package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import java.util.List;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.OrderWebItem;
import net.serenitybdd.core.pages.WebElementFacade;

public class OrdersListPanel extends AbstractPanel {

    public OrdersListPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public List<OrderWebItem> getOrderWebItemsList() {
        return initItems(OrderWebItem.class, getPanelBase().thenFindAll(".//tbody/tr"));
    }
}
