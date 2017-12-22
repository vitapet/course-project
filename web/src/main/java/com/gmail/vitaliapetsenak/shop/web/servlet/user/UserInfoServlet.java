package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_info.jsp").forward(request, response);
    }
}
