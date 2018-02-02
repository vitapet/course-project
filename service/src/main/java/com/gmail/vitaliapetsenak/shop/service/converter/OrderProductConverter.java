package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.OrderProduct;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.service.model.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProductConverter {
    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private ProductConverter productConverter;

    public OrderProduct convert(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = new OrderProduct();
        if (orderProductDTO.getOrder() != null) {
            try {
                orderProduct.setOrder(orderConverter.convert(orderProductDTO.getOrder()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (orderProductDTO.getProduct() != null) {
            orderProduct.setProduct(productConverter.convert(orderProductDTO.getProduct()));
        }
        if (orderProductDTO.getCount() != null) {
            orderProduct.setCount(orderProductDTO.getCount());
        }
        if (orderProductDTO.getAmount() != null) {
            orderProduct.setAmount(orderProductDTO.getAmount());
        }
        return orderProduct;
    }

    public List<OrderProduct> orderProductsToPOJO(OrderDTO orderDTO) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        List<OrderProductDTO> dtos = orderDTO.getOrderProducts();
        if (dtos != null) {
            for (OrderProductDTO orderProductDTO : dtos) {
                orderProductDTO.setOrder(orderDTO);
                orderProducts.add(convert(orderProductDTO));
            }
            return orderProducts;
        }
        return null;
    }

    public List<OrderProductDTO> orderProductsToDTO(List<OrderProduct> orderProducts) {
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        if (orderProducts != null) {
            for (OrderProduct orderProduct : orderProducts) {
                OrderProductDTO orderProductDTO = new OrderProductDTO(orderProduct);
                orderProductDTO.setOrder(orderConverter.convertToDTO(orderProduct.getOrder()));
                orderProductDTOS.add(orderProductDTO);
            }
            return orderProductDTOS;
        }
        return null;
    }
}
