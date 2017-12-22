package com.gmail.vitaliapetsenak.shop.web.servlet.root.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootUpdateNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = NewsManager.setNewsFromRequest(request, getServletContext(), Command.UPDATE);
        request.getSession().setAttribute("message", "News was updated successfully");
        response.sendRedirect(request.getContextPath() + "/root/news/selected?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putNewsToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_news_update.jsp").forward(request, response);
    }
}
