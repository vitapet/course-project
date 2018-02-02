package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IOrderDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDAO")
public class OrderDAOImpl extends GenericDAOImpl<Order, Long> implements IOrderDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrders(Integer page, Integer count) {
        return getSession().createCriteria(Order.class)
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrders(Long userId, Integer page, Integer count) {
        return getSession().createCriteria(Order.class)
                .add(Restrictions.eq("user.id", userId))
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }
}
