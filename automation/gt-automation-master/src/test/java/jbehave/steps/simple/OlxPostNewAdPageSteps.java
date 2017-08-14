package jbehave.steps.simple;

import org.openqa.selenium.WebDriver;

import domain.models.json.ad.NewAdJsonModel;
import pageobject.site.olx.postnewad.OlxPostNewAdPage;

/**
 * Created by Someone on 15.01.2017.
 */
public class OlxPostNewAdPageSteps extends AbstractSteps {
    public OlxPostNewAdPageSteps(final WebDriver driver) {
        super(driver);
    }

    private OlxPostNewAdPage olxPostNewAdPage = new OlxPostNewAdPage(getDriver());

    public void fillNewAd(final NewAdJsonModel newAdJsonModel) {
        olxPostNewAdPage.typeTitle(newAdJsonModel.getTitle());
        olxPostNewAdPage.clickCategory();
        olxPostNewAdPage.getCategoryPanel().selectCategory(newAdJsonModel.getCategory());
        olxPostNewAdPage.typePrice(newAdJsonModel.getPrice());
        olxPostNewAdPage.typeDescription(newAdJsonModel.getDescription());
        olxPostNewAdPage.selectCondition(newAdJsonModel.getCondition());
        olxPostNewAdPage.selectModel(newAdJsonModel.getModel());
        olxPostNewAdPage.selectPrivateOrBusyness(newAdJsonModel.getPrivateOrBusyness());
        olxPostNewAdPage.typeContactPerson(newAdJsonModel.getContactPerson());
        olxPostNewAdPage.getLocationPanel().selectLocation(newAdJsonModel.getLocationCity(),
                newAdJsonModel.getLocationDistrict());
    }

    public void submitNewAd() {
        olxPostNewAdPage.scrollBottom();
        olxPostNewAdPage.clickSubmit();
    }
}
