package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductCategory;

public enum ProductCategoryDTO {
    FOOD(ProductCategory.FOOD),
    ELECTRONICS(ProductCategory.ELECTRONICS),
    COMPUTERS(ProductCategory.COMPUTERS),
    APPLIANCES(ProductCategory.APPLIANCES),
    BUILDING(ProductCategory.BUILDING);

    private ProductCategory category;

    ProductCategoryDTO(ProductCategory category) {
        this.category = category;
    }

    public ProductCategory getCategory() {
        return category;
    }
}
