package net.kezzler.ssp.definitionsteps.ssp;

import java.util.List;

import net.kezzler.ssp.domain.Order;
import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.engine.session.Session;
import net.kezzler.ssp.engine.session.dictionary.Key;
import net.kezzler.ssp.steps.ssp.KcEngineGeneralSteps;
import net.kezzler.ssp.steps.ssp.order.CodesPanelSteps;
import net.kezzler.ssp.steps.ssp.order.EditOrderPanelSteps;
import net.kezzler.ssp.steps.ssp.order.GoToOrderModalPanelSteps;
import net.kezzler.ssp.steps.ssp.order.HeaderPanelSteps;
import net.kezzler.ssp.steps.ssp.order.NewOrderModalPanelSteps;
import net.kezzler.ssp.steps.ssp.order.OrderDetailsPanelSteps;
import net.kezzler.ssp.steps.ssp.order.OrderPanelSteps;
import net.kezzler.ssp.steps.ssp.order.TopUpModalPanelSteps;
import net.kezzler.ssp.steps.ws.ssp.SspRestClientSteps;
import net.kezzler.ssp.utils.FrameworkFileUtils;
import net.kezzler.ssp.utils.FrameworkRandomUtils;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersDefinitionSteps {

    private static final Logger LOG = LoggerFactory.getLogger(OrdersDefinitionSteps.class.getSimpleName());

    @Steps
    private LogInDefinitionSteps logInDefinitionSteps;
    @Steps
    private SspRestClientSteps sspRestClientsteps;
    @Steps
    private OrderPanelSteps orderPanelSteps;
    @Steps
    private NewOrderModalPanelSteps newOrderModalPanelSteps;
    @Steps
    private HeaderPanelSteps headerPanelSteps;
    @Steps
    private GoToOrderModalPanelSteps goToOrderModalPanelSteps;
    @Steps
    private OrderDetailsPanelSteps orderDetailsPanelSteps;
    @Steps
    private EditOrderPanelSteps editOrderPanelSteps;
    @Steps
    private KcEngineGeneralSteps kcEngineGeneralSteps;
    @Steps
    private CodesPanelSteps codesPanelSteps;
    @Steps
    private TopUpModalPanelSteps topUpModalPanelSteps;

    @Given("SSP-WS-User creates an order if needed")
    public void ws_user_creates_order_if_needed() {
        boolean isNeeded = Integer.valueOf(sspRestClientsteps.send_get_orders_request().getTotalRecords()).equals(0);
        if (isNeeded)
            sspRestClientsteps.send_post_orders_request(FrameworkRandomUtils.generate_random_uuid());
        else {
            LOG.info("The user already has visible order(s)");
        }
    }

    @Given("SSP-WS-User creates an order")
    public void ws_create_an_order() {
        logInDefinitionSteps.ws_user_logged_into_system();
        Order order = sspRestClientsteps.send_post_orders_request(FrameworkRandomUtils.generate_random_uuid());
        Session.addOrderToList(order);
    }

    @When("SSP-UI-User creates an order with section checkbox: $booleanValue")
    public void ui_user_creates_order(final boolean booleanValue) {
        String description = FrameworkRandomUtils.generate_random_uuid();
        orderPanelSteps.click_new_order_btn();
        newOrderModalPanelSteps.enter_description(description);
        if (booleanValue) {
            newOrderModalPanelSteps.click_section_checkbox();
        }
        newOrderModalPanelSteps.click_create_btn();
        Session.put(Key.DESCRIPTION, description);
    }

    @Then("SSP-UI-User verifies that created order is displayed in list")
    public void ui_verify_order_is_displayed_in_list() {
        logInDefinitionSteps.ws_user_logged_into_system();
        Order expectedOrder = sspRestClientsteps.get_orders_from_ws().stream()
                .filter(order -> order.getDescription().equals(Session.getS(Key.DESCRIPTION))).findFirst().orElse(null);
        List<Order> actualOrderList = orderPanelSteps.get_visible_orders();
        assertThat(expectedOrder).as("Created order is not present in the response from 'orders' service").isNotNull();
        assertThat(actualOrderList).as("The order [" + expectedOrder.getOrderId() + "] is not present in the list")
                .contains(expectedOrder);
    }

    @Then("SSP-UI-User verifies that product by index $index is displayed on Order Details view")
    public void ui_verify_order_by_index_is_displayed(final int index) {
        assertThat(orderDetailsPanelSteps.get_product_name())
                .as("Product's name is unexpected on the order's details view")
                .isEqualTo(Session.getProductList().get(index).getName());
    }

    @Given("SSP-UI-User opens orders perspective")
    @When("SSP-UI-User opens orders perspective")
    public void ui_open_order_perspective() {
        headerPanelSteps.click_serialization_link();
        headerPanelSteps.click_orders_link();
    }

    @When("SSP-UI-User adds schema to order by index $index")
    public void ui_add_schema_to_order_by_index(final int index) {
        ui_open_order_by_index_via_goto(index);
        orderDetailsPanelSteps.click_edit_order_button();
        editOrderPanelSteps.select_schema(Session.getS(Key.SCHEMA_NAME));
        editOrderPanelSteps.click_save_button();
    }

    @Then("SSP-UI-User verifies that selected schema is displayed on Order Details view")
    public void ui_check_schema_order_details() {
        assertThat(orderDetailsPanelSteps.get_schema_name()).as("The order's schema name is unexpected")
                .isEqualTo(Session.getS(Key.SCHEMA_NAME));
    }

    @Then("SSP-UI-User verifies that all visible to user orders are listed")
    public void ui_verify_orders_are_listed() {
        List<Order> expectedOrderList = sspRestClientsteps.get_orders_from_ws();

        // discard the value which are not present on ui for correct assertion
        expectedOrderList.stream().filter(order -> null != order.getProduct()).forEach(order -> {
            order.getProduct().setAlias(null);
            order.getProduct().setCustomerProductReference(null);
            order.getProduct().setGtin(null);
            order.getProduct().setProductId(null);
        });
        List<Order> actualOrderList = orderPanelSteps.get_visible_orders();
        assertThat(actualOrderList).as("Actual and expected orders do not match").containsAll(expectedOrderList);
    }

    @When("SSP-UI-User connects the order by index $orderIndex to the product by index $productIndex")
    public void ui_connects_order_to_product_by_indexes(final Integer orderIndex, final Integer productIndex) {
        Order order = Session.getOrderList().get(orderIndex);
        Product product = Session.getProductList().get(productIndex);
        orderPanelSteps.click_go_to_order_button();
        goToOrderModalPanelSteps.enter_order_id(order.getOrderId());
        goToOrderModalPanelSteps.click_go_to_button();
        orderDetailsPanelSteps.click_edit_order_button();
        editOrderPanelSteps.select_product(product.getName());
        editOrderPanelSteps.click_save_button();
        order.setProduct(new Product(product.getName()));
    }

    @When("SSP-UI-User opens order by index $orderIndex via 'Goto order' button")
    public void ui_open_order_by_index_via_goto(final Integer orderIndex) {
        Order order = Session.getOrderList().get(orderIndex);
        orderPanelSteps.click_go_to_order_button();
        goToOrderModalPanelSteps.enter_order_id(order.getOrderId());
        goToOrderModalPanelSteps.click_go_to_button();
    }

    @When("SSP-UI-User opens 'New Top-up' modal")
    public void ui_opens_new_top_up_modal() {
        orderDetailsPanelSteps.click_codes_button();
        codesPanelSteps.click_top_up_button();
    }

    @Then("SSP-UI-User verifies that 'Top up' button is disabled on the 'New Top-up' modal")
    public void ui_is_top_up_btn_disabled() {
        assertThat(topUpModalPanelSteps.is_top_up_button_disabled())
                .as("The 'Top up' button on the 'New Top-up' modal is enabled").isTrue();
    }

    @When("SSP-UI-User uploads random generated csv codes file")
    public void ui_uploads_csv_codes_file() {
        String csvCodesFilePath = FrameworkFileUtils.createCsvCodesFile();
        orderDetailsPanelSteps.click_codes_button();
        ui_uploads_csv(csvCodesFilePath);
        Session.put(Key.ABSOLUTE_FILEPATH, csvCodesFilePath);
    }

    @When("SSP-UI-User uploads csv file by filePath: $filePath")
    public void ui_uploads_csv(final String filePath) {
        codesPanelSteps.click_upload_button();
        codesPanelSteps.select_upload_file_on_upload_modal(filePath);
        codesPanelSteps.click_upload_button_on_upload_modal();
    }

    @When("SSP-UI-User uploads previously used csv codes file")
    public void ui_upload_previous_csv_file() {
        ui_uploads_csv(Session.getS(Key.ABSOLUTE_FILEPATH));
        codesPanelSteps.click_outside_upload_modal();
    }

    @Then("SSP-UI-User verifies that error alert appears")
    public void ui_veifies_error_alert_is_visible() {
        assertThat(kcEngineGeneralSteps.is_error_alert_visible(true)).as("Error alerts is not visible").isTrue();
    }

    @When("SSP-UI-User verifies that count of code lines is equal to $count")
    @Then("SSP-UI-User verifies that count of code lines is equal to $count")
    public void ui_verify_count_of_code_lines(final int count) {
        assertThat(codesPanelSteps.get_count_of_codes()).as("The count of code lines is unexpected").isEqualTo(count);
    }
}
