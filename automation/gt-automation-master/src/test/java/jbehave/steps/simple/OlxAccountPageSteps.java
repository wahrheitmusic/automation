package jbehave.steps.simple;

import org.openqa.selenium.WebDriver;

import pageobject.site.olx.account.OlxNewAccountPage;
import pageobject.site.olx.account.pannels.LoginPanel;

/**
 * Created by Someone on 15.01.2017.
 */
public class OlxAccountPageSteps extends AbstractSteps {
    public OlxAccountPageSteps(final WebDriver driver) {
        super(driver);
    }

    private OlxNewAccountPage olxNewAccountPage = new OlxNewAccountPage(getDriver());

    public void authorise(final String login, final String password) {
        LoginPanel loginPanel = olxNewAccountPage.getLoginPanel();
        loginPanel.typeEmail(login);
        loginPanel.typePassword(password);
        loginPanel.clickLoginBtn();
    }
}
