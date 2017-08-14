package core.webelement.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Duration;

import core.webelement.elements.Element;
import core.webelement.elements.impl.DefaultElementFactory;
import core.webelement.factory.proxy.LocatingCustomElementHandler;
import core.webelement.factory.proxy.LocatingCustomElementListHandler;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private static Duration implicitWaitTimeout = new Duration(
            Long.valueOf(System.getProperty("webdriver.implicitly.timeout")), TimeUnit.MILLISECONDS);
    private ElementFactory elementFactory = new DefaultElementFactory();
    WebDriver driver;

    public ExtendedFieldDecorator(final WebDriver searchContext) {
        super(new AjaxElementLocatorFactory(searchContext, (int) implicitWaitTimeout.in(TimeUnit.SECONDS)));
        this.driver = searchContext;
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (Element.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        if (List.class.isAssignableFrom(field.getType()) && !WebElement.class.equals(getClassOfListArgument(field))) {
            return decorateElements(loader, field);
        }
        return super.decorate(loader, field);
    }

    protected Element proxyForElement(final ClassLoader loader, final ElementLocator locator, final Field field) {
        InvocationHandler handler = new LocatingCustomElementHandler(locator, driver, elementFactory, field.getType());
        return (Element) Proxy.newProxyInstance(loader, new Class[] { field.getType() }, handler);
    }

    protected List<Element> proxyForElements(final ClassLoader loader, final ElementLocator locator,
            final WebDriver driver, final Field field) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, driver, elementFactory,
                getClassOfListArgument(field));
        return (List<Element>) Proxy.newProxyInstance(loader, new Class[] { List.class }, handler);
    }

    private Class getClassOfListArgument(final Field field) {
        return (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        return proxyForElement(loader, createLocator(field), field);
    }

    private Object decorateElements(final ClassLoader loader, final Field field) {
        return proxyForElements(loader, createLocator(field), driver, field);
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

}
