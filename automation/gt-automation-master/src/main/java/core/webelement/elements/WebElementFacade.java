package core.webelement.elements;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * Created by Someone on 22.01.2017.
 */
public interface WebElementFacade extends Element, WebElement {
    void waitUntilVisible();

    void waitUntilNotVisible();

    WebElementFacade findByXpath(final String xpath);

    List<WebElementFacade> findAllByXpath(final String xpath);

    void hover();
}
