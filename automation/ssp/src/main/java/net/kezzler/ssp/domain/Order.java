package net.kezzler.ssp.domain;

import org.apache.commons.lang.ObjectUtils;

public class Order {
    private String description;
    private String orderId;
    private String owner;
    private Product product;

    public Order(final String description, final String orderId, final String owner, final Product product) {
        this(description, orderId, owner);
        this.product = product;
    }

    public Order(final String description, final String orderId, final String owner) {
        this(description, orderId);
        this.owner = owner;
    }

    public Order(final String description, final String orderId) {
        this.description = description;
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Order tmp = (Order) obj;
            return ObjectUtils.equals(tmp.description, this.getDescription())
                    && ObjectUtils.equals(tmp.getOrderId(), this.getOrderId())
                    && ObjectUtils.equals(tmp.getOwner(), this.getOwner());
        }
    }
}
