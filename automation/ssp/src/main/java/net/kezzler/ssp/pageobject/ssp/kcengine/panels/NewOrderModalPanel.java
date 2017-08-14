package net.kezzler.ssp.pageobject.ssp.kcengine.panels;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NewOrderModalPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@id='order-name-input']")
    private WebElementFacade nameInput;
    @FindBy(xpath = ".//input[@id='mode']")
    private WebElementFacade sectionedInput;
    @FindBy(xpath = ".//button[@id='create-order-button']")
    private WebElementFacade createBtn;
    @FindBy(xpath = ".//button[@id='cancel-create-order-button']")
    private WebElementFacade cancelBtn;

    public NewOrderModalPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void typeIntoNameInput(final String text) {
        nameInput.type(text);
    }

    public void clickSection() {
        sectionedInput.click();
    }

    public void clickCreateBtn() {
        createBtn.click();
        getPage().waitForAjaxToComplete();
    }

    public void clickCancelBtn() {
        cancelBtn.click();
        getPage().waitForAjaxToComplete();
    }
}
