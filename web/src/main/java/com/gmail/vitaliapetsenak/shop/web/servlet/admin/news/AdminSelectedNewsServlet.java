package com.gmail.vitaliapetsenak.shop.web.servlet.admin.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSelectedNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId = NewsManager.deleteComment(request);
        response.sendRedirect(request.getContextPath() + "/admin/news/selected?id=" + newsId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putNewsWithComments(request);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news_selected.jsp").forward(request, response);
    }
}
