package net.kezzler.ssp.steps.ssp.products;

import java.util.List;
import java.util.stream.Collectors;

import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.pageobject.ssp.kcengine.KcEnginePage;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.ProductWebItem;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class ProductsPanelSteps extends AbstractSteps {

    private KcEnginePage kcEnginePage;

    @Step
    public void click_new_product_button() {
        kcEnginePage.getProductsPanel().clickAddNewProductLnk();
        kcEnginePage.waitForBackDrop();
    }

    @Step
    public List<Product> get_visible_products() {
        List<ProductWebItem> productWebItemsList = kcEnginePage.getProductsPanel().getProductsListPanel()
                .getProductWebItemsList();
        return productWebItemsList.stream()
                .map(productItem -> new Product(productItem.getName(), productItem.getAlias(),
                        Long.valueOf(productItem.getGtin()), Long.valueOf(productItem.getProductId())))
                .collect(Collectors.toList());
    }
}
