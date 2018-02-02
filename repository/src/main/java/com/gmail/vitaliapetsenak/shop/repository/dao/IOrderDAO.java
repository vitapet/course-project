package com.gmail.vitaliapetsenak.shop.repository.dao;

import com.gmail.vitaliapetsenak.shop.repository.model.Order;

import java.util.List;

public interface IOrderDAO extends IGenericDAO<Order, Long> {
    List<Order> getOrders(Integer page, Integer count);

    List<Order> getOrders(Long userId, Integer page, Integer count);
}
