package net.kezzler.ssp.pageobject.ssp.kcengine.webitems;

import net.kezzler.ssp.engine.pagedata.AbstractDataItem;
import net.kezzler.ssp.pageobject.AbstractPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OrderWebItem extends AbstractDataItem {
    @FindBy(xpath = "./td[@class='ng-binding'][1]")
    private WebElementFacade orderIdCell;
    @FindBy(xpath = "./td[contains(@class,'kz-order-name')]")
    private WebElementFacade descriptionCell;
    @FindBy(xpath = "./td[@class='ng-binding'][3]")
    private WebElementFacade ownerCell;
    @FindBy(xpath = "./td[@class='ng-binding'][2]")
    private WebElementFacade productCell;

    public OrderWebItem(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public String getOrderId() {
        return orderIdCell.getText();
    }

    public String getDescription() {
        return descriptionCell.getText();
    }

    public String getOwner() {
        return ownerCell.getText();
    }

    public String getProduct() {
        String text = productCell.getText();
        return text.equals("") ? null : text;
    }
}
