package com.gmail.vitaliapetsenak.shop.web.servlet.user;

import com.gmail.vitaliapetsenak.shop.service.GoodsService;
import com.gmail.vitaliapetsenak.shop.service.model.*;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCatalogServlet extends HttpServlet {
    private final GoodsService goodsService = GoodsService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        PurchasesDTO purchase = PurchasesDTO.newBuilder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .user((UserDTO) request.getSession().getAttribute("user"))
                .goods(goodsService.getGoods(Long.valueOf(id)))
                .count(1)
                .status(PurchasesStatusDTO.NEW)
                .build();
        cart.addToCart(purchase);
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/user/catalog?name=" + request.getSession().getAttribute("category"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<GoodsDTO> listGoods;
        List<CategoryDTO> categories = new ArrayList<>(Arrays.asList(CategoryDTO.values()));
        if (name == null || name.equals("all")) {
            listGoods = goodsService.getAllNotDeletedGoods();
            name = "all";

        } else {
            listGoods = goodsService.getByCategoryIsDeleted(CategoryDTO.valueOf(name), GoodsIsDeletedDTO.NOT_DELETED);
        }
        request.getSession().setAttribute("category", name);
        request.setAttribute("categoryName", name);
        request.setAttribute("categories", categories);
        request.setAttribute("listGoods", listGoods);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_catalog.jsp").forward(request, response);
    }
}
