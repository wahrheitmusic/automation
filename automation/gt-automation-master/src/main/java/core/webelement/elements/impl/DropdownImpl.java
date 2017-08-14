package core.webelement.elements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.webelement.elements.DropDown;
import core.webelement.elements.WebElementFacade;

/**
 * Created by Someone on 22.01.2017.
 */
public class DropdownImpl extends AbstractElement implements DropDown {
    private final static String OPTIONS_BASE_XPATH = ".//ul";
    private final static String OPTION_XPATH = ".//li";

    protected DropdownImpl(WebElement wrappedElement, WebDriver driver) {
        super(wrappedElement, driver);
    }

    @Override
    public DropDown unfold() {
        weFacade.click();
        weFacade.findByXpath(OPTIONS_BASE_XPATH).waitUntilVisible();
        return this;
    }

    @Override
    public void selectAndClick(String value) {
        getOption(value).click();
    }

    @Override
    public void selectAndHover(String value) {
        getOption(value).hover();
    }

    private WebElementFacade getOption(String value) {
        return weFacade.findAllByXpath(OPTION_XPATH).stream().filter(opt -> opt.getText().equals(value)).findFirst()
                .orElseThrow(() -> new RuntimeException("No such option"));
    }
}
