package net.kezzler.ssp.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.kezzler.ssp.commons.WebDriverAdaptor;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.locators.SmartElementLocatorFactory;
import net.thucydides.core.annotations.locators.SmartFieldDecorator;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.MobilePlatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class AbstractPanel {


    private final EnvironmentVariables environmentVariables;
    private AbstractPage page;
    private WebDriverAdaptor panelToWebDriver;

    @FindBy(xpath = ".")
    private WebElementFacade panelBase;

    public AbstractPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
        initPanel(panelBaseLocation, page);
    }
    public void clickOutside() {
        Actions builder = new Actions(getDriver());
        builder.moveToElement(panelBase, -20, -20).click().build().perform();
    }
    public WebDriver getDriver() {
        return page.getDriver();
    }

    public AbstractPage getPage() {
        return page;
    }

    protected WebElementFacade getPanelBase() {
        return panelBase;
    }


    protected <T> List<T> initItems(final Class clazz, final List<WebElementFacade> bases) {
        return getPage().initItems(clazz, bases);
    }

    private void initPanel(final WebElementFacade panelBaseLocation, final AbstractPage page) {
        this.page = page;
        this.panelToWebDriver = new WebDriverAdaptor(panelBaseLocation, getDriver());
        ElementLocatorFactory finder = new SmartElementLocatorFactory(panelToWebDriver, MobilePlatform.NONE,
                (int) page.getImplicitWaitTimeout().in(TimeUnit.SECONDS));
        FieldDecorator decorator = new SmartFieldDecorator(finder, getDriver(), page);
        PageFactory.initElements(decorator, this);
    }
}