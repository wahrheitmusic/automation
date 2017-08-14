package net.kezzler.ssp.pageobject.ssp.kcengine.webitems;

import net.kezzler.ssp.engine.pagedata.AbstractDataItem;
import net.kezzler.ssp.pageobject.AbstractPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductWebItem extends AbstractDataItem {

    @FindBy(xpath = "./td[@data-title-text='Name']")
    private WebElementFacade nameCell;
    @FindBy(xpath = "./td[@data-title-text='Alias']")
    private WebElementFacade aliasCell;
    @FindBy(xpath = "./td[@data-title-text='GTIN']")
    private WebElementFacade gtinCell;
    @FindBy(xpath = "./td[@data-title-text='Product ID']")
    private WebElementFacade productIdCell;

    public ProductWebItem(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public String getName() {
        return nameCell.getText();
    }

    public String getAlias() {
        return aliasCell.getText();
    }

    public String getGtin() {
        return gtinCell.getText();
    }

    public String getProductId() {
        return productIdCell.getText();
    }
}
