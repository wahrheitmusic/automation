package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class NewOrderModalPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void enter_description(final String description) {
        kcEnginePage.getNewOrderModalPanel().typeIntoNameInput(description);
    }

    @Step
    public void click_create_btn() {
        kcEnginePage.getNewOrderModalPanel().clickCreateBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_cancel_btn() {
        kcEnginePage.getNewOrderModalPanel().clickCancelBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_section_checkbox() {
        kcEnginePage.getNewOrderModalPanel().clickSection();
    }

}
