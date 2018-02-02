package com.gmail.vitaliapetsenak.shop.repository.model;

public enum ProductCategory {
    FOOD("FOOD"),
    ELECTRONICS("ELECTRONICS"),
    COMPUTERS("COMPUTERS"),
    APPLIANCES("APPLIANCES"),
    BUILDING("BUILDING");

    String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
