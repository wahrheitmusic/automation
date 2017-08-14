package core.webelement.elements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.Link;

/**
 * Created by Someone on 18.01.2017.
 */
public class LinkImpl extends AbstractElement implements Link {

    protected LinkImpl(WebElement wrappedElement, WebDriver driver) {
        super(wrappedElement, driver);
    }

    @Override public void click() {
        weFacade.click();
    }

    @Override public void checkHref() {
        weFacade.getAttribute("href");
    }
}
