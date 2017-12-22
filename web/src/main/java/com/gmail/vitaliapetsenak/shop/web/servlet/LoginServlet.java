package com.gmail.vitaliapetsenak.shop.web.servlet;

import com.gmail.vitaliapetsenak.shop.service.UserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserStatusDTO;
import com.gmail.vitaliapetsenak.shop.web.model.LoginDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private final UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO user = userService.getUser(login);
        if (user != null && password.equals(user.getPassword())) {
            if (user.getStatus().equals(UserStatusDTO.BLOCKED)) {
                request.getSession().setAttribute("error", "User is blocked.");
                response.sendRedirect(request.getContextPath() + "/login");
            } else if (user.getStatus().equals(UserStatusDTO.DELETED)) {
                request.getSession().setAttribute("error", "User is deleted.");
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.getSession().setAttribute("user", user);
                switch (user.getRole()) {
                    case ADMIN:
                        response.sendRedirect(request.getContextPath() + "/admin");
                        break;
                    case USER:
                        log.info("User with login \"" + user.getLogin() + "\" enter. Role: " + user.getRole());
                        ShoppingCart cart = new ShoppingCart();
                        request.getSession().setAttribute("cart", cart);
                        response.sendRedirect(request.getContextPath() + "/user");
                        break;
                    case ROOT:
                        response.sendRedirect(request.getContextPath() + "/root");
                        break;
                }
            }
        } else {
            request.getSession().setAttribute("loginObject", new LoginDTO(login, password));
            request.getSession().setAttribute("error", "Credentials is not valid.");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDTO loginObject = (LoginDTO) request.getSession().getAttribute("loginObject");
        if (loginObject == null) {
            request.getSession().setAttribute("loginObject", new LoginDTO());
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
