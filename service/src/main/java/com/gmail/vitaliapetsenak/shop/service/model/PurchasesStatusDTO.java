package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.PurchasesStatus;

public enum PurchasesStatusDTO {
    NEW(PurchasesStatus.NEW),
    REVIEWING(PurchasesStatus.REVIEWING),
    IN_PROGRESS(PurchasesStatus.IN_PROGRESS),
    DELIVERED(PurchasesStatus.DELIVERED);

    private PurchasesStatus status;

    PurchasesStatusDTO(PurchasesStatus status) {
        this.status = status;
    }

    public PurchasesStatus getStatus() {
        return status;
    }
}
