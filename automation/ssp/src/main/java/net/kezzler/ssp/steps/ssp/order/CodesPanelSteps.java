package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class CodesPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_top_up_button() {
        kcEnginePage.getCodesPanel().clickTopUpBtn();
    }

    @Step
    public void click_upload_button() {
        kcEnginePage.getCodesPanel().cickUploadBtn();
        kcEnginePage.waitForAjaxToComplete();
    }

    @Step
    public int get_count_of_codes() {
        return kcEnginePage.getCodesPanel().getCodesItemsList().size();
    }

    @Step
    public void select_upload_file_on_upload_modal(final String aboluteFilePath) {
        kcEnginePage.getUploadModalPanel().sendFileToUpload(aboluteFilePath);
    }

    @Step
    public void click_upload_button_on_upload_modal() {
        kcEnginePage.getUploadModalPanel().clickUploadBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_outside_upload_modal(){
        kcEnginePage.getUploadModalPanel().clickOutside();
        kcEnginePage.waitForBackDrop();
    }
}
