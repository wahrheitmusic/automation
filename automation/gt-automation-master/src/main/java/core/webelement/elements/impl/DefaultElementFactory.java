package core.webelement.elements.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;

import core.webelement.factory.ElementFactory;
import core.webelement.elements.Element;

/**
 * Created by Someone on 16.01.2017.
 */

/**
 * Implementation may be extended using dependency injection via reflection or DI library in case of need
 */
public class DefaultElementFactory implements ElementFactory {
    Reflections ref = new Reflections("core.webelement");

    @Override
    public <E extends Element> E create(final Class<E> elementClass, final WebElement wrappedElement,
            final WebDriver driver) {
        try {
            return findImplementationFor(elementClass).getDeclaredConstructor(WebElement.class, WebDriver.class)
                    .newInstance(wrappedElement, driver);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private <E extends Element> Class<? extends E> findImplementationFor(final Class<E> elementClass) {
        Set<Class<? extends E>> impls = ref.getSubTypesOf(elementClass);
        if (impls.size() != 1)
            throw new RuntimeException("There has to be only one implementation for each Element interface");
        return impls.stream().findFirst().get();
    }
}
