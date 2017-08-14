package net.kezzler.ssp.definitionsteps.ssp;

import net.kezzler.ssp.engine.session.Session;
import net.kezzler.ssp.steps.ssp.login.LogInPageSteps;
import net.kezzler.ssp.steps.ws.ssp.SspRestClientSteps;
import net.kezzler.ssp.utils.FrameworkRandomUtils;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInDefinitionSteps {
    private static final Logger LOG = LoggerFactory.getLogger(LogInDefinitionSteps.class.getSimpleName());


    @Steps
    private LogInPageSteps logInPageSteps;
    @Steps
    private SspRestClientSteps sspRestClientsteps;

    @Given("SSP-UI-User is logged into the system")
    public void user_logged_into_system() {
        logInPageSteps.log_into_ssp(Session.getDefaultUser());
    }

    @Given("SSP-WS-User is logged into the system")
    public void ws_user_logged_into_system() {
        sspRestClientsteps.send_login_request(Session.getDefaultUser());
    }
}
