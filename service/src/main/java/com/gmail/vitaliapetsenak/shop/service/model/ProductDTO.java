package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.Product;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductStatus;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private Integer count;
    private BigDecimal price;
    private ProductStatus status;

    public ProductDTO() {
        setCategory(ProductCategory.FOOD);
        setStatus(ProductStatus.NOT_DELETED);
    }

    public ProductDTO(Product product) {
        this(newBuilder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .count(product.getCount())
                .price(product.getPrice())
                .status(ProductStatus.valueOf(product.getStatus().toString()))
        );
    }

    private ProductDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setCategory(builder.category);
        setCount(builder.count);
        setPrice(builder.price);
        setStatus(builder.status);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String description;
        private ProductCategory category;
        private Integer count;
        private BigDecimal price;
        private ProductStatus status;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder category(ProductCategory category) {
            this.category = category;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductDTO build() {
            return new ProductDTO(this);
        }

    }
}
