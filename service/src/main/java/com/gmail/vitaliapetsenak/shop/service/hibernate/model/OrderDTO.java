package com.gmail.vitaliapetsenak.shop.service.hibernate.model;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Order;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.OrderProduct;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {
    private Long id;
    private UserDTO user;
    private Date timestamp;
    private OrderStatusDTO status;
    private Set<OrderProductDTO> orderProducts;

    public OrderDTO(Order order) {
        this(newBuilder()
                .id(order.getId())
                .user(new UserDTO(order.getUser()))
                .timestamp(order.getTimestamp())
                .status(OrderStatusDTO.valueOf(order.getStatus().toString()))
        );
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private OrderDTO(Builder builder) {
        setId(builder.id);
        setUser(builder.user);
        setTimestamp(builder.timestamp);
        setStatus(builder.status);
    }

    public Set<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setOrderProductFromPojo(Set<OrderProduct> orderProducts) {
        Set<OrderProductDTO> dtos = new HashSet<>();
        for (OrderProduct orderProduct : orderProducts) {
            dtos.add(new OrderProductDTO(orderProduct));
        }
        this.orderProducts = dtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public OrderStatusDTO getStatus() {
        return status;
    }

    public void setStatus(OrderStatusDTO status) {
        this.status = status;
    }

    public static final class Builder {
        private long id;
        private UserDTO user;
        private Date timestamp;
        private OrderStatusDTO status;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(OrderStatusDTO status) {
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
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
