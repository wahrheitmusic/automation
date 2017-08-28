package com.mycompany.testing.stepdefinition;

import com.mycompany.testing.spring.configuration.CucumberConfig;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by romanlapii on 8/28/17.
 */
@ContextConfiguration(classes = { CucumberConfig.class })
public class GenericDefinitionSteps {
    @Before
    public void beforeScenario() {
        System.out.println("BEFORE");
    }
    @After
    public void afterScenario() {
        System.out.println("AFTER");
    }
}
