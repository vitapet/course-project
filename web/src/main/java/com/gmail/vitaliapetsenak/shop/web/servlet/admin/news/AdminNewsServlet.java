package com.gmail.vitaliapetsenak.shop.web.servlet.admin.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.deleteNews(request, getServletContext());
        response.sendRedirect(request.getContextPath() + "/admin/news");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putListToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news.jsp").forward(request, response);
    }
}
