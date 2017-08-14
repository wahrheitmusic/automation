package core.webelement.elements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.InputField;

/**
 * Created by Someone on 16.01.2017.
 */

public class InputFieldImpl extends AbstractElement implements InputField {

    protected InputFieldImpl(WebElement wrappedElement, WebDriver driver) {
        super(wrappedElement, driver);
    }

    @Override
    public void type(final String value) {
        weFacade.clear();
        weFacade.sendKeys(value);
    }
}
