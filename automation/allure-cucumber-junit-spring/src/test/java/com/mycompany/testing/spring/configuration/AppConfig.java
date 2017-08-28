package com.mycompany.testing.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by romanlapii on 8/28/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.mycompany.testing.steps"})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String env = System.getProperty("env", "qa");
        String commonPropertiesFileLocation = "./commonproperties/common.properties";
        String envPropertiesFileLocation = "./env/" + env + "/project.properties";

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource(commonPropertiesFileLocation),
                new ClassPathResource(envPropertiesFileLocation));

        return configurer;
    }
}
