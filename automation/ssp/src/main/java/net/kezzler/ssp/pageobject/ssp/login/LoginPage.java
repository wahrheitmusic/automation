package net.kezzler.ssp.pageobject.ssp.login;

import net.kezzler.ssp.pageobject.ssp.AbstractSspPage;
import net.kezzler.ssp.pageobject.ssp.login.panels.LogInFormPanel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("/#/security/login")
@At(".*/#/security/login.*")
public class LoginPage extends AbstractSspPage {

    @FindBy(xpath = "//div[@class='log-in-form']")
    private WebElementFacade loginForPanelBase;


    public LoginPage(final WebDriver driver) {
        super(driver);
    }

    public LogInFormPanel getLogInFormPanel(){
        loginForPanelBase.waitUntilVisible();
        return new LogInFormPanel(loginForPanelBase, this);
    }
}
