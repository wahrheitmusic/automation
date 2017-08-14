package net.kezzler.ssp.pageobject.ssp.login.panels;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.pageobject.AbstractPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LogInFormPanel extends AbstractPanel {

    @FindBy(xpath = ".//input[@id='username']")
    private WebElementFacade usernameInpt;
    @FindBy(xpath = ".//input[@id='password']")
    private WebElementFacade passwordInpt;
    @FindBy(xpath = ".//button[@id='sign-in-button']")
    private WebElementFacade signinBtn;

    public LogInFormPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        super(panelBaseLocation, page);
    }

    public void enterUsername(final String username) {
        usernameInpt.type(username);
    }

    public void enterPassword(final String password) {
        passwordInpt.type(password);
    }

    public void clickSigninBtn() {
        signinBtn.click();
    }
}
