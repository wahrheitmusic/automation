package net.kezzler.ssp.pageobject.ssp.kcengine.panels.products;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateProductPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@id='productName-input']")
    private WebElementFacade productNameInpt;
    @FindBy(xpath = ".//input[@id='alias-input']")
    private WebElementFacade aliasInpt;
    @FindBy(xpath = ".//input[@id='gtin-input']")
    private WebElementFacade gtinInpt;
    @FindBy(xpath = ".//input[@id='productId-input']")
    private WebElementFacade productIdInpt;
    @FindBy(xpath = ".//button[@id='save-button']")
    private WebElementFacade saveBtn;

    public CreateProductPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
        saveBtn.waitUntilVisible();
    }

    public void typeIntoProductNameInpt(final String text) {
        productNameInpt.type(text);
    }

    public void typeIntoAliasInpt(final String text) {
        aliasInpt.type(text);
    }

    public void typeIntoGtinInpt(final String text) {
        gtinInpt.type(text);
    }

    public void typeIntoProductIdInpt(final String text) {
        productIdInpt.type(text);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

}
