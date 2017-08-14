package net.kezzler.ssp.steps.ssp.products;

import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class CreateProductPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void enter_product_name(final String productName) {
        kcEnginePage.getCreateProductsPanel().typeIntoProductNameInpt(productName);
    }

    @Step
    public void enter_alias(final String alias) {
        kcEnginePage.getCreateProductsPanel().typeIntoAliasInpt(alias);
    }

    @Step
    public void enter_gtin(final String gtin) {
        kcEnginePage.getCreateProductsPanel().typeIntoGtinInpt(gtin);
    }

    @Step
    public void enter_product_id(final String productId) {
        kcEnginePage.getCreateProductsPanel().typeIntoProductIdInpt(productId);
    }

    @Step
    public void click_save_button() {
        kcEnginePage.getCreateProductsPanel().clickSaveBtn();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public Product create_product_with_random_values() {
        Product product = Product.getRandomNewProduct();
        enter_product_name(product.getName());
        enter_alias(product.getAlias());
        enter_gtin(String.valueOf(product.getGtin()));
        enter_product_id(String.valueOf(product.getCustomerProductReference()));
        click_save_button();
        return product;
    }
}
