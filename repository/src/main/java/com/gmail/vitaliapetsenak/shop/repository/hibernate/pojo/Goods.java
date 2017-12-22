package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_GOODS")
public class Goods implements Serializable {

    private static final long serialVersionUID = 3104101270954409581L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_CATEGORY")
    private GoodsCategory category;

    @Column(name = "F_COUNT")
    private Long count;

    @Column(name = "F_PRICE")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS")
    private GoodsStatus status;

    @ManyToMany(mappedBy = "goodsSet")
    private Set<Order> orders = new HashSet<>();

    public Goods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GoodsStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsStatus status) {
        this.status = status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(description, goods.description) &&
                category == goods.category &&
                Objects.equals(count, goods.count) &&
                Objects.equals(price, goods.price) &&
                status == goods.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, category, count, price, status);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", count=" + count +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
