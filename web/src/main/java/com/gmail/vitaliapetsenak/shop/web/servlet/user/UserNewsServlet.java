package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = NewsManager.addComment(request);
        response.sendRedirect(request.getContextPath() + "/user/news?id=" + newsId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putNewsWithComments(request);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_news.jsp").forward(request, response);
    }
}
