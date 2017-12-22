package com.gmail.vitaliapetsenak.shop.web.servlet.root.catalog;

import com.gmail.vitaliapetsenak.shop.web.manager.GoodsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootCatalogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsManager.deleteGoods(request);
        response.sendRedirect(request.getContextPath() + "/root/catalog?name=" + request.getSession().getAttribute("category"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsManager.fillCatalog(request);
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_catalog.jsp").forward(request, response);
    }
}
