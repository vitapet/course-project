package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.OrderProduct;

import java.math.BigDecimal;

public class OrderProductDTO {
    private Long id;
    private OrderDTO order;
    private ProductDTO product;
    private Integer count;
    private BigDecimal amount;

    public OrderProductDTO() {
    }

    public OrderProductDTO(OrderProduct orderProduct) {
        this(newBuilder()
                .order(new OrderDTO(orderProduct.getOrder()))
                .product(new ProductDTO(orderProduct.getProduct()))
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
        private OrderDTO order;
        private ProductDTO product;
        private int count;
        private BigDecimal amount;

        private Builder() {
        }

        public Builder order(OrderDTO order) {
            this.order = order;
            return this;
        }

        public Builder product(ProductDTO product) {
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
