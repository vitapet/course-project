package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_PRODUCTS")
public class Product implements Serializable {

    private static final long serialVersionUID = 112729334855701439L;

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
    private ProductCategory category;

    @Column(name = "F_COUNT")
    private Integer count;

    @Column(name = "F_PRICE")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS")
    private ProductStatus status;

    @OneToMany(mappedBy = "primaryKey.product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Product() {
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                category == product.category &&
                Objects.equals(count, product.count) &&
                Objects.equals(price, product.price) &&
                status == product.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, category, count, price, status);
    }

    @Override
    public String toString() {
        return "Product{" +
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
