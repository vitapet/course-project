package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.OrderDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.OrderInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Order;
import com.gmail.vitaliapetsenak.shop.service.hibernate.OrderService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.OrderDTO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static volatile OrderServiceImpl instance;
    private OrderInterface orderDAO = OrderDAO.getInstance();

    private OrderServiceImpl() {
    }

    public static synchronized OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public List<OrderDTO> getAll() {
        orderDAO.getSession().beginTransaction();
        List<Order> orders = orderDAO.findAll();
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order order : orders) {
            dtos.add(new OrderDTO(order));
        }
        orderDAO.getSession().getTransaction().commit();
        return dtos;
    }

    @Override
    public OrderDTO getById(Long id) {
        orderDAO.getSession().beginTransaction();
        Order order = orderDAO.findById(id);
        Hibernate.initialize(order.getOrderProducts());
        OrderDTO orderDTO = new OrderDTO(order);
        orderDTO.setOrderProductFromPojo(order.getOrderProducts());
        orderDAO.getSession().getTransaction().commit();
        return orderDTO;
    }

    @Override
    public Long add(OrderDTO orderDTO) {
        Long id;
        orderDAO.getSession().beginTransaction();
        Order order = new Order();
        Converter.convert(orderDTO, order);
        id = orderDAO.create(order);
        orderDAO.getSession().getTransaction().commit();
        return id;
    }

    @Override
    public void update(OrderDTO orderDTO) {
        orderDAO.getSession().beginTransaction();
        Order order = new Order();
        order.setId(orderDTO.getId());
        Converter.convert(orderDTO, order);
        orderDAO.update(order);
        orderDAO.getSession().getTransaction().commit();
    }

    @Override
    public void delete(OrderDTO orderDTO) {
        orderDAO.getSession().beginTransaction();
        Order order = new Order();
        order.setId(orderDTO.getId());
        Converter.convert(orderDTO, order);
        orderDAO.delete(order);
        orderDAO.getSession().getTransaction().commit();
    }
}
