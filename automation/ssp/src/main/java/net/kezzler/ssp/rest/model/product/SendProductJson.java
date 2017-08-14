package net.kezzler.ssp.rest.model.product;

import com.google.gson.annotations.SerializedName;

import net.kezzler.ssp.domain.Product;

public class SendProductJson {

    @SerializedName("product")
    private ProductItemJson product;

    public SendProductJson() {
    }

    public SendProductJson(final Product product) {
        this.product = new ProductItemJson();
        product.setName(product.getName());
        product.setAlias(product.getAlias());
        product.setGtin(product.getGtin());
        product.setCustomerProductReference(product.getCustomerProductReference());
    }

    public ProductItemJson getProduct() {
        return product;
    }

    public void setProduct(ProductItemJson product) {
        this.product = product;
    }

    public Product getProductModel() {
        return new Product(product.getName(), product.getAlias(), product.getGtin(),
                product.getCustomerProductReference());
    }

    public class ProductItemJson {

        @SerializedName("name")
        private String name;
        @SerializedName("alias")
        private String alias;
        @SerializedName("gtin")
        private long gtin;
        @SerializedName("customerProductReference")
        private long customerProductReference;

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

        public long getGtin() {
            return gtin;
        }

        public void setGtin(long gtin) {
            this.gtin = gtin;
        }

        public long getCustomerProductReference() {
            return customerProductReference;
        }

        public void setCustomerProductReference(long customerProductReference) {
            this.customerProductReference = customerProductReference;
        }
    }
}
