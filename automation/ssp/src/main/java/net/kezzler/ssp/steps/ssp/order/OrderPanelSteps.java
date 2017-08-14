package net.kezzler.ssp.steps.ssp.order;

import java.util.List;
import java.util.stream.Collectors;

import net.kezzler.ssp.domain.Order;
import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.OrderWebItem;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class OrderPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public List<Order> get_visible_orders() {
        List<OrderWebItem> orderWebItems = kcEnginePage.getOrdersPanel().getOrdersListPanel().getOrderWebItemsList();
        return orderWebItems.stream().map(orderItem -> new Order(orderItem.getDescription(), orderItem.getOrderId(),
                orderItem.getOwner(), new Product(orderItem.getProduct()))).collect(Collectors.toList());
    }

    @Step
    public void click_new_order_btn() {
        kcEnginePage.getOrdersPanel().clickNewOrderBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_go_to_order_button() {
        kcEnginePage.getOrdersPanel().clickGoToOrderBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_refresh_button() {
        kcEnginePage.getOrdersPanel().clickRefreshBtn();
    }
}
