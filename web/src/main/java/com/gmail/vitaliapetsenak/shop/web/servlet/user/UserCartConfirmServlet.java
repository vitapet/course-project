package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.PurchasesService;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCartConfirmServlet extends HttpServlet {
    private final PurchasesService purchasesService = PurchasesService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        cart.confirm();
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_cart_confirm.jsp").forward(request, response);
    }
}
