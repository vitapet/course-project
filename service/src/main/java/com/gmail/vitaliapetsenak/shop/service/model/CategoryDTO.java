package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.GoodsCategory;

public enum CategoryDTO {
    FOOD(GoodsCategory.FOOD),
    ELECTRONICS(GoodsCategory.ELECTRONICS),
    COMPUTERS(GoodsCategory.COMPUTERS),
    APPLIANCES(GoodsCategory.APPLIANCES),
    BUILDING(GoodsCategory.BUILDING);

    private GoodsCategory category;

    CategoryDTO(GoodsCategory category) {
        this.category = category;
    }

    public GoodsCategory getCategory() {
        return category;
    }
}
