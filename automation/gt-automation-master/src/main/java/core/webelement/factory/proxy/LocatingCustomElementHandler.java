package core.webelement.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import core.webelement.elements.Element;
import core.webelement.factory.ElementFactory;

/**
 * Created by Someone on 22.01.2017.
 */
public class LocatingCustomElementHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final WebDriver driver;
    private final ElementFactory elementFactory;
    private final Class clazz;

    public LocatingCustomElementHandler(ElementLocator locator, WebDriver driver, ElementFactory elementFactory,
            Class clazz) {
        this.locator = locator;
        this.driver = driver;
        this.elementFactory = elementFactory;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        Element element;
        try {
            element = elementFactory.create((Class<? extends Element>) (clazz), locator.findElement(), driver);
        } catch (NoSuchElementException e) {
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + locator.toString();
            }
            throw e;
        }

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }

        try {
            return method.invoke(element, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
