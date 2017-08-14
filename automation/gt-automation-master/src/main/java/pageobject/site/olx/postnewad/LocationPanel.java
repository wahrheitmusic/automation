package pageobject.site.olx.postnewad;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.lang.String.format;

import core.webelement.elements.InputField;
import core.webelement.elements.WebElementFacade;
import pageobject.core.AbstractPage;
import pageobject.core.AbstractPanel;

/**
 * Created by Someone on 20.01.2017.
 */
public class LocationPanel extends AbstractPanel {
    private final String DISTRICT_XPATH = ".//a[contains(@id, 'suggested-city-district')][text()='%s']";
    @FindBy(xpath = ".//input[@id = 'mapAddress']")
    private InputField locationInput;
    @FindBy(xpath = ".//ul[@id = 'autosuggest-geo-ul']//a[contains(@id, 'suggested-city')]")
    private List<WebElementFacade> cities;

    public LocationPanel(WebElementFacade panelBase, AbstractPage page) {
        super(panelBase, page);
    }

    public void selectLocation(final String locationCity, final String locationDistrict) {
        boolean hasDistrict = null != locationDistrict;
        locationInput.type(locationCity);
        getPage().waitForJquery();
        chooseCity(locationCity, hasDistrict);
        getPage().waitForJquery();
        if (hasDistrict) {
            chooseDistrict(locationDistrict);
            getPage().waitForJquery();
        }
    }

    private void chooseDistrict(final String locationDistrict) {
        String districtXpath = format(DISTRICT_XPATH, locationDistrict);
        getPage().waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(districtXpath)));
        WebElementFacade districtElement = findByXpath(districtXpath);
        districtElement.click();
        districtElement.waitUntilNotVisible();
    }

    private void chooseCity(final String locationCity, final boolean hasDistrict) {
        getPage().waitFor(v -> cities.size() > 0);
        WebElementFacade cityElement = cities.stream().filter(city -> city.getText().equals(locationCity)).findFirst()
                .get();
        if (hasDistrict)
            cityElement.hover();
        else
            cityElement.click();
    }

}
