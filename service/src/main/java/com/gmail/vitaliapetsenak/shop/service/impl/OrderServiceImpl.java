package com.gmail.vitaliapetsenak.shop.service.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IOrderDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.IUserDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Order;
import com.gmail.vitaliapetsenak.shop.repository.model.OrderProduct;
import com.gmail.vitaliapetsenak.shop.service.IOrderService;
import com.gmail.vitaliapetsenak.shop.service.converter.OrderConverter;
import com.gmail.vitaliapetsenak.shop.service.converter.OrderProductConverter;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private OrderProductConverter orderProductConverter;

    @Override
    @Transactional
    public List<OrderDTO> getAll(Integer page, Integer count) {
        return orderConverter.ordersToDTO(orderDAO.getOrders(page, count));
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllByUserId(Long userId, Integer page, Integer count) {
        List<Order> list = orderDAO.getOrders(userId, page, count);
        return orderConverter.ordersToDTO(list);
    }

    @Override
    @Transactional
    public OrderDTO getById(Long id) {
        Order order = orderDAO.findById(id);
        OrderDTO orderDTO = orderConverter.convertToDTO(order);
        orderDTO.setOrderProducts(orderProductConverter.orderProductsToDTO(order.getOrderProducts()));
        return orderDTO;
    }

    @Override
    @Transactional
    public Long add(OrderDTO orderDTO) {
        Long id = null;
        Order order = null;
        orderDTO.setId(null);
        orderDTO.setTimestamp(dateTimeFormatter.format(Timestamp.valueOf(LocalDateTime.now())));
        try {
            order = orderConverter.convert(orderDTO);
            order.setOrderProducts(orderProductConverter.orderProductsToPOJO(orderDTO));
            order.setUser(userDAO.findById(orderDTO.getUserId()));
            for (OrderProduct item : order.getOrderProducts()) {
                item.setOrder(order);
            }
            id = orderDAO.create(order);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return id;
    }

    @Override
    @Transactional
    public void update(OrderDTO orderDTO) {
        Order order = orderDAO.findById(orderDTO.getId());
        if (order.getStatus().equals(orderDTO.getStatus())) {
            orderConverter.convertToPojo(order, orderDTO);
        } else {
            order.setStatus(orderDTO.getStatus());
        }
        orderDAO.update(order);
    }

    @Override
    @Transactional
    public void delete(OrderDTO orderDTO) {
        Order order = orderDAO.findById(orderDTO.getId());
        orderDAO.delete(order);
    }
}
