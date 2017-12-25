package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_ORDERS_PRODUCTS")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.order", joinColumns = @JoinColumn(name = "F_ORDER_ID")),
        @AssociationOverride(name = "primaryKey.product", joinColumns = @JoinColumn(name = "F_PRODUCT_ID"))
})
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 948802585965096828L;

    @EmbeddedId
    private OrderProductId primaryKey = new OrderProductId();

    @Column(name = "F_COUNT")
    private Integer count;

    @Column(name = "F_AMOUNT")
    private BigDecimal amount;

    public OrderProduct() {
    }

    public OrderProduct(OrderProductId primaryKey, Integer count) {
        this.primaryKey = primaryKey;
        this.count = count;
        this.amount = primaryKey.getProduct().getPrice().multiply(BigDecimal.valueOf(count));
    }

    public OrderProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(OrderProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Order getOrder() {
        return getPrimaryKey().getOrder();
    }

    public void setOrder(Order order) {
        getPrimaryKey().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getPrimaryKey().getProduct();
    }

    public void setProduct(Product product) {
        getPrimaryKey().setProduct(product);
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
}