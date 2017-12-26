package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductStatus;

public enum ProductStatusDTO {
    NOT_DELETED(ProductStatus.NOT_DELETED),
    DELETED(ProductStatus.DELETED);

    private ProductStatus status;

    ProductStatusDTO(ProductStatus status) {
        this.status = status;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
