package net.kezzler.ssp.steps.ssp.login;

import net.kezzler.ssp.domain.User;
import net.kezzler.ssp.pageobject.ssp.login.LoginPage;
import net.kezzler.ssp.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class LogInPageSteps extends AbstractSteps {

    private LoginPage loginPage;

    @Step
    public void open_login_page() {
        loginPage.open();
    }

    @Step
    public void enter_username(final String userName) {
        loginPage.getLogInFormPanel().enterUsername(userName);
    }

    @Step
    public void enter_password(final String password) {
        loginPage.getLogInFormPanel().enterPassword(password);
    }

    @Step
    public void click_signin_button() {
        loginPage.getLogInFormPanel().clickSigninBtn();
    }

    @Step
    public void log_into_ssp(final User user) {
        open_login_page();
        enter_username(user.getUserName());
        enter_password(user.getPassword());
        click_signin_button();
    }
}
