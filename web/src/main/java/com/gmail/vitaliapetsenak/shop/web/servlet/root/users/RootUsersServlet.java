package com.gmail.vitaliapetsenak.shop.web.servlet.root.users;

import com.gmail.vitaliapetsenak.shop.web.manager.UsersManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootUsersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersManager.deleteUser(request);
        response.sendRedirect(request.getContextPath() + "/root/users");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersManager.putListToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/root/root_users.jsp").forward(request, response);
    }
}
