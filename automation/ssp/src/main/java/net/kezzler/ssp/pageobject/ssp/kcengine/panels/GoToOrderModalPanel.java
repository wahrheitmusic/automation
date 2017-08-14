package net.kezzler.ssp.pageobject.ssp.kcengine.panels;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class GoToOrderModalPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@type='number']")
    private WebElementFacade orderIdInput;
    @FindBy(xpath = ".//button[contains(@ng-click, 'goto')]")
    private WebElementFacade goToOrderBtn;
    @FindBy(xpath = ".//button[contains(@ng-click, 'cancel')]")
    private WebElementFacade cancelBtn;

    public GoToOrderModalPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void typeIntoOrderIdInpt(final String orderId) {
        orderIdInput.type(orderId);
    }

    public void clickGoToOrderBtn() {
        goToOrderBtn.click();
    }

    public void clickCancelBtn() {
        cancelBtn.click();
    }

}
