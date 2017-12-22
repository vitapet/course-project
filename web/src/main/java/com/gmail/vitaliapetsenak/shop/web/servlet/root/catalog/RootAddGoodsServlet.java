package com.gmail.vitaliapetsenak.shop.web.servlet.root.catalog;

import com.gmail.vitaliapetsenak.shop.service.model.GoodsDTO;
import com.gmail.vitaliapetsenak.shop.web.manager.GoodsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootAddGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDTO goods = GoodsManager.setGoodsFromRequest(request, Command.ADD);
        request.getSession().setAttribute("message", "Goods was updated successfully");
        response.sendRedirect(request.getContextPath() + "/root/catalog?name=" + goods.getCategory().toString().toLowerCase());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsManager.setRequestParameters(request, Command.ADD);
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_catalog_add.jsp").forward(request, response);
    }
}
