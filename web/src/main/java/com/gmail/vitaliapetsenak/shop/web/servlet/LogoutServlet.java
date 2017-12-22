package com.gmail.vitaliapetsenak.shop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("cart", null);
        request.getSession().setAttribute("user", null);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
