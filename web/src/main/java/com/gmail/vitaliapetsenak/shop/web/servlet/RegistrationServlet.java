package com.gmail.vitaliapetsenak.shop.web.servlet;

import com.gmail.vitaliapetsenak.shop.service.UserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.manager.UsersManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userCheck = userService.getUser(login);
        String passwordCheck = request.getParameter("passwordCheck");
        UserDTO user = UsersManager.parseRequest(request, Command.ADD);
        if (userCheck == null) {
            if (password.equals(passwordCheck)) {
                userService.addNewUser(user);
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.getSession().setAttribute("userObj", user);
                request.getSession().setAttribute("message", "Password check is incorrect.");
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
            }
        } else {
            request.getSession().setAttribute("userObj", user);
            request.getSession().setAttribute("message", "User with the same login already exists.");
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }
}
