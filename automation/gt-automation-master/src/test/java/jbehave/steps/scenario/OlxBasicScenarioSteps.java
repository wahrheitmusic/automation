package jbehave.steps.scenario;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import domain.models.json.ad.NewAdJsonModel;
import jbehave.dictionary.TableHeaders;
import jbehave.steps.simple.OlxAccountPageSteps;
import jbehave.steps.simple.OlxHomePageSteps;
import jbehave.steps.simple.OlxPaymentPageSteps;
import jbehave.steps.simple.OlxPostNewAdPageSteps;

/**
 * Created by Someone on 15.01.2017.
 */
public class OlxBasicScenarioSteps extends CommonScenarioSteps {

    OlxHomePageSteps olxHomePageSteps = new OlxHomePageSteps(getDriver());
    OlxAccountPageSteps olxAccountPageSteps = new OlxAccountPageSteps(getDriver());
    OlxPostNewAdPageSteps olxPostNewAdPageSteps = new OlxPostNewAdPageSteps(getDriver());
    OlxPaymentPageSteps olxPaymentPageSteps = new OlxPaymentPageSteps(getDriver());

    @Given("User opens OLX Home Page")
    public void userOpensOlxHomePage() {
        olxHomePageSteps.openOlxHomePage();
    }

    @Given("User clicks New Ad Button and proceeds with authorisation: $table")
    public void userClicksNewAdBtnAndAuthorizes(final ExamplesTable table) {
        Parameters parameters = table.getRowAsParameters(0);
        String login = parameters.valueAs(TableHeaders.LOGIN.toString(), String.class);
        String password = parameters.valueAs(TableHeaders.PASSWORD.toString(), String.class);
        olxHomePageSteps.clickPostNewAdBtn();
        olxAccountPageSteps.authorise(login, password);
    }

    @When("User publishes new Ad: $jsonModelPath")
    public void userPublishesNewAd(final NewAdJsonModel newAdJsonModel) {
        olxPostNewAdPageSteps.fillNewAd(newAdJsonModel);
        olxPostNewAdPageSteps.submitNewAd();
    }

    @Then("User sees '$successMsg' on PaymentPage")
    public void userSeersSuccessMsgObPaymentPage(final String successMsg) {
        olxPaymentPageSteps.checkNewAdWasPublishedSuccessfully(successMsg);
    }
}
