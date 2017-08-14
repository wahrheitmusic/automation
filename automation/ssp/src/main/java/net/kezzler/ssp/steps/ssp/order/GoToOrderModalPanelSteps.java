package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class GoToOrderModalPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void enter_order_id(final String orderId) {
        kcEnginePage.getGoToOrderModalPanel().typeIntoOrderIdInpt(orderId);
    }

    public void click_go_to_button() {
        kcEnginePage.getGoToOrderModalPanel().clickGoToOrderBtn();
        kcEnginePage.waitForBackDrop();

    }
}
