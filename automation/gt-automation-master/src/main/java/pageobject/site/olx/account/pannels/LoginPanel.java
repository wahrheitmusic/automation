package pageobject.site.olx.account.pannels;

import org.openqa.selenium.support.FindBy;

import core.webelement.elements.InputField;
import core.webelement.elements.WebElementFacade;
import pageobject.core.AbstractPage;
import pageobject.core.AbstractPanel;

/**
 * Created by Someone on 15.01.2017.
 */
public class LoginPanel extends AbstractPanel {
    @FindBy(xpath = ".//input[@id = 'userEmail']")
    private InputField userEmailInput;
    @FindBy(xpath = ".//input[@id = 'userPass']")
    private InputField userPassInput;
    @FindBy(xpath = ".//button[@id = 'se_userLogin']")
    private WebElementFacade loginBtn;

    public LoginPanel(WebElementFacade panelBase, AbstractPage page) {
        super(panelBase, page);
    }

    public void typeEmail(final String login) {
        userEmailInput.type(login);
    }

    public void typePassword(final String password) {
        userPassInput.type(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
