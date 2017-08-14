package net.kezzler.ssp.pageobject.ssp.kcengine.panels.products;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateMetadataSchemaPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@id='schemaName-input']")
    private WebElementFacade nameInput;
    @FindBy(xpath = ".//textarea[@id='schema-input']")
    private WebElementFacade schemaDefinitionTextarea;
    @FindBy(xpath = ".//button[@id='save-button']")
    private WebElementFacade saveBtn;

    public CreateMetadataSchemaPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void typeIntoNameInpt(final String text) {
        nameInput.type(text);
    }

    public void typeIntoSchemaDefinitionArea(final String text) {
        schemaDefinitionTextarea.type(text);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

}
