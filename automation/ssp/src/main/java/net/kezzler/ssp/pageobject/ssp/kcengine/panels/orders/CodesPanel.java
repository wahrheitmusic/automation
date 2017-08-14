package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import java.util.List;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.webitems.CodesWebItem;
import net.kezzler.ssp.utils.wait.PageWaits;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CodesPanel extends AbstractPanel {

    @FindBy(xpath = ".//button[@id='codes-top-up-button']")
    private WebElementFacade topUpBtn;
    @FindBy(xpath = ".//button[@ng-click='uploadModal.open();']")
    private WebElementFacade uploadBtn;

    public CodesPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public List<CodesWebItem> getCodesItemsList() {
        return initItems(CodesWebItem.class, getPanelBase().thenFindAll(".//tr[contains(@id,'codes-tableid')]"));
    }

    public void cickUploadBtn() {
        uploadBtn.click();
    }

    public void clickTopUpBtn() {
        topUpBtn.click();
    }

}
