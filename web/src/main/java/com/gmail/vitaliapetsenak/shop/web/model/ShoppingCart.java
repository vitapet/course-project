package com.gmail.vitaliapetsenak.shop.web.model;

import com.gmail.vitaliapetsenak.shop.repository.model.OrderStatus;
import com.gmail.vitaliapetsenak.shop.service.IOrderService;
import com.gmail.vitaliapetsenak.shop.service.IProductService;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.service.model.OrderProductDTO;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;
    private List<OrderProductDTO> items;
    private BigDecimal totalCost;
    private Integer count;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalCost = BigDecimal.ZERO;
        count = 0;
    }

    public List<OrderProductDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderProductDTO> items) {
        count = 0;
        this.items.clear();
        totalCost = BigDecimal.ZERO;
        for (OrderProductDTO item : items) {
            addToCart(item);
        }
        updateTotalCost();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void updateTotalCost() {
        totalCost = BigDecimal.ZERO;
        for (OrderProductDTO orderProduct : items) {
            updateAmount(orderProduct);
            totalCost = totalCost.add(orderProduct.getAmount());
        }
    }

    public void addToCart(OrderProductDTO orderProduct) {
        orderProduct.setId(Long.valueOf(count));
        orderProduct.setAmount(BigDecimal.valueOf(orderProduct.getCount()).multiply(orderProduct.getProduct().getPrice()));
        items.add(orderProduct);
        totalCost = totalCost.add(orderProduct.getAmount());
        count++;
    }

    public void addToCart(Long productId) {
        ProductDTO product = productService.getById(productId);
        OrderProductDTO orderProduct = OrderProductDTO.newBuilder().build();
        orderProduct.setProduct(product);
        orderProduct.setId(Long.valueOf(count));
        orderProduct.setCount(1);
        orderProduct.setAmount(BigDecimal.valueOf(orderProduct.getCount()).multiply(orderProduct.getProduct().getPrice()));
        items.add(orderProduct);
        totalCost = totalCost.add(orderProduct.getAmount());
        count++;
    }

    public void deleteFromCart(Long orderProduct) {
        OrderProductDTO itemToDel = null;
        for (OrderProductDTO item : items) {
            if (item.getId().equals(orderProduct)) {
                itemToDel = item;
            }
        }
        assert itemToDel != null;
        totalCost = totalCost.subtract(BigDecimal.valueOf(itemToDel.getCount()).multiply(itemToDel.getProduct().getPrice()));
        items.remove(itemToDel);
        count--;
    }

    public void deleteFromCart(OrderProductDTO item) {
        totalCost = totalCost.subtract(BigDecimal.valueOf(item.getCount()).multiply(item.getProduct().getPrice()));
        items.remove(item);
    }

    public void confirm() {
        OrderDTO orderDTO = OrderDTO.newBuilder()
                .status(OrderStatus.NEW)
                .userId(getPrincipal().getUserId())
                .build();
        orderDTO.setOrderProducts(items);
        orderService.add(orderDTO);
        items.clear();
        totalCost = BigDecimal.ZERO;
        count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public OrderProductDTO getPurchaseById(Integer id) {
        for (OrderProductDTO item : items) {
            if (item.getId().equals(id.longValue())) {
                return item;
            }
        }
        return null;
    }

    private void updateAmount(OrderProductDTO item) {
        item.setAmount(BigDecimal.valueOf(item.getCount()).multiply(item.getProduct().getPrice()));
    }

    private UserPrincipal getPrincipal() {
        UserPrincipal user = null;
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null) {
            user = (UserPrincipal) principal;
        }
        return user;
    }
}
