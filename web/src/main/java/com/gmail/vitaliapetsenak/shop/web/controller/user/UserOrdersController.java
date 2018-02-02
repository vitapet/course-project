package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import com.gmail.vitaliapetsenak.shop.web.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/info/orders")
public class UserOrdersController {

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping
    public String showUserOrders(Model model) {
        List<OrderDTO> orders = orderUtil.getUserOrders(getPrincipal().getUserId(), null, null);
        model.addAttribute("orders", orders);
        return "user/user_info_orders";
    }

    @GetMapping("/{id}/edit")
    public String showOrderUpdatePage(@PathVariable Long id, Model model) {
        OrderDTO orderDTO = orderUtil.getOrderById(id);
        model.addAttribute("order", orderDTO);
        return "user/user_order_update";
    }

    @PostMapping("/{id}/edit")
    public String saveOrderChanges(@ModelAttribute("order") @Valid OrderDTO order,
                                   BindingResult bindingResult, HttpServletRequest request,
                                   Model model) {
        String delete = request.getParameter("delete");
        if (delete == null) {
            if (!bindingResult.hasErrors()) {
                orderUtil.updateOrder(order);
            } else {
                model.addAttribute("message", "Invalid count input.");
                return "user/user_order_update";
            }
        } else {
            orderUtil.deleteOrder(order);
        }
        return "redirect:/user/info/orders";
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
