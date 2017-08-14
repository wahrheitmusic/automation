package net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UploadModalPanel extends AbstractPanel {

    @FindBy(xpath = "//input[@id='uploadFileName']")
    private WebElementFacade uploadInput;
    @FindBy(xpath = ".//button[@ng-click='upload(file)']")
    private WebElementFacade uploadBtn;

    public UploadModalPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void sendFileToUpload(final String absoluteFilePath) {
        uploadInput.sendKeys(absoluteFilePath);
    }

    public void clickUploadBtn() {
        uploadBtn.click();
    }
}
