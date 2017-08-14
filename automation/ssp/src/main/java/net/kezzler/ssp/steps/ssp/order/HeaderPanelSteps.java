package net.kezzler.ssp.steps.ssp.order;

import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class HeaderPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_serialization_link() {
        kcEnginePage.getHeaderPanel().clickSerializatinLnk();
    }

    @Step
    public void click_orders_link() {
        kcEnginePage.getHeaderPanel().getSerializationDropDownPanel().clickOrdersLnk();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_products_link(){
        kcEnginePage.getHeaderPanel().getSerializationDropDownPanel().clickProductsLnk();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public void click_metadata_schemas_lnk(){
        kcEnginePage.getHeaderPanel().getSerializationDropDownPanel().clickMedataSchemasLnk();
        kcEnginePage.waitForBackDrop();
    }
}
