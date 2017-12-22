package com.gmail.vitaliapetsenak.shop.web.servlet.admin.purchases;

import com.gmail.vitaliapetsenak.shop.web.manager.PurchasesManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPurchasesUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasesManager.updatePurchaseStatus(request);
        response.sendRedirect(request.getContextPath() + "/admin/purchases");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasesManager.putPurchaseToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_purchases_update.jsp").forward(request, response);
    }
}
