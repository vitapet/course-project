package com.gmail.vitaliapetsenak.shop.web.servlet.root.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootAddNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NewsManager.setNewsFromRequest(request, getServletContext(), Command.ADD);
        request.getSession().setAttribute("message", "News was added successfully");
        response.sendRedirect(request.getContextPath() + "/root/news");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_news_add.jsp").forward(request, response);
    }
}
