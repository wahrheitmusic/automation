package com.mycompany.testing.stepdefinition;

import com.mycompany.testing.spring.configuration.CucumberConfig;
import com.mycompany.testing.steps.MidLayerSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.yandex.qatools.allure.annotations.Attachment;

@ContextConfiguration(classes = {CucumberConfig.class})
public class StepsDefinition {

    int a;
    int b;
    int c;
    int sum;

    @Autowired
    private MidLayerSteps midLayerSteps;

    @Given("^first digit (\\d+)$")
    public void firstDigit(int digit) throws Throwable {
        a = digit;
    }

    @And("^second digit (\\d+)$")
    public void secondDigit(int digit) throws Throwable {
        b = digit;
    }

    @When("^I multiply it$")
    public void multiply() throws Throwable {
        sum = a + b + c;
    }

    @Then("^sum is (\\d+)$")
    public void sum(int result) throws Throwable {
        Assert.assertEquals(result, sum);
    }


    @Attachment()
    private byte[] createAttachment(String att) {
        String content = att;
        return content.getBytes();
    }

    @Given("^First step using di container$")
    public void firstStepAsDiContainer() {
        Assert.assertEquals(true, midLayerSteps.firstStepUSingComponenet());
    }

}
