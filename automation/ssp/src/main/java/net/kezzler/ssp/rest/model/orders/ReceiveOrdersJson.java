package net.kezzler.ssp.rest.model.orders;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.rest.model.product.ReceiveProductsJson;

public class ReceiveOrdersJson {

    @SerializedName("offset")
    private Integer offset;
    @SerializedName("max")
    private Integer max;
    @SerializedName("hasMore")
    private Boolean hasMore;
    @SerializedName("totalRecords")
    private Integer totalRecords;
    @SerializedName("order")
    private List<OrderJson> order;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<OrderJson> getOrder() {
        return order;
    }

    public void setOrder(List<OrderJson> order) {
        this.order = order;
    }

    public class GroupJson {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("organizationId")
        private Integer organizationId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(Integer organizationId) {
            this.organizationId = organizationId;
        }

    }

    public class OrderJson {

        @SerializedName("id")
        private Long id;
        @SerializedName("type")
        private String type;
        @SerializedName("size")
        private Integer size;
        @SerializedName("created")
        private Long created;
        @SerializedName("isLocked")
        private Boolean isLocked;
        @SerializedName("description")
        private String description;
        @SerializedName("owner")
        private OwnerJson owner;
        @SerializedName("metadataPresent")
        private Boolean metadataPresent;
        @SerializedName("sectioned")
        private Boolean sectioned;
        @SerializedName("createdBy")
        private String createdBy;
        @SerializedName("product")
        private ReceiveProductsJson.Content product;

        public Boolean getLocked() {
            return isLocked;
        }

        public void setLocked(Boolean locked) {
            isLocked = locked;
        }

        public ReceiveProductsJson.Content getProduct() {
            return product;
        }

        public void setProduct(ReceiveProductsJson.Content product) {
            this.product = product;
        }

        public Product getProductModel() {
            return null == product ? null : product.toProductModel();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Long getCreated() {
            return created;
        }

        public void setCreated(Long created) {
            this.created = created;
        }

        public Boolean getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(Boolean isLocked) {
            this.isLocked = isLocked;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public OwnerJson getOwner() {
            return owner;
        }

        public void setOwner(OwnerJson owner) {
            this.owner = owner;
        }

        public Boolean getMetadataPresent() {
            return metadataPresent;
        }

        public void setMetadataPresent(Boolean metadataPresent) {
            this.metadataPresent = metadataPresent;
        }

        public Boolean getSectioned() {
            return sectioned;
        }

        public void setSectioned(Boolean sectioned) {
            this.sectioned = sectioned;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

    }

    public class OwnerJson {

        @SerializedName("group")
        private GroupJson group;

        public GroupJson getGroup() {
            return group;
        }

        public void setGroup(GroupJson group) {
            this.group = group;
        }

    }

}
