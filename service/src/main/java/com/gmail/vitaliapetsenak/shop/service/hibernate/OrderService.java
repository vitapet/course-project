package com.gmail.vitaliapetsenak.shop.service.hibernate;

import com.gmail.vitaliapetsenak.shop.service.hibernate.model.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();

    List<OrderDTO> getAllByUserId(Long userId);

    OrderDTO getById(Long id);

    Long add(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);
}
