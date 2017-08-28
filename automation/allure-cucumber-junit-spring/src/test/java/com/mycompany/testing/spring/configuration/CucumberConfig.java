package com.mycompany.testing.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by romanlapii on 8/28/17.
 */

@Configuration
@Import(AppConfig.class)
@ComponentScan(basePackages = {"com.mycompany.testing.steps"})
public class CucumberConfig {
}
