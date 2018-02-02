package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.Order;
import com.gmail.vitaliapetsenak.shop.repository.model.OrderProduct;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.service.model.OrderProductDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {
    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private OrderProductConverter orderProductConverter;

    public Order convert(OrderDTO orderDTO) throws ParseException {
        Order order = new Order();
        if (orderDTO.getId() != null) {
            order.setId(orderDTO.getId());
        }
        if (orderDTO.getStatus() != null) {
            order.setStatus(orderDTO.getStatus());
        }
        if (orderDTO.getTimestamp() != null) {
            order.setTimestamp(dateTimeFormatter.parse(orderDTO.getTimestamp()));
        }
        return order;
    }

    public void convertToPojo(Order order, OrderDTO orderDTO) {
        order.setStatus(orderDTO.getStatus());
        List<OrderProduct> orderProducts = order.getOrderProducts();
        List<OrderProductDTO> orderProductsDTO = orderDTO.getOrderProducts();
        if (orderProducts.size() == orderDTO.getOrderProducts().size()) {
            for (OrderProduct orderProduct : orderProducts) {
                for (OrderProductDTO orderProductDTO:orderProductsDTO) {
                    if (orderProduct.getProduct().getId() == orderProductDTO.getProduct().getId()){
                        if (orderProduct.getCount()!=orderProductDTO.getCount()){
                            orderProduct.setCount(orderProductDTO.getCount());
                            orderProduct.setAmount(BigDecimal.valueOf(orderProduct.getCount()).multiply(orderProduct.getProduct().getPrice()));;
                        }
                    }
                }
            }
        }
    }

    public List<OrderDTO> ordersToDTO(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                OrderDTO orderDTO = convertToDTO(order);
                orderDTO.setOrderProducts(orderProductConverter.orderProductsToDTO(order.getOrderProducts()));
                orderDTOS.add(orderDTO);
            }
            return orderDTOS;
        }
        return null;
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(order);
        orderDTO.setTimestamp(dateTimeFormatter.format(order.getTimestamp()));
        return orderDTO;
    }
}
