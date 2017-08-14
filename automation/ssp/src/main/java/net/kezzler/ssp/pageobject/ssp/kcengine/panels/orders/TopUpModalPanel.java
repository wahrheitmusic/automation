package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TopUpModalPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@id='top-up-size-input']")
    private WebElementFacade topUpSizeInpt;
    @FindBy(xpath = ".//input[@id='generate-codes-name-input']")
    private WebElementFacade nameInpt;
    @FindBy(xpath = ".//button[@id='top-up-button']")
    private WebElementFacade topUpBtn;

    public TopUpModalPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void typeIntoTopUpSizeInpt(final String text) {
        topUpSizeInpt.type(text);
    }

    public void typeIntoNameInpt(final String text) {
        nameInpt.type(text);
    }

    public void clickTopUpBtn() {
        topUpBtn.click();
    }

    public boolean isTopUpBtnDisabled() {
        return !topUpBtn.isEnabled();
    }
}
