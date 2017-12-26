package com.gmail.vitaliapetsenak.shop.service.hibernate.model;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Order;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.OrderProduct;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Product;

import java.math.BigDecimal;

public class OrderProductDTO {
    private Order order;
    private Product product;
    private Integer count;
    private BigDecimal amount;

    public OrderProductDTO(OrderProduct orderProduct) {
        this(newBuilder()
                .order(orderProduct.getOrder())
                .product(orderProduct.getProduct())
                .count(orderProduct.getCount())
                .amount(orderProduct.getAmount())
        );
    }

    public OrderProductDTO(Builder builder) {
        setOrder(builder.order);
        setProduct(builder.product);
        setCount(builder.count);
        setAmount(builder.amount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static final class Builder {
        private Order order;
        private Product product;
        private int count;
        private BigDecimal amount;

        private Builder() {
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public OrderProductDTO build() {
            return new OrderProductDTO(this);
        }
    }
}
