package com.gmail.vitaliapetsenak.shop.service.hibernate.model;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Product;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private ProductCategoryDTO category;
    private Integer count;
    private BigDecimal price;
    private ProductStatusDTO status;

    public ProductDTO(Product product) {
        this(newBuilder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(ProductCategoryDTO.valueOf(product.getCategory().toString()))
                .count(product.getCount())
                .price(product.getPrice())
                .status(ProductStatusDTO.valueOf(product.getStatus().toString()))
        );
    }

    private ProductDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setCategory(builder.category);
        setCount(builder.count);
        setPrice(builder.price);
        setIsDeleted(builder.status);
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

    public ProductStatusDTO getStatus() {
        return status;
    }

    public void setIsDeleted(ProductStatusDTO status) {
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

    public ProductCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryDTO category) {
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
        private long id;
        private String name;
        private String description;
        private ProductCategoryDTO category;
        private int count;
        private BigDecimal price;
        private ProductStatusDTO status;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder status(ProductStatusDTO status) {
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

        public Builder category(ProductCategoryDTO category) {
            this.category = category;
            return this;
        }

        public Builder count(int count) {
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
