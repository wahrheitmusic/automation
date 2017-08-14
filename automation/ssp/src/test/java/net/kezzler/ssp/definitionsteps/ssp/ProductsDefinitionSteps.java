package net.kezzler.ssp.definitionsteps.ssp;

import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.engine.session.Session;
import net.kezzler.ssp.steps.ssp.order.HeaderPanelSteps;
import net.kezzler.ssp.steps.ssp.products.CreateProductPanelSteps;
import net.kezzler.ssp.steps.ssp.products.ProductsPanelSteps;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

public class ProductsDefinitionSteps {

    @Steps
    private ProductsPanelSteps productsPanelSteps;
    @Steps
    private HeaderPanelSteps headerPanelSteps;
    @Steps
    private CreateProductPanelSteps createProductPanelSteps;

    @Given("SSP-UI-User opens products perspective")
    public void ui_open_products_perspective() {
        headerPanelSteps.click_serialization_link();
        headerPanelSteps.click_products_link();
    }

    @Given("SSP-UI-User opens Product Metadata Schemas view")
    public void ui_open_metadata_schemas_view(){
        headerPanelSteps.click_serialization_link();
        headerPanelSteps.click_metadata_schemas_lnk();
    }

    @When("SSP-UI-User creates a new product")
    public void ui_create_new_product() {
        productsPanelSteps.click_new_product_button();
        Product product = createProductPanelSteps.create_product_with_random_values();
        Session.addProductToList(product);
    }
}
