package net.kezzler.ssp.pageobject.ssp.kcengine.panels.products;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MetadataSchemaPanel extends AbstractPanel {

    @FindBy(xpath = ".//a[@id='create-metadata-schema-button']")
    private WebElementFacade createCchemaLnk;


    public MetadataSchemaPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void clickCreateSchemaLnk(){
        createCchemaLnk.click();
    }

}
