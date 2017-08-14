package net.kezzler.ssp.pageobject.ssp.kcengine;

import net.kezzler.ssp.pageobject.ssp.AbstractSspPage;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.GoToOrderModalPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.HeaderPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.NewOrderModalPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.CodesPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.EditOrderPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.OrderDetailsPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.OrdersPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.TopUpModalPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.orders.UploadModalPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.products.CreateMetadataSchemaPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.products.CreateProductPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.products.MetadataSchemaPanel;
import net.kezzler.ssp.pageobject.ssp.kcengine.panels.products.ProductsPanel;
import net.kezzler.ssp.utils.wait.PageWaits;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;

import org.openqa.selenium.WebDriver;

@DefaultUrl("/#/kcengine/{1}")
@At(".*/#/kcengine/{1}")
public class KcEnginePage extends AbstractSspPage {

    @FindBy(xpath = "//div[@ng-hide='isHeadlessPage']")
    private WebElementFacade headerPanelBase;
    @FindBy(xpath = "//div[@id='view']")
    private WebElementFacade viewPanelBase;
    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElementFacade modalPanelBase;
    @FindBy(xpath = "//div[@ng-show='order']")
    private WebElementFacade orderDetailsPanelBase;
    @FindBy(xpath = ".//form[@name='topUpForm']")
    private WebElementFacade topUpModalPanelBase;
    @FindBy(xpath = ".//div[@class='alert alert-danger']")
    private WebElementFacade errorAlertPanelBase;
    @FindBy(xpath = "//div[contains(@ng-class'{in: animate}')]")
    private WebElementFacade modalBackdrop;

    public KcEnginePage(final WebDriver driver) {
        super(driver);
    }

    public HeaderPanel getHeaderPanel() {
        headerPanelBase.waitUntilVisible();
        return new HeaderPanel(headerPanelBase, this);
    }

    public void waitForBackDrop() {
        PageWaits.waitForBackDrop(modalBackdrop, 1, 3);
    }

    public boolean isErrorAlertVisible(final boolean expectedResult) {
        return isElementVisible(expectedResult, errorAlertPanelBase);
    }

    public OrdersPanel getOrdersPanel() {
        viewPanelBase.findBy(".//button[@ng-click='order.newDialogOpen()']").waitUntilVisible();
        return new OrdersPanel(viewPanelBase, this);
    }

    public NewOrderModalPanel getNewOrderModalPanel() {
        findBy(".//button[@ng-click='order.newDialogOpen()']").waitUntilVisible();
        return new NewOrderModalPanel(modalPanelBase, this);
    }

    public ProductsPanel getProductsPanel() {
        viewPanelBase.findBy(".//input[@ng-model='filter']").waitUntilVisible();
        return new ProductsPanel(viewPanelBase, this);
    }

    public TopUpModalPanel getTopUpModalPanel() {
        return new TopUpModalPanel(topUpModalPanelBase, this);
    }

    public OrderDetailsPanel getOrderDetailsPanel() {
        findBy("//a[@id='code-order-button']").waitUntilVisible();
        return new OrderDetailsPanel(orderDetailsPanelBase, this);
    }

    public CodesPanel getCodesPanel() {
        findBy("//button[@id='codes-top-up-button']").waitUntilVisible();
        return new CodesPanel(viewPanelBase, this);
    }

    public CreateMetadataSchemaPanel getCreateMetadataSchemaPanel() {
        findBy("//button[@id='save-button']").waitUntilVisible();
        return new CreateMetadataSchemaPanel(viewPanelBase, this);
    }

    public MetadataSchemaPanel getMetadataSchemasPanel() {
        findBy("//a[@id='create-metadata-schema-button']").waitUntilVisible();
        return new MetadataSchemaPanel(viewPanelBase, this);
    }

    public EditOrderPanel getEditOrderPanel() {
        findBy("//h1[@class='page-header ng-binding ng-scope']//i[@class='fa fa-kz-order']").waitUntilVisible();
        return new EditOrderPanel(viewPanelBase, this);
    }

    public CreateProductPanel getCreateProductsPanel() {
        return new CreateProductPanel(viewPanelBase, this);
    }

    public GoToOrderModalPanel getGoToOrderModalPanel() {
        modalPanelBase.waitUntilVisible();
        return new GoToOrderModalPanel(modalPanelBase, this);
    }

    public UploadModalPanel getUploadModalPanel() {
        return new UploadModalPanel(modalPanelBase, this);
    }
}
