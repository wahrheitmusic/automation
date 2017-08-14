package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OrderDetailsPanel extends AbstractPanel {

    @FindBy(xpath = ".//a[@id='edit-order-button']")
    private WebElementFacade editOrderLnk;
    @FindBy(xpath = ".//span[@id='product-name']")
    private WebElementFacade productName;
    @FindBy(xpath = ".//em[@ng-hide='order.metadataSchema']/parent::div")
    private WebElementFacade schema;
    @FindBy(xpath = ".//a[@id='code-order-button']")
    private WebElementFacade codesLnk;

    public OrderDetailsPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void clickEditOrderLnk() {
        editOrderLnk.click();
    }

    public void clickCodesLnk() {
        codesLnk.waitUntilClickable();
        codesLnk.click();
    }

    public String getSchema() {
        return schema.getText();
    }

    public String getProductName() {
        return productName.getText();
    }
}
