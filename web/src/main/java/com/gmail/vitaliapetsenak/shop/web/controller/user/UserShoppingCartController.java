package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.service.model.OrderDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class UserShoppingCartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/user/cart")
    public String showShoppingCart(Model model) {
        OrderDTO order = new OrderDTO();
        order.setOrderProducts(new ArrayList<>(shoppingCart.getItems()));
        model.addAttribute("order", order);
        return "user/user_cart";
    }

    @PostMapping("/user/cart")
    public String confirmShoppingCart(@ModelAttribute("order") OrderDTO order,
                                      HttpServletRequest request,
                                      Model model) {
        String[] checks = request.getParameterValues("check");
        String delete = request.getParameter("delete");
        if (delete != null) {
            if (checks != null) {
                shoppingCart.setItems(new ArrayList<>(order.getOrderProducts()));
                for (String item : checks) {
                    shoppingCart.deleteFromCart(Long.valueOf(item));
                }
                return "redirect:/user/cart";
            } else {
                model.addAttribute("message", "Please choice item to delete.");
                return "user/user_cart";
            }
        } else {
            shoppingCart.setItems(new ArrayList<>(order.getOrderProducts()));
            shoppingCart.updateTotalCost();
            return "redirect:/user/cart/confirm";
        }
    }

    @GetMapping("/user/cart/confirm")
    public String showOrderConfirmPage(Model model) {
        OrderDTO order = new OrderDTO();
        order.setOrderProducts(new ArrayList<>(shoppingCart.getItems()));
        model.addAttribute("order", order);
        model.addAttribute("totalAmount", shoppingCart.getTotalCost());
        return "user/user_cart_confirm";
    }

    @PostMapping("/user/cart/confirm")
    public String confirmOrder(@ModelAttribute("order") OrderDTO order) {
        shoppingCart.confirm();
        return "redirect:/user";
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
