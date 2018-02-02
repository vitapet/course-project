package com.gmail.vitaliapetsenak.shop.web.model;

import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductStatus;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;

public class ProductForm {
    private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private String count;
    private String price;
    private ProductStatus status;

    public ProductForm() {
        setStatus(ProductStatus.NOT_DELETED);
        setCategory(ProductCategory.FOOD);
    }

    public ProductForm(ProductDTO product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setCategory(product.getCategory());
        setCount(product.getCount().toString());
        setPrice(product.getPrice().toString());
        setStatus(product.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
