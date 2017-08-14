package jbehave.steps.simple;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

import pageobject.core.AbstractPage;

/**
 * Created by Someone on 14.01.2017.
 */
public abstract class AbstractSteps {
    private final WebDriver driver;

    public AbstractSteps(final WebDriver driver) {
        this.driver = driver;
    }

    protected <T extends AbstractPage> void checkAt(final T page) {
        assertThat(page.isOpened()).as("Current page is not %s", page.getClass().getSimpleName()).isTrue();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
