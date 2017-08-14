package jbehave.steps.scenario;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;

import jbehave.session.Session;

/**
 * Created by Someone on 15.01.2017.
 */
public class BeforeAfterScenarioSteps extends CommonScenarioSteps {

    @BeforeScenario
    public void beforeScenario() {
        if (!System.getProperty("webdriver.driver").equals("chrome")) {
            getDriver().manage().window().maximize();
        }
        Session.initNewSession();
    }

    @AfterScenario
    public void afterScenario() {
        if (webDriverFactory.isInstantiated())
            getDriver().quit();
        webDriverFactory.reset();
        Session.resetSession();
    }

}
