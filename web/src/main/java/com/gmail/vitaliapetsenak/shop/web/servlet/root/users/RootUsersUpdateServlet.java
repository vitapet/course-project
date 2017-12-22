package com.gmail.vitaliapetsenak.shop.web.servlet.root.users;

import com.gmail.vitaliapetsenak.shop.web.manager.UsersManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootUsersUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersManager.updateUser(request);
        response.sendRedirect(request.getContextPath() + "/root/users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersManager.putUserToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_users_update.jsp").forward(request, response);
    }
}