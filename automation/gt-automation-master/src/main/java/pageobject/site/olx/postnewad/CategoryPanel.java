package pageobject.site.olx.postnewad;

import static java.lang.String.format;

import core.webelement.elements.WebElementFacade;
import pageobject.core.AbstractPage;
import pageobject.core.AbstractPanel;

/**
 * Created by Someone on 18.01.2017.
 */
public class CategoryPanel extends AbstractPanel {

    final String ICON_LINK_ON_MAIN_CATEGORY_SELECTOR_XPATH = ".//a[@data-category-name = '%s'][contains(@class, 'icon-link')]";
    final String SUBCATEGORY_LINK_XPATH = ".//div[@data-category= '%s']//a[contains(@class, 'icon-list')][.//span[text()='%s']]";

    public CategoryPanel(WebElementFacade panelBase, AbstractPage page) {
        super(panelBase, page);
    }

    /**
     * Category path separated by ".". I.E. Category.SubCategory1.SubCategory2
     * 
     * @param categoryPath
     */
    public void selectCategory(final String categoryPath) {
        String[] categories = categoryPath.split("\\.");
        String subCategoryIndex = clickMainCategoryIcon(categories[0]);
        waitForJquery();
        for (int catNumber = 1; catNumber < categories.length; catNumber++) {
            subCategoryIndex = clickSubcategoryLink(subCategoryIndex, categories[catNumber]);
            waitForJquery();
        }
    }

    private String clickMainCategoryIcon(final String categoryName) {
        WebElementFacade category = findByXpath(format(ICON_LINK_ON_MAIN_CATEGORY_SELECTOR_XPATH, categoryName));
        String subCategoryIndex = category.getAttribute("data-category");
        category.click();
        return subCategoryIndex;
    }

    private String clickSubcategoryLink(final String selectedSubCategoryIndex, final String subCategoryName) {
        WebElementFacade subCategory = findByXpath(
                format(SUBCATEGORY_LINK_XPATH, selectedSubCategoryIndex, subCategoryName));
        String subCategoryIndex = subCategory.getAttribute("data-category");
        subCategory.click();
        return subCategoryIndex;

    }
}
