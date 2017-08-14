package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class OrderDetailsPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_edit_order_button() {
        kcEnginePage.getOrderDetailsPanel().clickEditOrderLnk();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_codes_button(){
        kcEnginePage.getOrderDetailsPanel().clickCodesLnk();
    }

    @Step
    public String get_product_name() {
        return kcEnginePage.getOrderDetailsPanel().getProductName();
    }

    @Step
    public String get_schema_name() {
        return kcEnginePage.getOrderDetailsPanel().getSchema();
    }
}
