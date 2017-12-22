package com.gmail.vitaliapetsenak.shop.web.manager;

import com.gmail.vitaliapetsenak.shop.service.GoodsService;
import com.gmail.vitaliapetsenak.shop.service.model.CategoryDTO;
import com.gmail.vitaliapetsenak.shop.service.model.GoodsDTO;
import com.gmail.vitaliapetsenak.shop.service.model.GoodsIsDeletedDTO;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsManager {
    private static final GoodsService goodsService = GoodsService.getInstance();

    public static GoodsDTO setGoodsFromRequest(HttpServletRequest request, Command command) {
        GoodsDTO goods = GoodsDTO.newBuilder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .category(CategoryDTO.valueOf(request.getParameter("categorySelected")))
                .count(Long.valueOf(request.getParameter("count")))
                .price(BigDecimal.valueOf(Double.valueOf(request.getParameter("price"))))
                .build();
        switch (command) {
            case UPDATE:
                goods.setId(Long.valueOf(request.getParameter("id")));
                goods.setIsDeleted(GoodsIsDeletedDTO.valueOf(request.getParameter("goodsStatus")));
                goodsService.updateGoodsInfo(goods);
                break;
            case ADD:
                goods.setIsDeleted(GoodsIsDeletedDTO.NOT_DELETED);
                goodsService.addGoods(goods);
                break;
        }
        return goods;
    }

    public static void setRequestParameters(HttpServletRequest request, Command command) {
        switch (command) {
            case UPDATE:
                String id = request.getParameter("id");
                GoodsDTO goods = goodsService.getGoods(Long.valueOf(id));
                List<GoodsIsDeletedDTO> goodsStatuses = Arrays.asList(GoodsIsDeletedDTO.values());
                request.setAttribute("goods", goods);
                request.setAttribute("goodsStatuses", goodsStatuses);
                break;
        }
        List<CategoryDTO> categories = Arrays.asList(CategoryDTO.values());
        request.setAttribute("categories", categories);
    }

    public static void deleteGoods(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id == null) {
            request.getSession().setAttribute("message", "Choose goods.");
        } else {
            goodsService.deleteGoods(Long.valueOf(id));
            request.getSession().setAttribute("message", "Goods was removed successfully.");
        }
    }

    public static void fillCatalog(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<GoodsDTO> listGoods;
        List<CategoryDTO> categories = new ArrayList<>(Arrays.asList(CategoryDTO.values()));
        if (name == null || name.equals("all")) {
            listGoods = goodsService.getAllGoods();
            name = "all";
        } else {
            listGoods = goodsService.getAllByCategory(CategoryDTO.valueOf(name.toUpperCase()));
        }
        request.getSession().setAttribute("category", name);
        request.setAttribute("categoryName", name);
        request.setAttribute("categories", categories);
        request.setAttribute("listGoods", listGoods);
    }
}
