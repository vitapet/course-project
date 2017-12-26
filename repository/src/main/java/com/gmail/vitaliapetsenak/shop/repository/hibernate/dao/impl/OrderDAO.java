package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.OrderInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Order;

public class OrderDAO extends AbstractDAO<Order, Long> implements OrderInterface {

    private static volatile OrderDAO instance;

    private OrderDAO() {
    }

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }
}
