package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAll(Integer page,Integer count);

    List<OrderDTO> getAllByUserId(Long userId,Integer page,Integer count);

    OrderDTO getById(Long id);

    Long add(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);
}
