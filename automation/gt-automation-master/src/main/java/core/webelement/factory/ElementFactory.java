package core.webelement.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.Element;

/**
 * Created by Someone on 16.01.2017.
 */
public interface ElementFactory {
    <E extends Element> E create(Class<E> elementClass, WebElement wrappedElement, WebDriver driver);
}