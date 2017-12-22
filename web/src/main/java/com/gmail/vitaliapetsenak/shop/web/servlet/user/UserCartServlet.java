package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.model.PurchasesDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class UserCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String[] parts = request.getParameterValues("count");
        String[] checks = request.getParameterValues("check");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        String delete = request.getParameter("delete");
        if (delete != null) {
            if (checks != null) {
//                if (parts != null) {
//                    for (int i = 0; i < parts.length; i++) {
//                        setPurchaseAttr(parts[i], cart, i);
//                    }
//                }
                setGoodsCount(cart, request);
                for (String item : checks) {
                    cart.deleteFromCart(Long.valueOf(item));
                }
                request.getSession().setAttribute("cart", cart);
                response.sendRedirect(request.getContextPath() + "/user/cart");
            } else {
                request.getSession().setAttribute("message", "Please choice item to delete.");
                response.sendRedirect(request.getContextPath() + "/user/cart");
            }
        } else {
//            if (parts != null) {
//                for (int i = 0; i < parts.length; i++) {
//                    setPurchaseAttr(parts[i], cart, i);
//                }
//            }
            setGoodsCount(cart, request);
            cart.updateTotalCost();
            request.getSession().setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/user/cart/confirm");
        }
    }

    private void setGoodsCount(ShoppingCart cart, HttpServletRequest request) {
        for (PurchasesDTO purchase : cart.getCart()) {
            purchase.setCount(Long.valueOf(request.getParameter("count" + purchase.getId())));
        }
    }

    private void setPurchaseAttr(String part, ShoppingCart cart, int i) {
        PurchasesDTO purchase = cart.getCart().get(i);
        purchase.setCount(Long.valueOf(part));
        purchase.setAmount(BigDecimal.valueOf(purchase.getCount()).multiply(purchase.getGoods().getPrice()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_cart.jsp").forward(request, response);
    }
}
