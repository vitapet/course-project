package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.UserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.manager.UsersManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoUpdateServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = UsersManager.parseRequest(request, Command.UPDATE);
        UserDTO sessionUser = (UserDTO) request.getSession().getAttribute("user");
        user.setId(sessionUser.getId());
        user.setRole(sessionUser.getRole());
        userService.updateUserInfo(user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/user/info");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_info_update.jsp").forward(request, response);
    }
}
