package core.webelement.elements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.Button;

/**
 * Created by Someone on 16.01.2017.
 */
public class ButtonImpl extends AbstractElement implements Button {

    protected ButtonImpl(WebElement wrappedElement, WebDriver driver) {
        super(wrappedElement, driver);
    }

    @Override
    public void click() {
        weFacade.click();
    }
}
