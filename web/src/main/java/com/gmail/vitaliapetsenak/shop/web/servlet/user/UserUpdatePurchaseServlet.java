package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.PurchasesService;
import com.gmail.vitaliapetsenak.shop.web.manager.PurchasesManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdatePurchaseServlet extends HttpServlet {
    private final PurchasesService purchasesService = PurchasesService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String saveButton = request.getParameter("save");
        if (saveButton != null) {
            PurchasesManager.updatePurchase(request);
        } else {
            PurchasesManager.deletePurchase(request);
        }
        response.sendRedirect(request.getContextPath() + "/user/info/purchases");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasesManager.putPurchaseForUser(request);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_purchase_update.jsp").forward(request, response);
    }
}
