package com.gmail.vitaliapetsenak.shop.web.servlet.admin.purchases;

import com.gmail.vitaliapetsenak.shop.web.manager.PurchasesManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPurchasesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasesManager.putListToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_purchases.jsp").forward(request, response);
    }
}
