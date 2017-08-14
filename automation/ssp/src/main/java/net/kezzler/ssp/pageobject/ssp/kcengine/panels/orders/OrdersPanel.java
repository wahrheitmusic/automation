package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OrdersPanel extends AbstractPanel {

    @FindBy(xpath = ".//button[@id='create-new-order']")
    private WebElementFacade newOrderBtn;
    @FindBy(xpath = ".//button[contains(@ng-click,'gotoDialog')]")
    private WebElementFacade goToOrderBtn;
    @FindBy(xpath = ".//button[contains(@ng-click,'reload')]")
    private WebElementFacade refreshBtn;
    @FindBy(xpath = ".//table[contains(@class,'table')]")
    private WebElementFacade ordersListPanelBase;

    public OrdersPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public OrdersListPanel getOrdersListPanel() {
        ordersListPanelBase.waitUntilVisible();
        return new OrdersListPanel(ordersListPanelBase, getPage());
    }

    public void clickNewOrderBtn() {
        newOrderBtn.click();
    }

    public void clickGoToOrderBtn() {
        goToOrderBtn.click();
    }

    public void clickRefreshBtn() {
        refreshBtn.click();
    }
}
