package com.mycompany.testing;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by romanlapii on 8/26/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/firstfeature/difirst.feature"}, glue = "com.mycompany.testing.stepdefinition")
public class RerunRunner {
}
