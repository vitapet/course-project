package com.gmail.vitaliapetsenak.shop.web.util;

import com.gmail.vitaliapetsenak.shop.service.IOrderService;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderUtil {

    @Autowired
    private IOrderService orderService;

    public List<OrderDTO> getUserOrders(Long userId, Integer page, Integer count) {
        return orderService.getAllByUserId(userId, page, count);
    }

    public OrderDTO getOrderById(Long id) {
        return orderService.getById(id);
    }

    public void updateOrder(OrderDTO orderDTO) {
        orderService.update(orderDTO);
    }

    public void deleteOrder(OrderDTO orderDTO) {
        orderService.delete(orderDTO);
    }

}
