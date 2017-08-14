package net.kezzler.ssp.steps.ssp.products;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class ProductMetadataSchemaPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_new_product_metadata_schema_button(){
        kcEnginePage.getMetadataSchemasPanel().clickCreateSchemaLnk();
    }
}
