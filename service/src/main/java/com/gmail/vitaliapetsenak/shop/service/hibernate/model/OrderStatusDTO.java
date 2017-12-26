package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.OrderStatus;

public enum OrderStatusDTO {
    NEW(OrderStatus.NEW),
    REVIEWING(OrderStatus.REVIEWING),
    IN_PROGRESS(OrderStatus.IN_PROGRESS),
    DELIVERED(OrderStatus.DELIVERED);

    private OrderStatus status;

    OrderStatusDTO(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
