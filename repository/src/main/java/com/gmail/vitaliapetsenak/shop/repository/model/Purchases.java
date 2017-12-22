package com.gmail.vitaliapetsenak.shop.repository.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Purchases {
    private Long id;
    private Goods goods;
    private Long count;
    private User user;
    private Timestamp timestamp;
    private PurchasesStatus status;
    private BigDecimal amount;

    private Purchases(Builder builder) {
        setId(builder.id);
        setGoods(builder.goods);
        setCount(builder.count);
        setUser(builder.user);
        setTimestamp(builder.timestamp);
        setStatus(builder.status);
        setAmount(builder.amount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public PurchasesStatus getStatus() {
        return status;
    }

    public void setStatus(PurchasesStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Builder {
        private long id;
        private Goods goods;
        private long count;
        private User user;
        private Timestamp timestamp;
        private PurchasesStatus status;
        private BigDecimal amount;

        private Builder() {
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(PurchasesStatus status) {
            this.status = status;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder goods(Goods goods) {
            this.goods = goods;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder timestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Purchases build() {
            return new Purchases(this);
        }
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "id=" + id +
                ", goods=" + goods +
                ", count=" + count +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }
}
