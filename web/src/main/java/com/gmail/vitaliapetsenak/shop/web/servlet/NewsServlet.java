package com.gmail.vitaliapetsenak.shop.web.servlet;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putNewsWithComments(request);
        request.getRequestDispatcher("/WEB-INF/jsp/news.jsp").forward(request, response);
    }
}
