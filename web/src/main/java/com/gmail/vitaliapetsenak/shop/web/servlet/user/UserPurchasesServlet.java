package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.PurchasesService;
import com.gmail.vitaliapetsenak.shop.web.manager.PurchasesManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPurchasesServlet extends HttpServlet {

    private final PurchasesService purchasesService = PurchasesService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasesManager.putListForUser(request);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_info_purchases.jsp").forward(request, response);
    }
}
