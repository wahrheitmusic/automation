package pageobject.site.olx.postnewad;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.webelement.elements.Button;
import core.webelement.elements.DropDown;
import core.webelement.elements.InputField;
import core.webelement.elements.WebElementFacade;
import pageobject.core.annotations.At;
import pageobject.core.annotations.DefaultUrl;
import pageobject.site.olx.AbstractOlxPage;

/**
 * Created by Someone on 15.01.2017.
 */
@DefaultUrl(value = "")
@At(values = "")
public class OlxPostNewAdPage extends AbstractOlxPage {

    @FindBy(xpath = ".//input[@id = 'add-title']")
    private InputField titleInput;
    @FindBy(xpath = ".//div[@id = 'category-breadcrumb-container']")
    private Button categoryBtn;
    @FindBy(xpath = ".//dl[@id = 'targetparam13']")
    private DropDown conditionDD;
    @FindBy(xpath = ".//dl[@id = 'targetparam175']")
    private DropDown modelDD;
    @FindBy(xpath = ".//dl[@id = 'targetid_private_business']")
    private DropDown privateOrBusynessDD;
    @FindBy(xpath = ".//textarea[@id = 'add-description']")
    private InputField descriptionInput;
    @FindBy(xpath = ".//div[@id = 'map_address']")
    private WebElementFacade locationPanelBase;
    @FindBy(xpath = ".//input[@id = 'add-person']")
    private InputField contactPersonInput;
    @FindBy(xpath = ".//input[@name = 'data[param_price][1]']")
    private InputField priceInput;
    @FindBy(xpath = ".//input[@id = 'save']")
    private Button submitBtn;
    @FindBy(xpath = ".//div[@id='fancybox-content'][@class = 'adding moreLevels']")
    private WebElementFacade categoryPanelBase;

    public OlxPostNewAdPage(final WebDriver driver) {
        super(driver);
    }

    public void clickCategory() {
        categoryBtn.click();
    }

    public void typeTitle(final String title) {
        titleInput.type(title);
    }

    public CategoryPanel getCategoryPanel() {
        return new CategoryPanel(categoryPanelBase, this);
    }

    public void typeDescription(final String description) {
        descriptionInput.type(description);
    }

    public void selectCondition(final String condition) {
        conditionDD.unfold().selectAndClick(condition);
    }

    public void selectPrivateOrBusyness(final String pOrB) {
        privateOrBusynessDD.unfold().selectAndClick(pOrB);
    }

    public void typeContactPerson(final String contactPerson) {
        contactPersonInput.type(contactPerson);
    }

    public void typePrice(final String price) {
        priceInput.type(price);
    }

    public LocationPanel getLocationPanel() {
        return new LocationPanel(locationPanelBase, this);
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void selectModel(final String model) {
        modelDD.unfold().selectAndClick(model);
    }
}
