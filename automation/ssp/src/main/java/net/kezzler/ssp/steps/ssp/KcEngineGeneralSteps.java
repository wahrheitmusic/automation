package net.kezzler.ssp.steps.ssp;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class KcEngineGeneralSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void open_modifying_url(final String[] params) {
        kcEnginePage.open(params);
    }

    @Step
    public boolean is_error_alert_visible(final boolean expectedResult) {
        return kcEnginePage.isErrorAlertVisible(expectedResult);
    }
}
