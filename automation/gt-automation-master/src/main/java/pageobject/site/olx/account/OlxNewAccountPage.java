package pageobject.site.olx.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.webelement.elements.WebElementFacade;
import pageobject.core.annotations.At;
import pageobject.core.annotations.DefaultUrl;
import pageobject.site.olx.AbstractOlxPage;
import pageobject.site.olx.account.pannels.LoginPanel;

/**
 * Created by Someone on 15.01.2017.
 */
@DefaultUrl(value = "account")
@At(values = ".*/account.*")
public class OlxNewAccountPage extends AbstractOlxPage {

    @FindBy(xpath = ".//li[@data-content = 'login']")
    private WebElementFacade loginPanelBase;

    public OlxNewAccountPage(WebDriver driver) {
        super(driver);
    }

    public LoginPanel getLoginPanel() {
        return new LoginPanel(loginPanelBase, this);
    }
}
