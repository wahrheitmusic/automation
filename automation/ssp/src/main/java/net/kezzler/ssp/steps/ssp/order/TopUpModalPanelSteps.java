package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class TopUpModalPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void enter_to_up_size(final String size) {
        kcEnginePage.getTopUpModalPanel().typeIntoTopUpSizeInpt(size);
    }

    @Step
    public void enter_name(final String name) {
        kcEnginePage.getTopUpModalPanel().typeIntoNameInpt(name);
    }

    @Step
    public boolean is_top_up_button_disabled(){
        return kcEnginePage.getTopUpModalPanel().isTopUpBtnDisabled();
    }

    @Step
    public void click_top_up_button() {
        kcEnginePage.getTopUpModalPanel().clickTopUpBtn();
    }
}
