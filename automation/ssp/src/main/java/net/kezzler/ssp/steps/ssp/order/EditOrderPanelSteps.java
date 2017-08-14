package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class EditOrderPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void select_product(final String productName) {
        kcEnginePage.getEditOrderPanel().selectProduct(productName);
    }

    @Step
    public void click_save_button() {
        kcEnginePage.getEditOrderPanel().clickSaveBtn();
    }

    @Step
    public void select_schema(final String schmaName) {
        kcEnginePage.getEditOrderPanel().selectSchema(schmaName);
    }
}
