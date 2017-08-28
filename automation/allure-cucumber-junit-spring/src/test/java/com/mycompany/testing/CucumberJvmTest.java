package com.mycompany.testing;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Viktor Sidochenko viktor.sidochenko@gmail.com
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"."}, glue = "com.mycompany.testing.stepdefinition")
public class CucumberJvmTest {

}
