package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.thucydides.core.annotations.Step;

public class MetadataSchemaPanelSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_create_new_metadata_schema_button() {
        kcEnginePage.getMetadataSchemasPanel().clickCreateSchemaLnk();
    }

    @Step
    public void enter_schema_name(final String text) {
        kcEnginePage.getCreateMetadataSchemaPanel().typeIntoNameInpt(text);
    }

    @Step
    public void enter_schema_description(final String text) {
        kcEnginePage.getCreateMetadataSchemaPanel().typeIntoSchemaDefinitionArea(text);
    }

    @Step
    public void click_save_button() {
        kcEnginePage.getCreateMetadataSchemaPanel().clickSaveBtn();
        kcEnginePage.waitForBackDrop();
    }
}
