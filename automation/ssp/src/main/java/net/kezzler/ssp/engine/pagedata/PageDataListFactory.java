package net.kezzler.ssp.engine.pagedata;

import java.util.ArrayList;
import java.util.List;

import net.kezzler.ssp.pageobject.AbstractPage;
import net.kezzler.ssp.utils.Timer;
import net.serenitybdd.core.pages.WebElementFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PageDataListFactory {

    private static final Logger LOG = LoggerFactory.getLogger(PageDataListFactory.class.getSimpleName());

    private static PageDataListFactory instance;

    public static PageDataListFactory getInstance() {
        synchronized (PageDataListFactory.class) {
            if (null == instance) {
                instance = new PageDataListFactory();
            }
        }
        return instance;
    }

    public <T extends AbstractDataItem> List<T> getElementsNamed(final Class<T> clazz,
                                                                 final List<WebElementFacade> baseElements, final AbstractPage page) {
        Timer timer = new Timer();
        timer.resetAndStartWatch();
        final List<T> pageDataList = new ArrayList<>();
        LOG.info("processing data lines of type {}", clazz.getCanonicalName());
        for (WebElementFacade baseElement : baseElements) {
            try {
                T dataItem = getElementNamed(clazz, baseElement, page);
                pageDataList.add(dataItem);
            } catch (ReflectiveOperationException e) {
                LOG.error(
                        "error instantiating data line object. Line for base element {} is not included to result list",
                        baseElement.toString(), e);
            }
        }
        final long time = timer.stopAndGetTime();
        LOG.info("found {} elements for the given query, processing took {} ms", pageDataList.size(), time);
        return pageDataList;
    }

    public <T extends AbstractDataItem> T getElementNamed(final Class<T> clazz, final WebElementFacade baseElement,
            final AbstractPage page) throws ReflectiveOperationException {
        T dataItem = clazz.getDeclaredConstructor(WebElementFacade.class, AbstractPage.class).newInstance(baseElement,
                page);
        logDataItem(dataItem);
        return dataItem;
    }

    /*
     * Such a mechanism is provided because invoking toString() on dataItem is usually a quite expensive action (it
     * causes some web elements in dataItem to be explicitly found) So the goal is to save a lot of time unless debug is
     * enabled
     */
    private <T extends AbstractDataItem> void logDataItem(final T dataItem) {
        LOG.debug("{}", dataItem);
    }
}
