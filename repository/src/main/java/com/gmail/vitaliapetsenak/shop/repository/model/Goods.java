package com.gmail.vitaliapetsenak.shop.repository.model;

import java.math.BigDecimal;

public class Goods {
    private Long id;
    private String name;
    private String description;
    private GoodsCategory category;
    private Long count;
    private BigDecimal price;
    private GoodsIsDeleted isDeleted;

    private Goods(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setCategory(builder.category);
        setCount(builder.count);
        setPrice(builder.price);
        setIsDeleted(builder.isDeleted);
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

    public GoodsIsDeleted getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(GoodsIsDeleted isDeleted) {
        this.isDeleted = isDeleted;
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

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
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
        private GoodsCategory category;
        private long count;
        private BigDecimal price;
        private GoodsIsDeleted isDeleted;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder isDeleted(GoodsIsDeleted isDeleted) {
            this.isDeleted = isDeleted;
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

        public Builder category(GoodsCategory category) {
            this.category = category;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Goods build() {
            return new Goods(this);
        }

    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
