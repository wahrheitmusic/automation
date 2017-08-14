package net.kezzler.ssp.rest.model.product;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import net.kezzler.ssp.domain.Product;

public class ReceiveProductsJson {

    @SerializedName("content")
    private List<Content> content;
    @SerializedName("last")
    private Boolean last;
    @SerializedName("totalPages")
    private Integer totalPages;
    @SerializedName("totalElements")
    private Integer totalElements;
    @SerializedName("first")
    private Boolean first;
    @SerializedName("numberOfElements")
    private Integer numberOfElements;
    @SerializedName("size")
    private Integer size;
    @SerializedName("number")
    private Integer number;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public class Content {

        @SerializedName("productId")
        private Integer productId;
        @SerializedName("name")
        private String name;
        @SerializedName("gtin")
        private Long gtin;
        @SerializedName("alias")
        private String alias;
        @SerializedName("deleted")
        private Boolean deleted;
        @SerializedName("customerProductReference")
        private Long customerProductReference;

        public Product toProductModel() {
            return new Product(getName(), getAlias(), getGtin(), getCustomerProductReference(), getProductId());
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

        public Long getGtin() {
            return gtin;
        }

        public void setGtin(Long gtin) {
            this.gtin = gtin;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public Long getCustomerProductReference() {
            return customerProductReference;
        }

        public void setCustomerProductReference(Long customerProductReference) {
            this.customerProductReference = customerProductReference;
        }

    }
}
