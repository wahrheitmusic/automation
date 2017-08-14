package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class EditOrderPanel extends AbstractPanel {

    @FindBy(xpath = ".//select[@id='product']")
    private WebElementFacade productDropDown;
    @FindBy(xpath = ".//button[@id='edit-order-save-button']")
    private WebElementFacade saveBtn;
    @FindBy(xpath = ".//select[@id='metadataSchema']")
    private WebElementFacade schemaDropDown;

    public EditOrderPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void selectProduct(final String productName) {
        productDropDown.selectByVisibleText(productName);
    }

    public void selectSchema(final String schemaName) {
        schemaDropDown.selectByVisibleText(schemaName);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }
}
