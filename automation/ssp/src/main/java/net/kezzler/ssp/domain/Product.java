package net.kezzler.ssp.domain;

import net.kezzler.ssp.utils.FrameworkRandomUtils;

import org.apache.commons.lang.ObjectUtils;

public class Product {

    private String name;
    private String alias;
    private Integer productId;
    private Long gtin;
    private Long customerProductReference;

    public Product(final String name) {
        this.name = name;
    }

    public Product(final String name, final String alias, final Long gtin, final Long customerProductReference,
            final Integer productId) {
        this(name, alias, gtin, customerProductReference);
        this.productId = productId;
    }

    public Product(final String name, final String alias, final Long gtin, final Long customerProductReference) {
        this.name = name;
        this.alias = alias;
        this.gtin = gtin;
        this.customerProductReference = customerProductReference;
    }

    public static Product getRandomNewProduct() {
        return new Product(FrameworkRandomUtils.generateRandomProductName(),
                FrameworkRandomUtils.generateRandomAliasName(), FrameworkRandomUtils.generate_random_gtin(),
                FrameworkRandomUtils.generateRandomProductId());
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getGtin() {
        return gtin;
    }

    public void setGtin(Long gtin) {
        this.gtin = gtin;
    }

    public Long getCustomerProductReference() {
        return customerProductReference;
    }

    public void setCustomerProductReference(Long customerProductReference) {
        this.customerProductReference = customerProductReference;
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
            Product tmp = (Product) obj;
            return ObjectUtils.equals(tmp.getAlias(), this.getAlias())
                    && ObjectUtils.equals(tmp.getName(), this.getName())
                    && ObjectUtils.equals(tmp.getGtin(), this.getGtin())
                    && ObjectUtils.equals(tmp.getCustomerProductReference(), this.getCustomerProductReference())
                    && ObjectUtils.equals(tmp.getProductId(), this.getProductId());
        }
    }
}
