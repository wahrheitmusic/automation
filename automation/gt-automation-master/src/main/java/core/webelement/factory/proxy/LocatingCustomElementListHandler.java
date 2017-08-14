package core.webelement.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import core.webelement.factory.ElementFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import core.webelement.elements.Element;

/**
 * Created by Someone on 22.01.2017.
 */
public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final WebDriver driver;
    private final ElementFactory elementFactory;
    private final Class clazz;

    public LocatingCustomElementListHandler(ElementLocator locator, WebDriver driver, ElementFactory elementFactory,
            Class clazz) {
        this.locator = locator;
        this.driver = driver;
        this.elementFactory = elementFactory;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> wrappedElements = locator.findElements();

        List elements = wrappedElements.stream().map(we -> elementFactory.create(
                (Class<? extends Element>) (clazz), we,
                driver)).collect(Collectors.toList());

        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
