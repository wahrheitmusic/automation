package net.kezzler.ssp.steps.ws.ssp;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import net.kezzler.ssp.domain.Order;
import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.domain.User;
import net.kezzler.ssp.rest.client.sspClient.SspRestClient;
import net.kezzler.ssp.rest.model.orders.ReceiveOrdersJson;
import net.kezzler.ssp.rest.model.product.ReceiveProductsJson;
import net.kezzler.ssp.rest.model.product.SendProductJson;
import net.kezzler.ssp.rest.parser.SspRestClientParser;
import net.thucydides.core.annotations.Step;

public class SspRestClientSteps {

    private SspRestClient sspRestClient = new SspRestClient();

    @Step
    public void send_login_request(final User user) {
        sspRestClient.sendGetAuthStatus(user.getUserName(), user.getPassword());
    }

    @Step
    public ReceiveOrdersJson send_get_orders_request() {
        return SspRestClientParser.parseReceiveOrdersJson(sspRestClient.sendGetOrders());
    }

    @Step
    public List<Order> get_orders_from_ws() {
        ReceiveOrdersJson ordersJson = send_get_orders_request();
        if (ordersJson.getTotalRecords().equals(0)) {
            return Collections.emptyList();
        } else {
            return ordersJson.getOrder().stream().map(order -> new Order(order.getDescription(),
                    order.getId().toString(), order.getCreatedBy(), order.getProductModel()))
                    .collect(Collectors.toList());
        }
    }

    @Step
    public Order send_post_orders_request(final String description) {
        String orderId = String.valueOf(SspRestClientParser
                .fromJson(sspRestClient.sendPostOrders(description).getBody(), HashMap.class).get("orderId"));
        // casting to integer because parser recognizes it as a double
        return new Order(description, Double.valueOf(orderId).toString());
    }

    @Step
    public Product send_post_products_request(final Product product) {
        int productId = Integer.valueOf(SspRestClientParser
                .fromJson(sspRestClient.sendPostProducts(new SendProductJson()).getBody(), HashMap.class)
                .get("productId").toString());
        product.setProductId(productId);
        return product;
    }

    @Step
    public ReceiveProductsJson send_get_products_request() {
        return SspRestClientParser.parceReceiveProductsJson(sspRestClient.sendGetProducts());
    }

    @Step
    public void send_post_metadata_schema_request(final String name, final String description) {
        sspRestClient.sendPostMetadaSchema(name, description);
    }
}
