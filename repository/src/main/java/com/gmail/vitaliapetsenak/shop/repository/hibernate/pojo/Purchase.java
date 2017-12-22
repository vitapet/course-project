package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "T_PURCHASES")
public class Purchase implements Serializable {

    private static final long serialVersionUID = -2505327303561054555L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "F_ORDER_ID")
    private Order order;

    @OneToOne
    @JoinColumn(name = "F_GOODS_ID")
    private Goods goods;

    @Column(name = "F_COUNT")
    private Long count;

    @Column(name = "F_AMOUNT")
    private BigDecimal amount;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) &&
                Objects.equals(order, purchase.order) &&
                Objects.equals(goods, purchase.goods) &&
                Objects.equals(count, purchase.count) &&
                Objects.equals(amount, purchase.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, order, goods, count, amount);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", order=" + order +
                ", goods=" + goods +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }
}
