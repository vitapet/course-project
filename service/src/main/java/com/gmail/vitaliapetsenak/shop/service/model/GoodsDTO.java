package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.Goods;

import java.math.BigDecimal;

public class GoodsDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;
    private Long count;
    private BigDecimal price;
    private GoodsIsDeletedDTO isDeleted;

    public GoodsDTO(Goods goods) {
        this(newBuilder()
                .id(goods.getId())
                .name(goods.getName())
                .description(goods.getDescription())
                .category(CategoryDTO.valueOf(goods.getCategory().toString()))
                .count(goods.getCount())
                .price(goods.getPrice())
                .isDeleted(GoodsIsDeletedDTO.valueOf(goods.getIsDeleted().toString()))
        );
    }

    private GoodsDTO(Builder builder) {
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

    public GoodsIsDeletedDTO getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(GoodsIsDeletedDTO isDeleted) {
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
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
        private CategoryDTO category;
        private long count;
        private BigDecimal price;
        private GoodsIsDeletedDTO isDeleted;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder isDeleted(GoodsIsDeletedDTO isDeleted) {
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

        public Builder category(CategoryDTO category) {
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

        public GoodsDTO build() {
            return new GoodsDTO(this);
        }

    }

    @Override
    public String toString() {
        return "GoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
