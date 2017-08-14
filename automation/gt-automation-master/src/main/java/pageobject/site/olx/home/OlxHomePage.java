package pageobject.site.olx.home;

import org.openqa.selenium.WebDriver;

import pageobject.core.annotations.At;
import pageobject.core.annotations.DefaultUrl;
import pageobject.site.olx.AbstractOlxPage;

/**
 * Created by Someone on 15.01.2017.
 */

@DefaultUrl(value = "")
@At(values = "")
public class OlxHomePage extends AbstractOlxPage {

    public OlxHomePage(WebDriver driver) {
        super(driver);
    }
}
