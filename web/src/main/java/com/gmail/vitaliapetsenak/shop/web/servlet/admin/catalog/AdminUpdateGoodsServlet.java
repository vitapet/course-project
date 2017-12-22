package com.gmail.vitaliapetsenak.shop.web.servlet.admin.catalog;

import com.gmail.vitaliapetsenak.shop.service.model.GoodsDTO;
import com.gmail.vitaliapetsenak.shop.web.manager.GoodsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUpdateGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDTO goods = GoodsManager.setGoodsFromRequest(request, Command.UPDATE);
        request.getSession().setAttribute("message", "Goods was updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/catalog?name=" + goods.getCategory().toString().toLowerCase());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsManager.setRequestParameters(request, Command.UPDATE);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_catalog_update.jsp").forward(request, response);
    }
}
