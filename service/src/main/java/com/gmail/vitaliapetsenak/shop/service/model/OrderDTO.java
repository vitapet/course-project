package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.Order;
import com.gmail.vitaliapetsenak.shop.repository.model.OrderStatus;
import com.gmail.vitaliapetsenak.shop.service.validator.order.OrderProductConstraint;

import java.util.List;

public class OrderDTO {
    private Long id;
    private Long userId;
    private String timestamp;
    private OrderStatus status;
    @OrderProductConstraint
    private List<OrderProductDTO> orderProducts;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this(newBuilder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .status(order.getStatus())
        );
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private OrderDTO(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setTimestamp(builder.timestamp);
        setStatus(builder.status);
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static final class Builder {
        private long id;
        private Long userId;
        private String timestamp;
        private OrderStatus status;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderDTO build() {
            return new OrderDTO(this);
        }
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
