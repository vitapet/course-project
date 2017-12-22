package com.gmail.vitaliapetsenak.shop.web.servlet;

import com.gmail.vitaliapetsenak.shop.service.NewsService;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StartPageServlet extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        if (userDTO == null) {
            userDTO = UserDTO.newBuilder().build();
            request.getSession().setAttribute("user", userDTO);
        }
        List<NewsDTO> newsList = newsService.getAllNews();
        request.setAttribute("newsList", newsList);
        request.getRequestDispatcher("/WEB-INF/jsp/start.jsp").forward(request, response);
    }
}
