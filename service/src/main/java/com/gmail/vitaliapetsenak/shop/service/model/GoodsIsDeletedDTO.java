package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.GoodsIsDeleted;

public enum GoodsIsDeletedDTO {
    NOT_DELETED(GoodsIsDeleted.NOT_DELETED),
    DELETED(GoodsIsDeleted.DELETED);

    private GoodsIsDeleted isDeleted;

    GoodsIsDeletedDTO(GoodsIsDeleted isDeleted) {
        this.isDeleted = isDeleted;
    }

    public GoodsIsDeleted getIsDeleted() {
        return isDeleted;
    }
}
