package com.gmail.vitaliapetsenak.shop.web.controller.admin;

import com.gmail.vitaliapetsenak.shop.repository.model.OrderStatus;
import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.service.IOrderService;
import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.web.model.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminOrdersController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private LoggedInUser loggedInUser;

    @GetMapping({"/admin/orders", "/root/orders"})
    public String showOrders(Model model) {
        List<OrderDTO> orders = orderService.getAll(null,null);
        model.addAttribute("orders", orders);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_orders";
        } else {
            return "root/root_orders";
        }
    }

    @GetMapping({"/admin/orders/{id}/edit", "/root/orders/{id}/edit"})
    public String editOrder(Model model, @PathVariable Long id) {
        OrderDTO order = orderService.getById(id);
        model.addAttribute("order", order);
        List<OrderStatus> statuses = Arrays.asList(OrderStatus.values());
        model.addAttribute("statuses", statuses);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_order_update";
        } else {
            return "root/root_order_update";
        }
    }

    @PostMapping({"/admin/orders/*/edit", "/root/orders/*/edit"})
    public String saveOrder(@ModelAttribute OrderDTO order) {
        orderService.update(order);
        return "redirect:/" + loggedInUser.getUser().getRole().name().toLowerCase() + "/orders";
    }
}
