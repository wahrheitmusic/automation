package core.webelement.elements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.Element;
import core.webelement.elements.WebElementFacade;

/**
 * Created by Someone on 16.01.2017.
 */
public abstract class AbstractElement implements Element {

    protected final WebElementFacade weFacade;
    protected final WebDriver driver;

    protected AbstractElement(final WebElement wrappedElement, final WebDriver driver) {
        this.weFacade = new WebElementFacadeImpl(wrappedElement, driver);
        this.driver = driver;
    }

    @Override
    public boolean isVisible() {
        return weFacade.isVisible();
    }
}
