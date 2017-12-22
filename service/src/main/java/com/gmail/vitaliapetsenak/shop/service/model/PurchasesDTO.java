package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.Purchases;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchasesDTO {
    private Long id;
    private GoodsDTO goods;
    private Long count;
    private UserDTO user;
    private Timestamp timestamp;
    private PurchasesStatusDTO status;
    private BigDecimal amount;

    public PurchasesDTO(Purchases purchases) {
        this(newBuilder()
                .id(purchases.getId())
                .goods(new GoodsDTO(purchases.getGoods()))
                .count(purchases.getCount())
                .user(new UserDTO(purchases.getUser()))
                .timestamp(purchases.getTimestamp())
                .status(PurchasesStatusDTO.valueOf(purchases.getStatus().toString()))
                .amount(purchases.getAmount())
        );
    }

    private PurchasesDTO(Builder builder) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PurchasesStatusDTO getStatus() {
        return status;
    }

    public void setStatus(PurchasesStatusDTO status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsDTO getGoods() {
        return goods;
    }

    public void setGoods(GoodsDTO goods) {
        this.goods = goods;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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
        private GoodsDTO goods;
        private long count;
        private UserDTO user;
        private Timestamp timestamp;
        private PurchasesStatusDTO status;
        private BigDecimal amount;

        private Builder() {
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(PurchasesStatusDTO status) {
            this.status = status;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder goods(GoodsDTO goods) {
            this.goods = goods;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public Builder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public Builder timestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public PurchasesDTO build() {
            return new PurchasesDTO(this);
        }
    }

    @Override
    public String toString() {
        return "PurchasesDTO{" +
                "id=" + id +
                ", goods=" + goods +
                ", count=" + count +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }
}
