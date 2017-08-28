package com.mycompany.testing.steps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

@Component
public class MidLayerSteps {

    @Value("${common.property}")
    private String commonProperties;
    @Value("${project.property}")
    private String projectProperties;

    public String getCommonProperties(){
        return commonProperties;
    }

    public String getProjectProperties(){
        return projectProperties;
    }

    @Step
    public boolean firstStepUSingComponenet() {
        return true;
    }
}
